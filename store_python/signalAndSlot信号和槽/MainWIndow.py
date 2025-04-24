# -*- coding:utf-8 -*-
################################################################################
##
# # Design Main Windows
# # BY: 扣脚大汉
# # PROJECT MADE WITH: Qt Designer and PySide6
# # V: 1.0.0
##
################################################################################
from PySide6.QtWidgets import QMainWindow
from PySide6 import QtWidgets
import sys
from ChildWIndow import SubWindow  # 导入子窗口
from ui_main import Ui_MainWindow  # 导入主窗口的UI代码


class MainWindow(QMainWindow):
    def __init__(self):
        QMainWindow.__init__(self)
        self.ui = Ui_MainWindow()
        self.ui.setupUi(self)
        self.intiUi()

    # # 初始化按钮事件
    ########################################################################
    def intiUi(self):
        self.ui.open_subwin.clicked.connect(self.openSubwin)
        self.sub = SubWindow()
        self.sub.sub_Signal.connect(self.getSubwininfo)  # # 子窗口信号连接主窗口的槽函数

    # # 打开子窗口
    ########################################################################
    def openSubwin(self):
        self.sub.move(1200, 310)
        self.sub.show()

    # # 主窗口接收到子窗口发来的函数后，在主窗口显示出来
    ########################################################################
    def getSubwininfo(self, subarg):  # # 主窗口的槽函数
        print(subarg)
        self.ui.recevice_text.setText(subarg)


if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    root = MainWindow()
    root.show()
    sys.exit(app.exec())
