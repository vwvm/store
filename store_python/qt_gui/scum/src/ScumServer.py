import sys
import json
import os
import datetime
from typing import List, Dict, Any, Optional
from PySide6.QtWidgets import (QApplication, QMainWindow, QWidget, QVBoxLayout,
                               QHBoxLayout, QLabel, QPushButton, QTableWidget,
                               QTableWidgetItem, QMessageBox,
                               QTimeEdit, QTextEdit, QHeaderView,
                               QComboBox, QGroupBox, QCheckBox, QLineEdit, QFileDialog,
                               QDialog, QFormLayout)
from PySide6.QtCore import Qt, QTime, QThread, Signal, QTimer
from PySide6.QtGui import QColor, QIcon

# 导入模块（假设已正确组织目录结构）
from src.ScheduleChecker import ScheduleChecker
from src.SteamCMDManager import SteamCMDManager
from src.ServerManager import ServerManager

class ScheduleDialog(QDialog):
    """重启计划编辑对话框"""
    schedule_updated = Signal(dict)
    schedule_added = Signal(dict)

    def __init__(self, parent=None, schedule: Optional[Dict[str, Any]] = None):
        super().__init__(parent)
        self.schedule = schedule or {
            "name": "",
            "time": QTime.currentTime().addSecs(3600).toPython(),
            "repeat": "none",
            "weekday": None,
            "enabled": True
        }
        self.is_editing = schedule is not None
        self.init_ui()

    def init_ui(self):
        """初始化对话框界面"""
        self.setWindowTitle("编辑重启计划" if self.is_editing else "添加重启计划")
        self.setMinimumWidth(400)

        layout = QFormLayout(self)

        # 计划名称
        self.name_edit = QLineEdit(self.schedule.get("name", ""))
        layout.addRow("计划名称:", self.name_edit)

        # 重启时间
        time = QTime.fromString(self.schedule.get("time", "").strftime("%H:%M:%S"), "HH:mm:ss")
        self.time_edit = QTimeEdit(time)
        layout.addRow("重启时间:", self.time_edit)

        # 重复方式
        self.repeat_combo = QComboBox()
        self.repeat_combo.addItems(["不重复", "每天", "每周"])
        repeat_types = ["none", "daily", "weekly"]
        self.repeat_combo.setCurrentIndex(repeat_types.index(self.schedule.get("repeat", "none")))
        self.repeat_combo.currentIndexChanged.connect(self._toggle_weekday_visibility)
        layout.addRow("重复方式:", self.repeat_combo)

        # 星期选择
        self.weekday_combo = QComboBox()
        self.weekday_combo.addItems(["周一", "周二", "周三", "周四", "周五", "周六", "周日"])
        if self.schedule.get("weekday") is not None:
            self.weekday_combo.setCurrentIndex(self.schedule.get("weekday"))

        self.weekday_layout = QHBoxLayout()
        self.weekday_layout.addWidget(QLabel("星期:"))
        self.weekday_layout.addWidget(self.weekday_combo)
        self.weekday_widget = QWidget()
        self.weekday_widget.setLayout(self.weekday_layout)
        layout.addRow(self.weekday_widget)

        # 启用状态
        self.enabled_check = QCheckBox("启用此计划")
        self.enabled_check.setChecked(self.schedule.get("enabled", True))
        layout.addRow("", self.enabled_check)

        # 按钮
        button_layout = QHBoxLayout()
        ok_button = QPushButton("确定")
        cancel_button = QPushButton("取消")
        button_layout.addWidget(ok_button)
        button_layout.addWidget(cancel_button)
        layout.addRow(button_layout)

        # 信号连接
        ok_button.clicked.connect(self.accept_schedule)
        cancel_button.clicked.connect(self.reject)

        # 初始化星期选择可见性
        self._toggle_weekday_visibility(self.repeat_combo.currentIndex())

    def _toggle_weekday_visibility(self, index: int):
        """切换星期选择的可见性"""
        self.weekday_widget.setVisible(index == 2)  # 仅当重复方式为"每周"时显示

    def accept_schedule(self):
        """确认并保存计划"""
        name = self.name_edit.text().strip()
        if not name:
            QMessageBox.warning(self, "警告", "请输入计划名称")
            return

        time_obj = self.time_edit.time().toPython()
        repeat_types = ["none", "daily", "weekly"]
        repeat = repeat_types[self.repeat_combo.currentIndex()]
        weekday = self.weekday_combo.currentIndex() if repeat == "weekly" else None
        enabled = self.enabled_check.isChecked()

        new_schedule = {
            "name": name,
            "time": time_obj,
            "repeat": repeat,
            "weekday": weekday,
            "enabled": enabled
        }

        if self.is_editing:
            self.schedule_updated.emit(new_schedule)
        else:
            self.schedule_added.emit(new_schedule)

        self.accept()


