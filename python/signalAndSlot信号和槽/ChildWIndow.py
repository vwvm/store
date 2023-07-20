# -*- coding:utf-8 -*-
################################################################################
##
# # Design Child Windows
# # BY: 扣脚大汉
# # PROJECT MADE WITH: Qt Designer and PySide6
# # V: 1.0.0
##
################################################################################

from PySide6.QtWidgets import QMainWindow, QDialog, QMessageBox, QButtonGroup, QLineEdit, QTabWidget
from PySide6.QtCore import Signal, QUrl
from ChildWindowUi import Ui_Dialog  # 导入子窗口的UI代码

class SubWindow(QDialog):
    sub_Signal = Signal(str)

    def __init__(self):
        QDialog.__init__(self)
        self.ui = Ui_Dialog()
        self.ui.setupUi(self)
        self.setWindowTitle("扣脚小妹")
        self.initUI()

    # # 初始化子窗口按钮事件
    ########################################################################
    def initUI(self):
        self.ui.sub_sent_btn.clicked.connect(self.sentToMain)

    # # 子向主发送内容
    ########################################################################
    def sentToMain(self):
        text = self.ui.sub_sent_text.text()
        self.sub_Signal.emit(text)
