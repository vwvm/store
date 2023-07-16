from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, \
    QTextEdit, QLabel
from PySide6.QtCore import Qt, QThread
import sys
from WorkThread import WorkThread


class MyWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.resize(800, 600)

        self.lb = QLabel("当前值为：0")
        self.lb.setAlignment(Qt.AlignmentFlag.AlignCenter)

        self.workThread = WorkThread()
        self.threadList = QThread()
        self.workThread.moveToThread(self.threadList)
        self.threadList.started.connect(self.workThread.work)
        self.threadList.finished.connect(lambda: print("finish"))
        self.threadList.start()

        self.mainLayout = QVBoxLayout()
        self.mainLayout.addWidget(self.lb)
        self.setLayout(self.mainLayout)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = MyWindow()
    window.show()
    app.exec()
