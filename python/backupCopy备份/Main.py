from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, \
    QTextEdit, QPushButton, QFileDialog, QMainWindow, QGridLayout, \
    QHBoxLayout, QLineEdit
from PySide6.QtCore import Qt, Slot
from PySide6.QtGui import QGuiApplication, QPixmap, QAction, QColor, QPalette
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
        self.bind()
        self.folderName: str = str(pathlib.Path.home())

    def bind(self):
        layout_h = QVBoxLayout()

        push_button = QPushButton("添加项目")
        push_button.clicked.connect(self.open_the_new_folder_selection_window)

        self.qLineEdit = QLineEdit()

        layout_h.addWidget(push_button)
        layout_h.addWidget(self.qLineEdit)

        widget = QWidget()
        widget.setLayout(layout_h)
        self.setCentralWidget(widget)
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


if __name__ == '__main__':
    app = QApplication(sys.argv)
    # apply_stylesheet(app, theme="dark_teal.xml")
    window = MyWindow()
    window.show()
    sys.exit(app.exec())
