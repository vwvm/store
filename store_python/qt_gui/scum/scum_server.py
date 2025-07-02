import sys
import json
import os
import time
import subprocess
import psutil
import threading
import schedule
import win32gui
import win32api
import win32con
import win32process
import win32console
import pyautogui
import ctypes
from datetime import datetime, timedelta
from PySide6.QtWidgets import (QApplication, QMainWindow, QWidget, QVBoxLayout,
                               QHBoxLayout, QLabel, QPushButton, QTableWidget,
                               QTableWidgetItem, QMessageBox, QInputDialog,
                               QTimeEdit, QCalendarWidget, QTextEdit, QHeaderView,
                               QFrame, QComboBox, QGroupBox, QCheckBox, QLineEdit)
from PySide6.QtCore import Qt, QThread, Signal, QTimer, QDateTime, QTime
from PySide6.QtGui import QColor


class ServerManager:
    """服务器管理类，负责与SCUM服务器交互"""

    def __init__(self, server_path):
        self.server_path = server_path

    def get_server_status(self):
        """获取服务器状态"""
        try:
            if not self.server_path:
                return "未配置路径"

            exe_name = os.path.basename(self.server_path)
            for proc in psutil.process_iter(['name', 'cmdline']):
                if proc.info['name'] == exe_name:
                    # 检查命令行参数是否包含 -log
                    cmdline = " ".join(proc.info['cmdline'] or [])
                    if "-log" in cmdline:
                        return "运行中"
            return "已停止"
        except Exception as e:
            return f"检查失败: {str(e)}"

    def send_ctrl_c(self, pid):
        """向Windows控制台进程发送Ctrl+C信号"""
        try:
            # 尝试查找进程的主窗口
            window_title = self.get_process_window_title(pid)
            if window_title:
                # 如果找到窗口，尝试激活窗口并发送Ctrl+C
                if self.activate_window_by_title(window_title):
                    # 等待窗口激活
                    time.sleep(0.5)

                    # 模拟按下Ctrl+C
                    pyautogui.hotkey('ctrl', 'c')
                    print(f"已向进程 {pid} 发送Ctrl+C信号")
                    return True

            # 如果窗口方法失败，尝试使用控制台附加方法（需要管理员权限）
            try:
                # 获取进程句柄
                process_handle = win32api.OpenProcess(
                    win32con.PROCESS_TERMINATE | win32con.PROCESS_QUERY_INFORMATION,
                    False,
                    pid
                )

                if not process_handle:
                    raise Exception(f"无法获取进程 {pid} 的句柄")

                # 尝试附加到进程的控制台
                if win32console.AttachConsole(pid):
                    # 附加成功后，向控制台发送Ctrl+C事件
                    win32api.GenerateConsoleCtrlEvent(win32con.CTRL_C_EVENT, 0)

                    # 释放控制台
                    win32console.FreeConsole()
                    win32api.CloseHandle(process_handle)
                    print(f"已向进程 {pid} 发送Ctrl+C信号")
                    return True

                win32api.CloseHandle(process_handle)

            except Exception as console_ex:
                print(f"控制台附加方法失败: {console_ex}")

            # 如果上述方法都失败，尝试使用任务管理器优雅终止
            print(f"所有方法失败，尝试优雅终止进程 {pid}")
            return self.graceful_terminate_process(pid)

        except Exception as e:
            print(f"发送Ctrl+C失败: {e}")
            return False

    def get_process_window_title(self, pid):
        """获取进程的窗口标题"""

        def enum_windows_callback(hwnd, ctx):
            if win32gui.IsWindowVisible(hwnd) and win32gui.IsWindowEnabled(hwnd):
                _, window_pid = win32process.GetWindowThreadProcessId(hwnd)
                if window_pid == pid:
                    ctx.append(win32gui.GetWindowText(hwnd))
            return True

        window_titles = []
        win32gui.EnumWindows(enum_windows_callback, window_titles)

        if window_titles:
            return window_titles[0]  # 返回第一个找到的窗口标题
        return None

    def activate_window_by_title(self, title):
        """激活指定标题的窗口"""
        hwnd = win32gui.FindWindow(None, title)
        if hwnd:
            # 确保窗口没有最小化
            if win32gui.IsIconic(hwnd):
                win32gui.ShowWindow(hwnd, win32con.SW_RESTORE)

            # 激活窗口
            win32gui.SetForegroundWindow(hwnd)
            return True
        return False

    def graceful_terminate_process(self, pid):
        """使用taskkill命令优雅终止进程"""
        try:
            import subprocess
            result = subprocess.run(
                ["taskkill", "/T", "/PID", str(pid)],
                capture_output=True,
                text=True
            )

            if result.returncode == 0:
                print(f"已优雅终止进程 {pid}")
                return True
            else:
                print(f"终止进程失败: {result.stderr}")
                return False

        except Exception as e:
            print(f"终止进程失败: {e}")
            return False

    def restart_server(self):
        """优雅地重启服务器（通过发送Ctrl+C而不是强制终止）"""
        try:
            # 1. 查找服务器进程
            exe_name = os.path.basename(self.server_path)
            server_process = None

            for proc in psutil.process_iter(['name', 'cmdline']):
                if proc.info['name'] == exe_name:
                    # 检查命令行参数是否包含 -log
                    cmdline = " ".join(proc.info['cmdline'] or [])
                    if "-log" in cmdline:
                        server_process = proc
                        break

            if server_process:
                # 2. 发送Ctrl+C信号
                self.log("发送Ctrl+C信号给服务器进程...")
                self.send_ctrl_c(server_process.pid)

                # 3. 等待进程终止
                timeout = 300  # 5分钟超时
                start_time = time.time()

                while time.time() - start_time < timeout:
                    if not server_process.is_running():
                        self.log("服务器已成功停止")
                        break
                    time.sleep(1)
                else:
                    self.log("等待服务器停止超时，尝试强制终止...")
                    try:
                        server_process.kill()
                        server_process.wait(timeout=10)
                        self.log("服务器已被强制终止")
                    except Exception as e:
                        self.log(f"强制终止服务器失败: {e}")
                        return False
            else:
                self.log("未找到运行中的服务器进程")

            # 4. 启动服务器
            self.log("正在启动服务器...")
            subprocess.Popen([self.server_path, "-log"], creationflags=subprocess.CREATE_NEW_CONSOLE)
            return True
        except Exception as e:
            self.log(f"重启服务器时出错: {str(e)}")
            return False

    def log(self, message):
        """记录日志"""
        timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        print(f"[{timestamp}] {message}")


