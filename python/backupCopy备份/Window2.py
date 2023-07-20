import pathlib

from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, \
    QTextEdit, QMainWindow, QLineEdit, QPushButton, QFileDialog
from qt_material import apply_stylesheet
from PySide6.QtGui import QIcon
from PySide6.QtCore import Signal
import sys

from MainUI import Ui_MainWindow


class Window2(QMainWindow, Ui_MainWindow):
    list_signal = Signal(list, list)

    def __init__(self):
        super().__init__()
        self.setupUi(self)

        self.ok_button.clicked.connect(self.ok_button_clicked)

    def ok_button_clicked(self):


        left_list = []
        right_list = []
        # 遍历
        for i in range(self.listWidget.count()):
            left_list.append(self.listWidget.item(i).text())

        for i in range(self.listWidget_2.count()):
            right_list.append(self.listWidget_2.item(i).text())

        self.list_signal.emit(left_list, right_list)
        # 写入到消息
        pass


if __name__ == '__main__':
    print(type({"123": [1, 2, 3]}))

    app = QApplication(sys.argv)
    # apply_stylesheet(app, theme="dark_teal.xml")
    window = Window2()
    window.show()
    app.exec()
