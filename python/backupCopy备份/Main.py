# -*- coding: utf-8 -*-
from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, \
    QTextEdit, QPushButton, QFileDialog, QMainWindow, QGridLayout, \
    QHBoxLayout, QLineEdit, QTableWidget, QHeaderView, QTableWidgetItem, \
    QScrollArea, QLabel, QFrame
from PySide6.QtCore import Qt, Slot, QEvent
from PySide6.QtGui import QGuiApplication, QPixmap, QAction, QColor, \
    QPalette, QResizeEvent
from qt_material import apply_stylesheet
import sys
import os
import pathlib

from NewSelectFolderWindow import NewSelectFolderWindow
from Window2 import Window2
import logging
from Watch import StartWatch


class MyWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.resize(800, 600)
        self.setWindowTitle("文件自动备份")
        self.setWindowIcon(QPixmap("vwvmn.png"))
        self.setupMenuBar()
        self.main_ui()
        self.main_data()
        # 获取用户目录
        self.folderName: str = str(pathlib.Path.home())

    def main_data(self):
        self.sequence_number = 0

    def main_ui(self):
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

    def add_watch_widget(self):
        btn = QPushButton("ti")
        widget = QWidget()
        widget.resize(12, 200)
        self.add_watch_main_layout.addWidget(btn)
        self.add_watch_main_layout.addWidget(widget)
        pass

    def resizeEvent(self, event: QResizeEvent) -> None:
        """
        窗口调整大小事件
        :param event:
        """
        self.table_widget.setColumnWidth(0, int(event.size().width() / 6))
        pass

    def setupMenuBar(self):
        layout = QGridLayout()
        # 添加文件菜单项
        fileMenu = self.menuBar().addMenu(QPixmap(":/icon/ic_start"), "文件")
        fileMenu2 = self.menuBar().addMenu("编辑")
        fileMenu3 = self.menuBar().addMenu("帮助")
        # 文件菜单项下的动作列表
        newAct = QAction(QPixmap(":/icon/ic_start"), "新建", self)
        fileMenu.addAction(newAct)

        saveAct = QAction(QPixmap(":/icon/ic_start"), "保存", self)
        fileMenu.addAction(saveAct)

        exitAct = QAction(QPixmap(":/icon/ic_start"), "退出", self)
        fileMenu.addAction(exitAct)

        # 自定义快捷键
        exitAct.setShortcut('Ctrl+Q')
        # 状态栏提示
        exitAct.setStatusTip('Exit application')
        # 利用信号槽机制,将退出动作和窗口的关闭函数联系起来
        exitAct.triggered.connect(self.close)

    def open_the_folder(self):
        pass

    def select_folder(self):
        self.folderName = QFileDialog.getExistingDirectory(self, "选择文件夹", self.folderName)
        print(self.folderName)

    def open_the_new_folder_selection_window(self):
        print("open")
        window2 = Window2()
        window2.list_signal.connect(self.path_list_slot)
        window2.show()

    @Slot(list, list)
    def path_list_slot(self, origin_path_list: list[str], target_path_list: list[str]):
        """
        槽函数，接收子窗口的列表
        :param origin_path_list:
        :param target_path_list:
        """
        # 校验是否存在嵌套文件夹
        pass
        # 绘制到主屏幕
        new_widget = QWidget()
        new_widget.setStyleSheet(".QWidget{border: 2px solid green;}")
        new_main_layout = QHBoxLayout()
        self.sequence_number += 1
        # 设置序号
        new_seq_num = QLabel(str(self.sequence_number))
        new_seq_num.setStyleSheet(".QLabel{border: 1px solid greenQLabel}")
        new_seq_num.resize(20, 0)
        new_seq_num.setMaximumWidth(50)
        new_origin_path_layout = QVBoxLayout()
        new_target_path_layout = QVBoxLayout()
        for i in origin_path_list:
            temp_frame = QFrame()
            temp_frame.setStyleSheet(".QFrame{border: 1px solid green;}")
            temp_label = QLabel(i)
            temp_label.setMinimumHeight(30)
            temp_button = QPushButton("×")
            temp_button.setFixedSize(24, 24)  # 设置按钮为圆形
            temp_button.setStyleSheet(
                "QPushButton { border-radius: 12px; background-color: #CD5C5C; color: white; font-size: 20px; }"
            )
            temp_layout = QHBoxLayout()
            temp_layout.addWidget(temp_label)
            temp_layout.addWidget(temp_button)
            temp_frame.setLayout(temp_layout)
            new_origin_path_layout.addWidget(temp_frame)
        for i in target_path_list:
            temp_label = QLabel(i)
            temp_label.setStyleSheet("border: 1px solid green")
            temp_label.setMinimumHeight(30)
            new_target_path_layout.addWidget(temp_label)

        new_widget.setLayout(new_main_layout)
        new_main_layout.addWidget(new_seq_num)
        new_main_layout.addLayout(new_origin_path_layout)
        new_main_layout.addLayout(new_target_path_layout)

        self.add_watch_main_layout.addWidget(new_widget)
        # 启动监视,迭代
        for origin_path in origin_path_list:
            for target_path in target_path_list:
                StartWatch(origin_path, target_path)

        # 获取数据
        print(origin_path_list, target_path_list)

        pass


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
