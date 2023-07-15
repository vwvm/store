from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, QTextEdit
import sys


class MyWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.resize(800, 600)

        self.mainLayout = QVBoxLayout()
        self.setLayout(self.mainLayout)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = MyWindow()
    window.show()
    app.exec()
