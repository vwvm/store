from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, \
    QTextEdit, QPushButton, QFileDialog, QMainWindow, QGridLayout, \
    QHBoxLayout, QLineEdit, QTableWidget, QHeaderView, QTableWidgetItem, QScrollArea, QLabel
from PySide6.QtCore import Qt, Slot, QEvent
from PySide6.QtGui import QGuiApplication, QPixmap, QAction, QColor, \
    QPalette, QResizeEvent
from qt_material import apply_stylesheet
import sys
import os
import pathlib

from NewSelectFolderWindow import NewSelectFolderWindow
from Window2 import Window2


class MyWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.resize(800, 600)
        self.setWindowTitle("文件自动备份")
        self.setWindowIcon(QPixmap("vwvmn.png"))
        self.setupMenuBar()
        self.bind_ui()
        # 获取用户目录
        self.folderName: str = str(pathlib.Path.home())

    def bind_ui(self):
        self.main_layout = QVBoxLayout()
        # 设置表
        self.table_widget = QTableWidget()
        self.table_widget.setColumnCount(4)
        self.table_widget.horizontalHeader().setSectionResizeMode(QHeaderView.ResizeMode.Stretch)
        self.table_widget.horizontalHeader().setSectionResizeMode(0, QHeaderView.ResizeMode.Interactive)
        self.table_widget.setHorizontalHeaderLabels(["序号", "监视路径", "输出路径", "状态"])

        push_button = QPushButton("添加项目")
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

        self.add_watch_main_widget.setStyleSheet("border: 1px solid green;")
        self.scroll_area.setWidgetResizable(True)  # 这条不加无法显示里面的控件
        # 更改布局为从上而下
        self.add_watch_main_layout.setAlignment(Qt.AlignTop)



        self.main_layout.addWidget(self.scroll_area)
        self.main_layout.addWidget(push_button)
        self.main_layout.addWidget(add_button)

        widget = QWidget()
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
    def path_list_slot(self, origin_path_list: list, target_path_list: list):
        """
        槽函数，接收子窗口的列表
        :param origin_path_list:
        :param target_path_list:
        """
        self.table_widget.setItem(0, 0, QTableWidgetItem(origin_path_list[0]))
        # 获取数据
        print(origin_path_list, target_path_list)
        # 添加到table
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


if __name__ == '__main__':
    app = QApplication(sys.argv)
    # apply_stylesheet(app, theme="dark_teal.xml")
    window = MyWindow()
    window.show()
    sys.exit(app.exec())
