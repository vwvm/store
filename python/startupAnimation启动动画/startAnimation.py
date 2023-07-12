from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, \
    QTextEdit, QLabel
from PySide6.QtGui import QFont
from PySide6.QtCore import Qt, QTimer

import sys


class MyWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.resize(400, 300)

        self.lbWait = QLabel("加载中...")
        self.lbWait.setFont(QFont("微软雅黑", 50))
        self.lbWait.setAlignment(Qt.AlignmentFlag.AlignCenter)

        self.mainLayout = QVBoxLayout()
        self.mainLayout.addWidget(self.lbWait)
        self.setLayout(self.mainLayout)

        QTimer.singleShot(3000, self.openMainWindow)

    def openMainWindow(self):
        self.close()
        self.mainWindow = MainWindow()
        self.mainWindow.show()


class MainWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.resize(800, 600)
        self.setWindowTitle("主窗口")

        self.lbWelcome = QLabel("欢迎使用本软件")
        self.lbWelcome.setFont(QFont("微软雅黑", 50))
        self.lbWelcome.setAlignment(Qt.AlignmentFlag.AlignCenter)

        self.mainLayout = QVBoxLayout()
        self.mainLayout.addWidget(self.lbWelcome)
        self.setLayout(self.mainLayout)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = MyWindow()
    window.show()
    app.exec()
