from PySide6.QtWidgets import QApplication, QWidget
from Converter_ui import Ui_Form
import sys


class MyWindow(QWidget, Ui_Form):
    def __init__(self):
        super().__init__()
        self.setupUi(Ui_Form)

        self.lengthVar = {'米': 100, '千米': 100000, '厘米': 1, "分米": 10}
        self.weightVar = {'克': 1, '千克': 1000, "斤": 500}

        self.TypeDice = {"长度": self.lengthVar, "质量": self.weightVar}

    def bind(self):

        pass

    def calc(self):
        # 获取第一个输入框的值
        value = self.lineEdit_one.text()
        if value == '':
            return
        current_type = self.lineEdit_one.currentText()
        trans_type = self.lineEdit_two.currentText()

        calculate = float(value) * self.TypeDice[current_type][trans_type]
        result = calculate / self.TypeDice[trans_type][current_type]

        pass


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = MyWindow()
    window.show()
    app.exec()
