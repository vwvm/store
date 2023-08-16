# -*- coding: utf-8 -*-
from functools import partial

from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, \
    QTextEdit, QPushButton, QFileDialog, QMainWindow, QGridLayout, \
    QHBoxLayout, QLineEdit, QTableWidget, QHeaderView, QTableWidgetItem, \
    QScrollArea, QLabel, QFrame, QMessageBox
from PySide6.QtCore import Qt, Slot, QEvent, QTimer
from PySide6.QtGui import QGuiApplication, QPixmap, QAction, QColor, \
    QPalette, QResizeEvent, QCloseEvent
import sys
import pathlib

from Window2 import Window2
import logging
from Watch import StartWatch
from ReadWriteDB import read_data, save_data, examine_data, clean_data


class MyWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.init_data()
        self.init_ui()

        # 如果没有自动创建文件数据库
        examine_data()
        # 读取配置
        self.read_config()

    def closeEvent(self, event: QCloseEvent) -> None:
        """
        退出时操作
        :param event:
        """
        reply = QMessageBox.question(self, "Exit", "是否保存当前配置?",
                                     QMessageBox.Yes | QMessageBox.No, QMessageBox.No)
        if reply == QMessageBox.Yes:
            self.save_config()
        else:
            return



    def init_data(self):
        self.sequence_number = 0

        # 获取用户目录
        self.folderName: str = str(pathlib.Path.home())

        # 用于存储，每一组的序号
        self.seq_frame_list = []

        # 配置文件列表
        self.config_List: list[list[int, list[str], list[str]]] = []

    def init_ui(self):

        self.resize(800, 600)
        self.setWindowTitle("文件自动备份")
        self.setWindowIcon(QPixmap("vwvmn.png"))
        self.setup_menu_bar()

        self.main_layout = QVBoxLayout()
        # 设置表
        self.table_widget = QTableWidget()
        self.table_widget.setColumnCount(4)
        self.table_widget.horizontalHeader().setSectionResizeMode(QHeaderView.ResizeMode.Stretch)
        self.table_widget.horizontalHeader().setSectionResizeMode(0, QHeaderView.ResizeMode.Interactive)
        self.table_widget.setHorizontalHeaderLabels(["序号", "监视路径", "输出路径", "状态"])

        push_button = QPushButton("添加项目")
        push_button.setStyleSheet(
            "QPushButton{"
            "background-color: #00a1d6;"
            "border-radius:4px"
            "}"
            "QPushButton:hover{background: #00b5e5;}"
        )
        push_button.clicked.connect(self.open_the_new_folder_selection_window)

        add_button = QPushButton("添加")
        add_button.clicked.connect(self.add_watch_widget)

        # 创建一个 QScrollArea 作为滚动区域
        self.scroll_area = QScrollArea()
        self.add_watch_main_widget = QWidget()
        # 设置布局
        self.add_watch_main_layout = QVBoxLayout()
        self.add_watch_main_widget.setLayout(self.add_watch_main_layout)
        self.scroll_area.setWidget(self.add_watch_main_widget)

        self.add_watch_main_widget.setStyleSheet(".QWidget{border: 1px solid black;}")
        self.scroll_area.setWidgetResizable(True)  # 这条不加无法显示里面的控件
        # 更改布局为从上而下
        self.add_watch_main_layout.setAlignment(Qt.AlignTop)

        self.main_layout.addWidget(self.scroll_area)
        self.main_layout.addWidget(push_button)
        self.main_layout.addWidget(add_button)

        widget = QWidget()
        widget.setStyleSheet("font-size:16px")
        widget.setLayout(self.main_layout)
        self.setCentralWidget(widget)
        pass

    def seq_num_sort(self):
        """
        重新排序序号
        """
        children_widget: list[QLabel] = self.add_watch_main_widget.findChildren(QLabel, "seq_label")
        # children_widget: list[QWidget] = [child for child in children_widget if type(child) is QLabel]
        temp_number = 0
        for i in children_widget:
            temp_number += 1
            i.setText(str(temp_number))

    def add_watch_widget(self):
        """
        重新排序序号
        """
        self.seq_num_sort()
        pass


    def resizeEvent(self, event: QResizeEvent) -> None:
        """
        窗口调整大小事件
        :param event:
        """
        self.table_widget.setColumnWidth(0, int(event.size().width() / 6))
        pass

    def setup_menu_bar(self):
        """
        菜单项设置
        """
        layout = QGridLayout()
        # 添加文件菜单项
        fileMenu = self.menuBar().addMenu(QPixmap(":/icon/ic_start"), "文件")
        fileMenu2 = self.menuBar().addMenu("编辑")
        fileMenu3 = self.menuBar().addMenu("帮助")
        # 文件菜单项下的动作列表
        newAct = QAction(QPixmap(":/icon/ic_start"), "新建", self)
        fileMenu.addAction(newAct)

        save_act = QAction(QPixmap(":/icon/ic_start"), "保存", self)
        save_act.triggered.connect(self.save_config)
        fileMenu.addAction(save_act)

        exitAct = QAction(QPixmap(":/icon/ic_start"), "退出", self)
        fileMenu.addAction(exitAct)

        # 自定义快捷键
        exitAct.setShortcut('Ctrl+Q')
        # 状态栏提示
        exitAct.setStatusTip('Exit application')
        # 利用信号槽机制,将退出动作和窗口的关闭函数联系起来
        exitAct.triggered.connect(self.close)

    def save_config(self):
        """
        保存配置文件
        """
        # 清除原有配置
        clean_data()
        # 保存
        save_data(self.config_List)
        self.show_success_message("操作成功")
        pass

    def read_config(self):
        """
        读取配置文件
        """
        self.config_List = read_data()
        print(len(self.config_List))
        if len(self.config_List) == 0:
            return
        for i in self.config_List:
            self.show_path_list_widgets(i[1], i[2])

    def open_the_folder(self):
        pass

    def select_folder(self):
        self.folderName = QFileDialog.getExistingDirectory(self, "选择文件夹", self.folderName)
        print(self.folderName)

    def open_the_new_folder_selection_window(self):
        """
        打开新的文件夹选择窗口
        """
        print("open")
        window2 = Window2()
        window2.list_signal.connect(self.path_list_slot)
        window2.show()

    def show_path_list_widgets(self, origin_path_list: list[str], target_path_list: list[str]):
        """
        显示主界面列表
        :param origin_path_list:
        :param target_path_list:
        """
        pass

        # 校验是否存在嵌套文件夹
        pass

        start_watch_list = []
        # 启动监视,迭代
        for origin_path in origin_path_list:
            for target_path in target_path_list:
                start_watch = StartWatch(origin_path, target_path)
                start_watch_list.append(start_watch)

        # 最大的框
        new_widget = QWidget()
        new_widget.setStyleSheet(".QWidget{border: 2px solid green;}")
        new_main_layout = QHBoxLayout()
        # 主要的序号
        self.sequence_number += 1

        # 设置序号
        new_seq_frame = QFrame()
        new_seq_frame.setStyleSheet(".QFrame{border: 1px solid green;}")

        new_seq_num = QLabel(str(self.sequence_number))
        new_seq_num.setObjectName("seq_label")
        new_seq_num.resize(20, 0)
        new_seq_num.setMaximumWidth(50)

        # 序号的叉按钮
        new_seq_fork_button = QPushButton("×")
        new_seq_fork_button.setFixedSize(24, 24)  # 设置按钮为圆形
        new_seq_fork_button.setStyleSheet(
            "QPushButton { border-radius: 12px; background-color: #CD5C5C; color: white; font-size: 18px; }"
        )
        new_seq_fork_button.clicked.connect(lambda: self.show_warning_box(start_watch_list, "-1", "-1", new_widget))

        temp_layout = QHBoxLayout()
        temp_layout.addWidget(new_seq_num)
        temp_layout.addWidget(new_seq_fork_button)
        new_seq_frame.setLayout(temp_layout)

        # 左右竖排
        new_origin_path_layout = QVBoxLayout()
        new_target_path_layout = QVBoxLayout()

        for origin_path in origin_path_list:
            temp_frame = QFrame()
            temp_frame.setStyleSheet(".QFrame{border: 1px solid green;}")

            temp_label = QLabel(origin_path)
            temp_label.setMinimumHeight(30)

            # 临时的叉按钮
            temp_button = QPushButton("×")
            temp_button.setFixedSize(24, 24)  # 设置按钮为圆形
            temp_button.setStyleSheet(
                "QPushButton { border-radius: 12px; background-color: #CD5C5C; color: white; font-size: 18px; }"
            )

            ## 下面的无法固定参数，待处理
            callback_with = partial(self.show_warning_box, start_watch_list, origin_path, "-1", temp_frame)
            # temp_button.clicked.connect(lambda: self.show_warning_box(start_watch_list, origin_path, "-1", temp_frame))
            temp_button.clicked.connect(callback_with)

            temp_layout = QHBoxLayout()
            temp_layout.addWidget(temp_label)
            temp_layout.addWidget(temp_button)
            temp_frame.setLayout(temp_layout)
            new_origin_path_layout.addWidget(temp_frame)

        for target_path in target_path_list:
            temp_frame = QFrame()
            temp_frame.setStyleSheet(".QFrame{border: 1px solid green;}")

            temp_label = QLabel(target_path)
            temp_label.setMinimumHeight(30)

            # 临时的叉按钮
            temp_button = QPushButton("×")
            temp_button.setFixedSize(24, 24)  # 设置按钮为圆形
            temp_button.setStyleSheet(
                "QPushButton { border-radius: 12px; background-color: #CD5C5C; color: white; font-size: 18px; }"
            )
            callback_with = partial(self.show_warning_box, start_watch_list, "-1", target_path, temp_frame)
            temp_button.clicked.connect(callback_with)

            temp_layout = QHBoxLayout()
            temp_layout.addWidget(temp_label)
            temp_layout.addWidget(temp_button)
            temp_frame.setLayout(temp_layout)
            new_target_path_layout.addWidget(temp_frame)

        new_widget.setLayout(new_main_layout)
        new_main_layout.addWidget(new_seq_frame)
        new_main_layout.addLayout(new_origin_path_layout)
        new_main_layout.addLayout(new_target_path_layout)

        self.add_watch_main_layout.addWidget(new_widget)

    @Slot(list, list)
    def path_list_slot(self, origin_path_list: list[str], target_path_list: list[str]):
        """
        槽函数，接收子窗口的列表
        :param origin_path_list:
        :param target_path_list:
        """
        # 显示部件
        self.show_path_list_widgets(origin_path_list, target_path_list)

        # 存到配置列表中
        self.config_List.append([self.sequence_number, origin_path_list, target_path_list])
        pass

    def show_success_message(self, msg: str = "操作成功"):
        """
        :type msg: str
        :param msg: 提示信息
        """
        message_box = QMessageBox(self)
        message_box.setText(msg)
        message_box.setWindowTitle('Success')
        message_box.setIcon(QMessageBox.Information)
        message_box.setStandardButtons(QMessageBox.Ok)
        message_box.setDefaultButton(QMessageBox.Ok)

        # 使用 QTimer 在3秒后关闭提示框
        timer = QTimer(self)
        timer.timeout.connect(message_box.close)
        timer.start(3000)  # 3000 毫秒即 3 秒

        message_box.exec()

    def show_warning_box(self, start_watch_list: list[StartWatch], origin_path: str, target_path: str, widget: QWidget):
        """
        todo 传入列表,并不同步,可能会出现问题
        显示警告提示框
        同时删除组件
        :param widget:
        :param start_watch_list: 线程列表
        :param origin_path: 源路径
        :param target_path: 目标路径
        """
        reply = QMessageBox.warning(self, "警告", "确定要执行此操作吗？", QMessageBox.Yes | QMessageBox.No,
                                    QMessageBox.No)
        list_size = len(start_watch_list)
        if list_size <= 0:
            return
        if reply == QMessageBox.Yes:
            # "用户点击了确定按钮"
            # 删除组件
            widget.deleteLater()
            # 停止监视
            if origin_path != "-1":
                for i in range(list_size - 1, -1, -1):
                    if start_watch_list[i].get_origin_path() == origin_path:
                        start_watch_list[i].thread_stop()
                        start_watch_list.pop(i)
                return 0
            if target_path != "-1":
                for i in range(list_size - 1, -1, -1):
                    if start_watch_list[i].get_target_path() == target_path:
                        start_watch_list[i].thread_stop()
                        start_watch_list.pop(i)
                return 0
            QTimer.singleShot(50, self.seq_num_sort)

            # 停止全部
            if target_path == origin_path:
                for i in start_watch_list:
                    i.thread_stop()
        else:
            print("用户点击了取消按钮")



