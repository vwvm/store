from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, QTextEdit
import sys

from onlineTranslation_ui import Ui_Form
from apidemo.Translate import Translate


class MyWindow(QWidget, Ui_Form):
    def __init__(self):
        super().__init__()

        self.fromLanguage = 0

        self.setupUi(self)
        self.bind()

    def bind(self):
        self.pushButton.clicked.connect(lambda: self.translate())
        self.radioButton_auto.clicked.connect(lambda: self.set_from_language(0))
        self.radioButton_ch.clicked.connect(lambda: self.set_from_language(1))
        self.radioButton_en.clicked.connect(lambda: self.set_from_language(2))
        pass

    def set_from_language(self, language: int):
        self.fromLanguage = language

    def translate(self):
        # 获取输入的文本
        inputText = self.textEdit.toPlainText()

        res = Translate(inputText)

        print(res.get_translation())
        print(res.get_basic())
        print(res.get_translation())
        print(res.get_basic())
        self.textEdit_3.setPlainText(str(res.get_translation()))
        self.textEdit_4.setPlainText(str(res.get_basic()))


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = MyWindow()
    window.show()
    app.exec()
