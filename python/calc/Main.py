from PySide6.QtWidgets import QApplication, QWidget
import sys

from calc import Ui_Form


class MyWindow(QWidget, Ui_Form):
    def __init__(self):
        super().__init__()

        self.setupUi(self)

        self.result = ''

        self.bind()

    def bind(self):
        self.pushButton_0.clicked.connect(lambda: self.add_number("0"))
        self.pushButton_1.clicked.connect(lambda: self.add_number("1"))
        self.pushButton_2.clicked.connect(lambda: self.add_number("2"))
        self.pushButton_3.clicked.connect(lambda: self.add_number("3"))
        self.pushButton_4.clicked.connect(lambda: self.add_number("4"))
        self.pushButton_5.clicked.connect(lambda: self.add_number("5"))
        self.pushButton_6.clicked.connect(lambda: self.add_number("6"))
        self.pushButton_7.clicked.connect(lambda: self.add_number("7"))
        self.pushButton_8.clicked.connect(lambda: self.add_number("8"))
        self.pushButton_9.clicked.connect(lambda: self.add_number("9"))
        self.pushButton_10.clicked.connect(lambda: self.add_number("+"))
        self.pushButton_11.clicked.connect(lambda: self.add_number("-"))
        self.pushButton_12.clicked.connect(lambda: self.add_number("*"))
        self.pushButton_13.clicked.connect(lambda: self.add_number("/"))
        self.pushButton_14.clicked.connect(lambda: self.add_number("."))
        self.pushButton_left.clicked.connect(lambda: self.add_number("("))
        self.pushButton_right.clicked.connect(lambda: self.add_number(")"))
        self.pushButton_enter.clicked.connect(lambda: self.equal())
        self.pushButton_backspacing.clicked.connect(lambda: self.equal())
        self.pushButton_clear.clicked.connect(lambda: self.equal())


    def add_number(self, number):
        self.lineEdit.clear()
        self.result += number
        self.lineEdit.setText(self.result)

    def equal(self):
        number_result = eval(self.result)
        self.lineEdit.setText(str(number_result))


if __name__ == '__main__':
    app = QApplication(sys.argv)

    window = MyWindow()
    window.show()

    app.exec()
