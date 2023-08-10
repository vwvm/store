from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, \
    QTextEdit, QLabel, QLineEdit
from PySide6.QtGui import QFont, QAction
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
        # 执行一次

        QTimer.singleShot(1000, self.openMainWindow)

    def openMainWindow(self):
        self.close()
        self.mainWindow = MainWindow()
        self.mainWindow.show()


class MainWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.resize(800, 600)
        self.setWindowTitle("主窗口")

        self.setContextMenuPolicy(Qt.ContextMenuPolicy.ActionsContextMenu)
        self.openFile = QAction("打开文件")
        self.closeFile = QAction("关闭文件")
        self.addActions([self.openFile, self.closeFile])

        self.lienEdit1 = QLineEdit()
        self.lienEdit2 = QLineEdit()
        self.lienEdit1.setContextMenuPolicy(Qt.ContextMenuPolicy.ActionsContextMenu)
        self.lienEdit2.setContextMenuPolicy(Qt.ContextMenuPolicy.ActionsContextMenu)
        self.sendValue = QAction("发送信息")
        self.showValue = QAction("接收信息")
        self.lienEdit1.addActions([self.sendValue])
        self.lienEdit2.addActions([self.showValue])

        self.lbWelcome = QLabel("欢迎使用本软件")
        self.lbWelcome.setFont(QFont("微软雅黑", 50))
        self.lbWelcome.setAlignment(Qt.AlignmentFlag.AlignCenter)

        self.mainLayout = QVBoxLayout()
        self.mainLayout.addWidget(self.lbWelcome)
        self.mainLayout.addWidget(self.lienEdit1)
        self.mainLayout.addWidget(self.lienEdit2)
        self.setLayout(self.mainLayout)
        self.bind()

    def bind(self):
        self.openFile.triggered.connect(lambda: print("打开文件"))
        self.closeFile.triggered.connect(lambda: print("打开文件"))
        self.sendValue.triggered.connect(lambda: self.lienEdit2.setText(self.lienEdit1.text()))
        self.showValue.triggered.connect(lambda: print(self.lienEdit2.text()))


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = MyWindow()
    window.show()
    app.exec()