class ScheduleChecker(QThread):
    """计划任务检查线程"""
    status_updated = Signal(str)
    log_updated = Signal(str)
    schedule_updated = Signal(list)

    def __init__(self, server_manager, schedules):
        super().__init__()
        self.server_manager = server_manager
        self.schedules = schedules
        self.running = True
        self.next_run = None

    def run(self):
        while self.running:
            now = datetime.now()

            # 检查是否有计划任务需要执行
            for s in self.schedules:
                if s.get('enabled', False):
                    schedule_time = datetime.combine(now.date(), s['time'])

                    # 如果是重复任务且时间已过，则计算下一次执行时间
                    if s.get('repeat', 'none') != 'none' and schedule_time < now:
                        if s['repeat'] == 'daily':
                            schedule_time += timedelta(days=1)
                        elif s['repeat'] == 'weekly' and s.get('weekday') is not None:
                            # 计算下一个指定星期几的日期
                            current_weekday = now.weekday()  # 0是周一，6是周日
                            target_weekday = s['weekday']
                            days_ahead = target_weekday - current_weekday
                            if days_ahead <= 0:  # 如果目标星期几已经过去，则计算下一周
                                days_ahead += 7
                            schedule_time = datetime.combine(
                                now.date() + timedelta(days=days_ahead),
                                s['time']
                            )

                    # 如果时间到了，执行重启
                    if schedule_time <= now and (self.next_run is None or schedule_time == self.next_run):
                        self.log_updated.emit(f"执行计划重启: {s['name']}")
                        success = self.server_manager.restart_server()
                        self.log_updated.emit(f"服务器重启 {'成功' if success else '失败'}")

                        # 更新下次执行时间
                        if s.get('repeat', 'none') == 'none':
                            s['enabled'] = False  # 一次性任务执行后禁用

                        self.next_run = None
                        self.schedule_updated.emit(self.schedules)

            # 更新服务器状态
            status = self.server_manager.get_server_status()
            self.status_updated.emit(status)

            # 计算下次计划执行时间
            self.update_next_run()

            time.sleep(1)  # 每秒检查一次

    def update_next_run(self):
        """更新下次计划执行时间"""
        next_run = None
        for s in self.schedules:
            if s.get('enabled', False):
                schedule_time = datetime.combine(datetime.now().date(), s['time'])

                # 处理重复任务
                if s.get('repeat', 'none') != 'none':
                    if schedule_time < datetime.now():
                        if s['repeat'] == 'daily':
                            schedule_time += timedelta(days=1)
                        elif s['repeat'] == 'weekly' and s.get('weekday') is not None:
                            current_weekday = datetime.now().weekday()
                            target_weekday = s['weekday']
                            days_ahead = target_weekday - current_weekday
                            if days_ahead <= 0:
                                days_ahead += 7
                            schedule_time = datetime.combine(
                                datetime.now().date() + timedelta(days=days_ahead),
                                s['time']
                            )

                if next_run is None or schedule_time < next_run:
                    next_run = schedule_time

        self.next_run = next_run

    def get_next_run(self):
        """获取下次计划执行时间"""
        if self.next_run:
            return self.next_run.strftime("%Y-%m-%d %H:%M:%S")
        return "无计划"

    def stop(self):
        """停止线程"""
        self.running = False
        self.wait()