class ScumServerScheduler(QMainWindow):
    """SCUM服务器定时重启管理器主窗口"""

    def __init__(self):
        super().__init__()
        self.server_path = r"C:\Program Files\steamcmd\steamapps\common\SCUM Server\SCUM\Binaries\Win64\SCUMServer.exe"
        self.server_manager = ServerManager(self.server_path)
        self.schedules: List[Dict[str, Any]] = []
        self.config_file = "scum_schedules.json"
        self.schedule_checker = None
        self.steam_manager = SteamCMDManager()

        self.init_ui()
        self.load_schedules()
        self.update_schedule_table()
        self.start_schedule_checker()
        self.setup_steamcmd_logging()

        # 设置窗口图标（如果有）
        try:
            self.setWindowIcon(QIcon("icon.ico"))
        except:
            pass

    def setup_steamcmd_logging(self):
        """设置SteamCMD日志连接"""
        if self.steam_manager:
            self.steam_manager.log_signal.connect(self.log)
            # 启动SteamCMD检查线程（如果需要）
            self.steam_manager.start()

    def init_ui(self):
        """初始化用户界面"""
        self.setWindowTitle("SCUM服务器定时重启管理器")
        self.setGeometry(100, 100, 900, 700)

        # 主布局
        central_widget = QWidget()
        self.setCentralWidget(central_widget)
        main_layout = QVBoxLayout(central_widget)
        main_layout.setContentsMargins(10, 10, 10, 10)
        main_layout.setSpacing(10)

        # 服务器信息区域
        server_info_group = QGroupBox("服务器信息")
        server_info_layout = QHBoxLayout(server_info_group)
        server_info_layout.setSpacing(10)

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
        schedule_layout.setSpacing(10)

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
        button_layout.setSpacing(10)

        self.remove_schedule_button = QPushButton("删除选中计划")
        self.remove_schedule_button.clicked.connect(self.remove_schedule)
        button_layout.addWidget(self.remove_schedule_button)

        button_layout.addStretch()

        schedule_layout.addLayout(button_layout)

        main_layout.addWidget(schedule_group, 2)

        # 日志区域
        log_group = QGroupBox("操作日志")
        log_layout = QVBoxLayout(log_group)
        log_layout.setSpacing(10)

        self.log_text = QTextEdit()
        self.log_text.setReadOnly(True)
        log_layout.addWidget(self.log_text)

        main_layout.addWidget(log_group, 1)

        # 应用样式
        self.apply_style_sheet()

    def apply_style_sheet(self):
        """应用统一样式表"""
        style = """
            QMainWindow {
                background-color: #f5f5f5;
            }
            QTableWidget {
                gridline-color: #e9ecef;
                selection-background-color: #bee5eb;
                background-color: white;
                border-radius: 4px;
                border: 1px solid #dee2e6;
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
                background-color: white;
                border: 1px solid #dee2e6;
                border-radius: 4px;
                font-family: Consolas, "Courier New", monospace;
                font-size: 12px;
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
        """
        self.setStyleSheet(style)

    def load_schedules(self):
        """从配置文件加载重启计划"""
        try:
            if os.path.exists(self.config_file):
                with open(self.config_file, 'r', encoding='utf-8') as f:
                    data = json.load(f)
                    self.server_path = data.get('server_path', self.server_path)
                    self.server_manager.server_path = self.server_path
                    self.server_path_label.setText(f"服务器路径: {self.server_path}")

                    # 加载计划并转换时间格式
                    schedules_data = data.get('schedules', [])
                    self.schedules = []
                    for s in schedules_data:
                        if 'time' in s and isinstance(s['time'], str):
                            try:
                                time_obj = datetime.datetime.strptime(s['time'], '%H:%M:%S').time()
                                s['time'] = time_obj
                            except ValueError:
                                self.log(f"忽略无效时间格式: {s.get('name', '未知计划')}")
                                continue
                        self.schedules.append(s)

                self.log(f"已从 {self.config_file} 加载 {len(self.schedules)} 个重启计划")
            else:
                self.log(f"配置文件 {self.config_file} 不存在，将创建新文件")
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
            name_item.setTextAlignment(Qt.AlignmentFlag.AlignHCenter | Qt.AlignmentFlag.AlignVCenter)  # 居中
            self.schedule_table.setItem(row, 0, name_item)

            # 时间
            time_str = schedule.get("time", datetime.datetime.now().time()).strftime("%H:%M:%S")
            time_item = QTableWidgetItem(time_str)
            time_item.setTextAlignment(Qt.AlignmentFlag.AlignHCenter | Qt.AlignmentFlag.AlignVCenter)  # 居中
            self.schedule_table.setItem(row, 1, time_item)

            # 重复
            repeat_types = {
                'none': '不重复',
                'daily': '每天',
                'weekly': '每周'
            }
            repeat_item = QTableWidgetItem(repeat_types.get(schedule.get("repeat", "none"), "未知"))
            repeat_item.setTextAlignment(Qt.AlignmentFlag.AlignHCenter | Qt.AlignmentFlag.AlignVCenter)  # 居中
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
            weekday_item.setTextAlignment(Qt.AlignmentFlag.AlignHCenter | Qt.AlignmentFlag.AlignVCenter)  # 居中
            self.schedule_table.setItem(row, 3, weekday_item)

            # 启用状态
            enabled = schedule.get("enabled", False)
            enabled_item = QTableWidgetItem("启用" if enabled else "禁用")
            enabled_item.setTextAlignment(Qt.AlignmentFlag.AlignHCenter | Qt.AlignmentFlag.AlignVCenter)  # 居中
            if enabled:
                enabled_item.setBackground(QColor(144, 238, 144))  # 浅绿色
            else:
                enabled_item.setBackground(QColor(255, 182, 193))  # 浅红色
            self.schedule_table.setItem(row, 4, enabled_item)

            # 操作按钮
            action_widget = QWidget()
            action_layout = QHBoxLayout(action_widget)
            action_layout.setContentsMargins(2, 2, 2, 2)
            action_layout.setSpacing(2)

            toggle_text = "禁用" if enabled else "启用"
            toggle_button = QPushButton(toggle_text)
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
        self.schedule_checker.next_run_updated.connect(self.update_next_run_label)
        self.schedule_checker.start()

    def update_server_status(self, status: str):
        """更新服务器状态标签"""
        self.server_status_label.setText(f"服务器状态: {status}")

        # 根据状态设置样式
        if status == "运行中":
            self.server_status_label.setStyleSheet("color: #28a745; font-weight: 500;")
        elif status == "已停止":
            self.server_status_label.setStyleSheet("color: #dc3545; font-weight: 500;")
        else:
            self.server_status_label.setStyleSheet("color: #ffc107; font-weight: 500;")

    def update_schedules(self, schedules: List[Dict[str, Any]]):
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
        self.log("正在检查服务器状态...")
        status = self.server_manager.get_server_status()
        self.log(f"服务器状态: {status}")
        self.update_server_status(status)

    def restart_server_now(self):
        """立即重启服务器"""
        if QMessageBox.question(self, "确认",
                                "确定要立即重启服务器吗?\n这将断开所有在线玩家连接。") != QMessageBox.Yes:
            return

        self.log("正在立即重启服务器...")
        success = self.server_manager.restart_server()
        status_text = "成功" if success else "失败"
        self.log(f"服务器重启 {status_text}")
        self.check_server_status()

    def add_schedule(self):
        """添加新的重启计划"""
        dialog = ScheduleDialog(self)
        dialog.schedule_added.connect(self.on_schedule_added)
        dialog.exec()

    def on_schedule_added(self, schedule: Dict[str, Any]):
        """处理新增计划"""
        self.schedules.append(schedule)
        self.update_schedules(self.schedules)
        self.log(f"已添加重启计划: {schedule['name']}")

    def remove_schedule(self, row: Optional[int] = None):
        """删除重启计划"""
        if row is not None:
            # 删除指定行
            self._remove_schedule_by_row(row)
        else:
            # 删除选中的行
            selected_rows = sorted(set(index.row() for index in self.schedule_table.selectedIndexes()),
                                   reverse=True)
            if not selected_rows:
                QMessageBox.information(self, "提示", "请先选择要删除的重启计划")
                return

            if QMessageBox.question(self, "确认",
                                    f"确定要删除选中的 {len(selected_rows)} 个重启计划吗?") != QMessageBox.Yes:
                return

            for row in selected_rows:
                self._remove_schedule_by_row(row)

    def _remove_schedule_by_row(self, row: int):
        """按行删除计划"""
        if 0 <= row < len(self.schedules):
            schedule_name = self.schedules[row]["name"]
            self.schedules.pop(row)
            self.update_schedules(self.schedules)
            self.log(f"已删除重启计划: {schedule_name}")

    def toggle_schedule(self, row: int):
        """切换重启计划的启用状态"""
        if 0 <= row < len(self.schedules):
            self.schedules[row]["enabled"] = not self.schedules[row]["enabled"]
            status = "启用" if self.schedules[row]["enabled"] else "禁用"
            self.log(f"已{status}重启计划: {self.schedules[row]['name']}")
            self.update_schedules(self.schedules)

    def edit_schedule(self, row: int):
        """编辑重启计划"""
        if 0 <= row < len(self.schedules):
            schedule = self.schedules[row].copy()
            dialog = ScheduleDialog(self, schedule)
            dialog.schedule_updated.connect(lambda s: self.on_schedule_updated(row, s))
            dialog.exec()

    def on_schedule_updated(self, row: int, updated_schedule: Dict[str, Any]):
        """处理更新后的计划"""
        if 0 <= row < len(self.schedules):
            self.schedules[row] = updated_schedule
            self.update_schedules(self.schedules)
            self.log(f"已更新重启计划: {updated_schedule['name']}")

    def log(self, message: str):
        """添加日志消息"""
        timestamp = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        log_entry = f"[{timestamp}] {message}"
        self.log_text.append(log_entry)
        self.log_text.verticalScrollBar().setValue(self.log_text.verticalScrollBar().maximum())

    def closeEvent(self, event):
        """窗口关闭事件"""
        self.log("正在关闭应用程序...")

        if self.schedule_checker and self.schedule_checker.isRunning():
            self.schedule_checker.stop()
            self.log("计划检查线程已停止")

        self.save_schedules()
        self.log(f"已保存 {len(self.schedules)} 个重启计划")

        if self.steam_manager:
            self.steam_manager.stop()
            self.log("SteamCMD管理器已停止")

        self.log("应用程序关闭完成")
        event.accept()


def win_start():
    """Windows启动入口"""
    app = QApplication(sys.argv)
    # 设置高DPI缩放
    app.setAttribute(Qt.AA_EnableHighDpiScaling, True)
    app.setAttribute(Qt.AA_UseHighDpiPixmaps, True)

    window = ScumServerScheduler()
    window.show()
    sys.exit(app.exec())


if __name__ == "__main__":

    win_start()
