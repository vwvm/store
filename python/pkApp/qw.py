#!/usr/bin/env python
# coding=utf-8
import sys

from PySide6.QtWidgets import QApplication, QMainWindow


class App(QMainWindow):

    def __init__(self):
        super().__init__()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    temp = App()
    temp.show()
    sys.exit(app.exec())
    pass