class ScumServerScheduler(QMainWindow):
    """SCUM服务器定时重启管理器主窗口"""

    def __init__(self):
        super().__init__()
        self.server_path = r"C:\Program Files\steamcmd\steamapps\common\SCUM Server\SCUM\Binaries\Win64\SCUMServer.exe"
        self.server_manager = ServerManager(self.server_path)
        self.schedules = []
        self.config_file = "scum_schedules.json"
        self.schedule_checker = None

        # 导入signal模块用于发送Ctrl+C信号
        global signal
        import signal

        self.init_ui()
        self.load_schedules()
        self.update_schedule_table()
        self.start_schedule_checker()

        # 设置窗口样式
        self.setStyleSheet("""
            QMainWindow {
                background-color: #f5f5f5;
            }
            QTableWidget {
                gridline-color: #d0d0d0;
                selection-background-color: #a6c9e2;
            }
            QHeaderView::section {
                background-color: #e0e0e0;
                padding: 4px;
                border: 1px solid #c0c0c0;
                font-weight: bold;
            }
            QPushButton {
                background-color: #4a86e8;
                color: white;
                border: none;
                padding: 6px 12px;
                border-radius: 4px;
            }
            QPushButton:hover {
                background-color: #3a76d8;
            }
            QPushButton:pressed {
                background-color: #2a66c8;
            }
            QLabel {
                color: #333333;
            }
            QTextEdit {
                background-color: #ffffff;
                border: 1px solid #c0c0c0;
                border-radius: 4px;
            }
            QGroupBox {
                border: 1px solid #c0c0c0;
                border-radius: 4px;
                margin-top: 10px;
            }
            QGroupBox::title {
                subcontrol-origin: margin;
                left: 10px;
                padding: 0 3px 0 3px;
            }
        """)

    def init_ui(self):
        """初始化用户界面"""
        self.setWindowTitle("SCUM服务器定时重启管理器")
        self.setGeometry(100, 100, 900, 700)

        # 主布局
        central_widget = QWidget()
        self.setCentralWidget(central_widget)
        main_layout = QVBoxLayout(central_widget)

        # 服务器信息区域
        server_info_group = QGroupBox("服务器信息")
        server_info_layout = QHBoxLayout(server_info_group)

        self.server_path_label = QLabel(f"服务器路径: {self.server_path}")
        server_info_layout.addWidget(self.server_path_label)

        self.change_path_button = QPushButton("更改路径")
        self.change_path_button.clicked.connect(self.change_server_path)
        server_info_layout.addWidget(self.change_path_button)

        self.server_status_label = QLabel("服务器状态: 未知")
        server_info_layout.addWidget(self.server_status_label)

        self.check_status_button = QPushButton("检查状态")
        self.check_status_button.clicked.connect(self.check_server_status)
        server_info_layout.addWidget(self.check_status_button)

        self.restart_now_button = QPushButton("立即重启")
        self.restart_now_button.clicked.connect(self.restart_server_now)
        server_info_layout.addWidget(self.restart_now_button)

        self.add_schedule_button = QPushButton("添加重启计划")
        self.add_schedule_button.clicked.connect(self.add_schedule)
        server_info_layout.addWidget(self.add_schedule_button)

        server_info_layout.addStretch()

        main_layout.addWidget(server_info_group)

        # 计划任务区域
        schedule_group = QGroupBox("重启计划列表")
        schedule_layout = QVBoxLayout(schedule_group)

        schedule_header_layout = QHBoxLayout()
        schedule_header_layout.addWidget(QLabel("下次重启时间:"))
        self.next_run_label = QLabel("无计划")
        schedule_header_layout.addWidget(self.next_run_label)
        schedule_header_layout.addStretch()

        schedule_layout.addLayout(schedule_header_layout)

        self.schedule_table = QTableWidget()
        self.schedule_table.setColumnCount(6)
        self.schedule_table.setHorizontalHeaderLabels(["名称", "时间", "重复", "星期", "启用", "操作"])
        self.schedule_table.horizontalHeader().setSectionResizeMode(QHeaderView.Stretch)
        self.schedule_table.horizontalHeader().setSectionResizeMode(4, QHeaderView.ResizeToContents)
        self.schedule_table.horizontalHeader().setSectionResizeMode(5, QHeaderView.ResizeToContents)
        self.schedule_table.setSelectionBehavior(QTableWidget.SelectRows)
        self.schedule_table.setEditTriggers(QTableWidget.NoEditTriggers)
        schedule_layout.addWidget(self.schedule_table)

        # 按钮布局
        button_layout = QHBoxLayout()

        # self.add_schedule_button = QPushButton("添加重启计划")
        # self.add_schedule_button.clicked.connect(self.add_schedule)
        # button_layout.addWidget(self.add_schedule_button)

        self.remove_schedule_button = QPushButton("删除选中计划")
        self.remove_schedule_button.clicked.connect(self.remove_schedule)
        button_layout.addWidget(self.remove_schedule_button)

        button_layout.addStretch()

        main_layout.addWidget(schedule_group, 2)

        # 日志区域
        log_group = QGroupBox("操作日志")
        log_layout = QVBoxLayout(log_group)

        self.log_text = QTextEdit()
        self.log_text.setReadOnly(True)
        log_layout.addWidget(self.log_text)

        main_layout.addWidget(log_group, 1)

    def load_schedules(self):
        """从配置文件加载重启计划"""
        try:
            if os.path.exists(self.config_file):
                with open(self.config_file, 'r', encoding='utf-8') as f:
                    data = json.load(f)
                    self.server_path = data.get('server_path', self.server_path)
                    self.server_manager.server_path = self.server_path
                    self.server_path_label.setText(f"服务器路径: {self.server_path}")

                    # 加载计划
                    schedules_data = data.get('schedules', [])
                    for s in schedules_data:
                        # 将时间字符串转换为datetime.time对象
                        if 'time' in s and isinstance(s['time'], str):
                            try:
                                time_obj = datetime.strptime(s['time'], '%H:%M:%S').time()
                                s['time'] = time_obj
                            except ValueError:
                                continue
                        self.schedules.append(s)

                self.log(f"已从 {self.config_file} 加载 {len(self.schedules)} 个重启计划")
        except Exception as e:
            self.log(f"加载重启计划失败: {str(e)}")

    def save_schedules(self):
        """保存重启计划到配置文件"""
        try:
            # 将时间对象转换为字符串以便保存
            schedules_to_save = []
            for s in self.schedules:
                schedule_copy = s.copy()
                if 'time' in schedule_copy and isinstance(schedule_copy['time'], datetime.time):
                    schedule_copy['time'] = schedule_copy['time'].strftime('%H:%M:%S')
                schedules_to_save.append(schedule_copy)

            data = {
                'server_path': self.server_path,
                'schedules': schedules_to_save
            }

            with open(self.config_file, 'w', encoding='utf-8') as f:
                json.dump(data, f, ensure_ascii=False, indent=2)

            self.log(f"已保存 {len(self.schedules)} 个重启计划到 {self.config_file}")
        except Exception as e:
            self.log(f"保存重启计划失败: {str(e)}")

    def update_schedule_table(self):
        """更新重启计划表格显示"""
        self.schedule_table.setRowCount(len(self.schedules))

        for row, schedule in enumerate(self.schedules):
            # 名称
            name_item = QTableWidgetItem(schedule.get("name", ""))
            self.schedule_table.setItem(row, 0, name_item)

            # 时间
            time_str = schedule.get("time", datetime.now().time()).strftime("%H:%M:%S")
            time_item = QTableWidgetItem(time_str)
            self.schedule_table.setItem(row, 1, time_item)

            # 重复
            repeat_types = {
                'none': '不重复',
                'daily': '每天',
                'weekly': '每周'
            }
            repeat_item = QTableWidgetItem(repeat_types.get(schedule.get("repeat", "none"), "未知"))
            self.schedule_table.setItem(row, 2, repeat_item)

            # 星期
            weekdays = {
                0: '周一',
                1: '周二',
                2: '周三',
                3: '周四',
                4: '周五',
                5: '周六',
                6: '周日'
            }
            weekday = schedule.get("weekday")
            weekday_item = QTableWidgetItem(weekdays.get(weekday, "不适用") if weekday is not None else "不适用")
            self.schedule_table.setItem(row, 3, weekday_item)

            # 启用状态
            enabled_item = QTableWidgetItem("是" if schedule.get("enabled", False) else "否")
            if schedule.get("enabled", False):
                enabled_item.setBackground(QColor(144, 238, 144))  # 浅绿色
            else:
                enabled_item.setBackground(QColor(255, 182, 193))  # 浅红色
            self.schedule_table.setItem(row, 4, enabled_item)

            # 操作按钮
            action_widget = QWidget()
            action_layout = QHBoxLayout(action_widget)
            action_layout.setContentsMargins(2, 2, 2, 2)

            toggle_button = QPushButton("启用" if not schedule.get("enabled", False) else "禁用")
            toggle_button.setProperty("row", row)
            toggle_button.clicked.connect(lambda checked, r=row: self.toggle_schedule(r))
            action_layout.addWidget(toggle_button)

            edit_button = QPushButton("编辑")
            edit_button.setProperty("row", row)
            edit_button.clicked.connect(lambda checked, r=row: self.edit_schedule(r))
            action_layout.addWidget(edit_button)

            delete_button = QPushButton("删除")
            delete_button.setProperty("row", row)
            delete_button.clicked.connect(lambda checked, r=row: self.remove_schedule(r))
            action_layout.addWidget(delete_button)

            action_widget.setLayout(action_layout)
            self.schedule_table.setCellWidget(row, 5, action_widget)

    def start_schedule_checker(self):
        """启动计划任务检查线程"""
        if self.schedule_checker and self.schedule_checker.isRunning():
            self.schedule_checker.stop()

        self.schedule_checker = ScheduleChecker(self.server_manager, self.schedules)
        self.schedule_checker.status_updated.connect(self.update_server_status)
        self.schedule_checker.log_updated.connect(self.log)
        self.schedule_checker.schedule_updated.connect(self.update_schedules)
        self.schedule_checker.start()

    def update_server_status(self, status):
        """更新服务器状态标签"""
        self.server_status_label.setText(f"服务器状态: {status}")

        # 根据状态设置颜色
        if status == "运行中":
            self.server_status_label.setStyleSheet("color: green; font-weight: bold;")
        elif status == "已停止":
            self.server_status_label.setStyleSheet("color: red; font-weight: bold;")
        else:
            self.server_status_label.setStyleSheet("color: orange; font-weight: bold;")

    def update_schedules(self, schedules):
        """更新重启计划列表"""
        self.schedules = schedules
        self.update_schedule_table()
        self.save_schedules()
        self.update_next_run_label()

    def update_next_run_label(self):
        """更新下次重启时间标签"""
        next_run = "无计划"
        if self.schedule_checker:
            next_run = self.schedule_checker.get_next_run()
        self.next_run_label.setText(next_run)

    def change_server_path(self):
        """更改服务器路径"""
        path, _ = QFileDialog.getOpenFileName(
            self, "选择服务器可执行文件", self.server_path, "可执行文件 (*.exe)"
        )

        if path:
            self.server_path = path
            self.server_manager.server_path = path
            self.server_path_label.setText(f"服务器路径: {self.server_path}")
            self.save_schedules()
            self.log(f"已更新服务器路径: {self.server_path}")

    def check_server_status(self):
        """检查服务器状态"""
        status = self.server_manager.get_server_status()
        self.log(f"服务器状态: {status}")
        self.update_server_status(status)

    def restart_server_now(self):
        """立即重启服务器"""
        if QMessageBox.question(self, "确认", "确定要立即重启服务器吗?") != QMessageBox.Yes:
            return

        self.log("正在立即重启服务器...")
        success = self.server_manager.restart_server()
        self.log(f"服务器重启 {'成功' if success else '失败'}")

    def add_schedule(self):
        """添加新的重启计划"""
        dialog = QWidget(self)
        dialog.setWindowTitle("添加重启计划")
        dialog.setGeometry(200, 200, 400, 300)

        layout = QVBoxLayout(dialog)

        # 名称
        name_layout = QHBoxLayout()
        name_layout.addWidget(QLabel("计划名称:"))
        name_edit = QLineEdit()
        name_layout.addWidget(name_edit)
        layout.addLayout(name_layout)

        # 时间
        time_layout = QHBoxLayout()
        time_layout.addWidget(QLabel("重启时间:"))
        time_edit = QTimeEdit()
        time_edit.setTime(QTime.currentTime().addSecs(3600))  # 默认1小时后
        time_layout.addWidget(time_edit)
        layout.addLayout(time_layout)

        # 重复选项
        repeat_layout = QHBoxLayout()
        repeat_layout.addWidget(QLabel("重复方式:"))
        repeat_combo = QComboBox()
        repeat_combo.addItems(["不重复", "每天", "每周"])
        repeat_layout.addWidget(repeat_combo)
        layout.addLayout(repeat_layout)

        # 星期选择 (仅当重复方式为每周时显示)
        weekday_layout = QHBoxLayout()
        weekday_layout.addWidget(QLabel("星期:"))
        weekday_combo = QComboBox()
        weekday_combo.addItems(["周一", "周二", "周三", "周四", "周五", "周六", "周日"])
        weekday_layout.addWidget(weekday_combo)
        weekday_widget = QWidget()
        weekday_widget.setLayout(weekday_layout)
        weekday_widget.hide()  # 默认隐藏
        layout.addWidget(weekday_widget)

        # 启用状态
        enabled_check = QCheckBox("启用此计划")
        enabled_check.setChecked(True)
        layout.addWidget(enabled_check)

        # 按钮
        button_layout = QHBoxLayout()
        ok_button = QPushButton("确定")
        cancel_button = QPushButton("取消")
        button_layout.addWidget(ok_button)
        button_layout.addWidget(cancel_button)
        layout.addLayout(button_layout)

        # 连接信号
        repeat_combo.currentIndexChanged.connect(
            lambda index: weekday_widget.show() if index == 2 else weekday_widget.hide())
        cancel_button.clicked.connect(dialog.close)

        def add_new_schedule():
            name = name_edit.text().strip()
            if not name:
                QMessageBox.warning(dialog, "警告", "请输入计划名称")
                return

            time_obj = time_edit.time().toPython()

            repeat_types = ["none", "daily", "weekly"]
            repeat = repeat_types[repeat_combo.currentIndex()]

            weekday = weekday_combo.currentIndex() if repeat == "weekly" else None

            enabled = enabled_check.isChecked()

            new_schedule = {
                "name": name,
                "time": time_obj,
                "repeat": repeat,
                "weekday": weekday,
                "enabled": enabled
            }

            self.schedules.append(new_schedule)
            self.update_schedules(self.schedules)
            self.log(f"已添加重启计划: {name}")
            dialog.close()

        ok_button.clicked.connect(add_new_schedule)

        dialog.show()

    def remove_schedule(self, row=None):
        """删除重启计划"""
        if row is not None:
            # 直接删除指定行
            schedule_name = self.schedules[row]["name"]
            self.schedules.pop(row)
            self.update_schedules(self.schedules)
            self.log(f"已删除重启计划: {schedule_name}")
        else:
            # 删除选中的行
            selected_rows = sorted(set(index.row() for index in self.schedule_table.selectedIndexes()), reverse=True)
            if not selected_rows:
                QMessageBox.information(self, "提示", "请先选择要删除的重启计划")
                return

            if QMessageBox.question(self, "确认",
                                    f"确定要删除选中的 {len(selected_rows)} 个重启计划吗?") != QMessageBox.Yes:
                return

            for row in selected_rows:
                schedule_name = self.schedules[row]["name"]
                self.schedules.pop(row)
                self.log(f"已删除重启计划: {schedule_name}")

            self.update_schedules(self.schedules)

    def toggle_schedule(self, row):
        """切换重启计划的启用状态"""
        if 0 <= row < len(self.schedules):
            self.schedules[row]["enabled"] = not self.schedules[row]["enabled"]
            status = "启用" if self.schedules[row]["enabled"] else "禁用"
            self.log(f"已{status}重启计划: {self.schedules[row]['name']}")
            self.update_schedules(self.schedules)

    def edit_schedule(self, row):
        """编辑重启计划"""
        if 0 <= row < len(self.schedules):
            schedule = self.schedules[row]

            dialog = QWidget(self)
            dialog.setWindowTitle(f"编辑重启计划: {schedule['name']}")
            dialog.setGeometry(200, 200, 400, 300)

            layout = QVBoxLayout(dialog)

            # 名称
            name_layout = QHBoxLayout()
            name_layout.addWidget(QLabel("计划名称:"))
            name_edit = QLineEdit()
            name_edit.setText(schedule['name'])
            name_layout.addWidget(name_edit)
            layout.addLayout(name_layout)

            # 时间
            time_layout = QHBoxLayout()
            time_layout.addWidget(QLabel("重启时间:"))
            time_edit = QTimeEdit()
            time_edit.setTime(QTime.fromString(schedule['time'].strftime("%H:%M:%S"), "HH:mm:ss"))
            time_layout.addWidget(time_edit)
            layout.addLayout(time_layout)

            # 重复选项
            repeat_layout = QHBoxLayout()
            repeat_layout.addWidget(QLabel("重复方式:"))
            repeat_combo = QComboBox()
            repeat_combo.addItems(["不重复", "每天", "每周"])
            repeat_types = ["none", "daily", "weekly"]
            repeat_combo.setCurrentIndex(repeat_types.index(schedule.get('repeat', 'none')))
            repeat_layout.addWidget(repeat_combo)
            layout.addLayout(repeat_layout)

            # 星期选择 (仅当重复方式为每周时显示)
            weekday_layout = QHBoxLayout()
            weekday_layout.addWidget(QLabel("星期:"))
            weekday_combo = QComboBox()
            weekday_combo.addItems(["周一", "周二", "周三", "周四", "周五", "周六", "周日"])
            if schedule.get('repeat') == 'weekly' and schedule.get('weekday') is not None:
                weekday_combo.setCurrentIndex(schedule['weekday'])
            weekday_layout.addWidget(weekday_combo)
            weekday_widget = QWidget()
            weekday_widget.setLayout(weekday_layout)
            if schedule.get('repeat') != 'weekly':
                weekday_widget.hide()  # 默认隐藏
            layout.addWidget(weekday_widget)

            # 启用状态
            enabled_check = QCheckBox("启用此计划")
            enabled_check.setChecked(schedule.get('enabled', False))
            layout.addWidget(enabled_check)

            # 按钮
            button_layout = QHBoxLayout()
            ok_button = QPushButton("确定")
            cancel_button = QPushButton("取消")
            button_layout.addWidget(ok_button)
            button_layout.addWidget(cancel_button)
            layout.addLayout(button_layout)

            # 连接信号
            repeat_combo.currentIndexChanged.connect(
                lambda index: weekday_widget.show() if index == 2 else weekday_widget.hide())
            cancel_button.clicked.connect(dialog.close)

            def save_edited_schedule():
                name = name_edit.text().strip()
                if not name:
                    QMessageBox.warning(dialog, "警告", "请输入计划名称")
                    return

                time_obj = time_edit.time().toPython()

                repeat_types = ["none", "daily", "weekly"]
                repeat = repeat_types[repeat_combo.currentIndex()]

                weekday = weekday_combo.currentIndex() if repeat == "weekly" else None

                enabled = enabled_check.isChecked()

                # 更新计划
                schedule.update({
                    "name": name,
                    "time": time_obj,
                    "repeat": repeat,
                    "weekday": weekday,
                    "enabled": enabled
                })

                self.update_schedules(self.schedules)
                self.log(f"已更新重启计划: {name}")
                dialog.close()

            ok_button.clicked.connect(save_edited_schedule)

            dialog.show()

    def log(self, message):
        """添加日志消息"""
        timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        self.log_text.append(f"[{timestamp}] {message}")

    def closeEvent(self, event):
        """窗口关闭事件"""
        if self.schedule_checker and self.schedule_checker.isRunning():
            self.schedule_checker.stop()

        self.save_schedules()
        event.accept()


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = ScumServerScheduler()
    window.show()
    sys.exit(app.exec())