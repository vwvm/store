import pathlib

from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, \
    QTextEdit, QMainWindow, QLineEdit, QPushButton, QFileDialog
from qt_material import apply_stylesheet
from PySide6.QtGui import QIcon
import sys

from MainUI import Ui_MainWindow


class MyWindow(QMainWindow, Ui_MainWindow):
    def __init__(self):
        super().__init__()
        self.setupUi(self)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    # apply_stylesheet(app, theme="dark_teal.xml")
    window = MyWindow()
    window.show()
    app.exec()