class Color(QWidget):

    def __init__(self, color):
        super(Color, self).__init__()
        """将底板颜色设置为可以手动调节"""
        self.setAutoFillBackground(True)
        """获取界面底板"""
        palette = self.palette()
        """设置底板颜色"""
        palette.setColor(QPalette.Window, QColor(color))
        """将新底板代替默认值"""
        self.setPalette(palette)


def logging_config():
    """
    设置打印日志的级别，level级别以上的日志会打印出
    level=logging.DEBUG 、INFO 、WARNING、ERROR、CRITICAL
    此处进行Logging.basicConfig() 设置，后面设置无效
    format 设置输出格式，具体如下
        %(levelno)s 日志级别
        %(levelname)s 日志级别名称
        %(pathname)s 当前程序的路径
        %(filename)s 当前程序名称
        %(funcName)s 日志所属的当前函数
        %(lineno)d 日志的当前行号
        %(asctime)s 日志的时间
        %(thread)d 线程的ID
        %(threadName)s 线程的名称
        %(process)d 进程的ID
        %(message)s 日志的信息
    :return:
    """
    logging.basicConfig(filename='log.txt',
                        format='%(asctime)s - %(name)s - %(levelname)s - %(message)s-%(funcName)s',
                        level=logging.INFO)


if __name__ == '__main__':
    logging_config()
    app = QApplication(sys.argv)
    # apply_stylesheet(app, theme="dark_teal.xml")
    window = MyWindow()
    window.show()
    sys.exit(app.exec())
