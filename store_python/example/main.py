from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, \
    QTextEdit, QMainWindow
from qt_material import apply_stylesheet
import sys


class MyWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.resize(800, 600)

        self.mainLayout = QVBoxLayout()
        self.setLayout(self.mainLayout)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    apply_stylesheet(app, theme="dark_teal.xml")
    window = MyWindow()
    window.show()
    app.exec()
