import datetime
from typing import List, Dict, Any
from PySide6.QtCore import Qt, QThread, Signal, QTimer


class ScheduleChecker(QThread):
    """计划任务检查线程"""
    status_updated = Signal(str)
    log_updated = Signal(str)
    schedule_updated = Signal(list)
    next_run_updated = Signal(str)

    def __init__(self, server_manager, schedules: List[Dict[str, Any]]):
        super().__init__()
        self.server_manager = server_manager
        self.schedules = schedules
        self.running = False
        self.timer = QTimer()
        self.timer.timeout.connect(self.check_schedules)
        self.last_checked_second = datetime.datetime.now().second  # 记录上次检查的秒数
        self.last_executed = {}  # 记录每个计划上次执行的时间

    def run(self):
        """线程主循环"""
        self.running = True
        self.log_updated.emit("计划检查线程已启动")
        self.check_server_status()
        self.timer.start(1000)  # 每秒检查一次

        while self.running:
            self.check_schedules()
            self.msleep(100)

        self.timer.stop()
        self.log_updated.emit("计划检查线程已停止")

    def stop(self):
        """停止线程"""
        self.running = False

    def check_server_status(self):
        """检查服务器状态"""
        try:
            status = self.server_manager.get_server_status()
            self.status_updated.emit(status)
        except Exception as e:
            self.log_updated.emit(f"检查服务器状态失败: {str(e)}")

    def check_schedules(self):
        """检查是否有需要执行的计划"""
        now = datetime.datetime.now()
        current_second = now.second

        # 防止同一秒重复检查（提高效率）
        if current_second == self.last_checked_second:
            return
        self.last_checked_second = current_second

        # 每秒检查一次，在每分钟的第0秒记录日志
        if current_second == 0:
            self.log_updated.emit(f"计划检查线程运行中: {now.strftime('%Y-%m-%d %H:%M:%S')}")

        next_run = "无计划"
        enabled_schedules = [s for s in self.schedules if s.get("enabled", False)]

        if not enabled_schedules:
            self.next_run_updated.emit(next_run)
            return

        closest_schedule = None
        closest_time = None

        for schedule in enabled_schedules:
            time_obj = schedule["time"]
            repeat = schedule["repeat"]
            weekday = schedule.get("weekday")

            # 计算计划执行时间
            schedule_time = self._calculate_schedule_time(now, time_obj, repeat, weekday)

            if not schedule_time:
                continue

            # 检查是否需要执行（精度提高到秒级）
            if self._should_execute(now, schedule_time, schedule):
                self.execute_schedule(schedule)
                self.last_executed[schedule.get("id", id(schedule))] = now
                continue  # 执行后跳过后续比较

            # 记录最近的计划
            if closest_time is None or schedule_time < closest_time:
                closest_time = schedule_time
                closest_schedule = schedule

        # 更新下次运行时间
        if closest_time:
            next_run = closest_time.strftime("%Y-%m-%d %H:%M:%S")
        self.next_run_updated.emit(next_run)

    def _calculate_schedule_time(self, now, time_obj, repeat, weekday):
        """计算计划的执行时间"""
        try:
            if repeat == "none":
                # 不重复：今天的指定时间
                return datetime.datetime.combine(now.date(), time_obj)

            elif repeat == "daily":
                # 每天：今天的指定时间，如果已过则为明天
                schedule_date = datetime.datetime.combine(now.date(), time_obj)
                if schedule_date <= now:  # 使用<=确保当前时间点能触发
                    schedule_date += datetime.timedelta(days=1)
                return schedule_date

            elif repeat == "weekly" and weekday is not None:
                # 每周：计算下一个指定星期几的时间
                days_ahead = weekday - now.weekday()
                if days_ahead < 0:
                    days_ahead += 7
                schedule_date = now.date() + datetime.timedelta(days=days_ahead)
                return datetime.datetime.combine(schedule_date, time_obj)

        except Exception as e:
            self.log_updated.emit(f"计算计划时间失败: {str(e)}")
            return None

    def _should_execute(self, now, schedule_time, schedule):
        """判断计划是否应该执行（精确到秒）"""
        # 如果计划时间在未来，不执行
        if schedule_time > now:
            return False

        # 检查是否为重复类型
        if schedule["repeat"] != "none":
            # 重复类型：每分钟检查一次，在计划时间的分钟触发
            return now.minute == schedule_time.minute and now.hour == schedule_time.hour

        # 不重复类型：未执行且时间已到
        return not schedule.get("executed", False)

    def execute_schedule(self, schedule: Dict[str, Any]):
        """执行重启计划"""
        schedule_name = schedule["name"]
        self.log_updated.emit(f"执行重启计划: {schedule_name}")

        # 执行服务器重启
        self.log_updated.emit("正在重启服务器...")
        success = self.server_manager.restart_server()

        if success:
            self.log_updated.emit(f"服务器重启成功 ({schedule_name})")
            self.status_updated.emit("重启中")
        else:
            self.log_updated.emit(f"服务器重启失败 ({schedule_name})")

        # 对于不重复的计划，标记为已执行
        if schedule["repeat"] == "none":
            schedule["executed"] = True
            self.schedule_updated.emit(self.schedules)

    def get_next_run(self) -> str:
        """获取下次运行时间"""
        now = datetime.datetime.now()
        enabled_schedules = [s for s in self.schedules if s.get("enabled", False)]

        if not enabled_schedules:
            return "无计划"

        closest_time = None
        for schedule in enabled_schedules:
            time_obj = schedule["time"]
            repeat = schedule["repeat"]
            weekday = schedule.get("weekday")

            schedule_time = self._calculate_schedule_time(now, time_obj, repeat, weekday)
            if schedule_time and (closest_time is None or schedule_time < closest_time):
                closest_time = schedule_time

        if closest_time:
            return closest_time.strftime("%Y-%m-%d %H:%M:%S")
        return "无计划"