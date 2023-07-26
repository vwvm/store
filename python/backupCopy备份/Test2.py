import sys
import random
from PySide6.QtWidgets import QApplication, QMainWindow, QLabel, QLineEdit, QPushButton, QVBoxLayout, QWidget

class ArithmeticApp(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("小学算术题程序")
        self.resize(400, 300)
        self.init_ui()

    def init_ui(self):
        self.central_widget = QWidget()
        self.setCentralWidget(self.central_widget)

        self.layout = QVBoxLayout()

        self.label = QLabel()
        self.layout.addWidget(self.label)

        self.answer_input = QLineEdit()
        self.layout.addWidget(self.answer_input)

        self.check_button = QPushButton("检查答案")
        self.check_button.clicked.connect(self.check_answer)
        self.layout.addWidget(self.check_button)

        self.new_question_button = QPushButton("下一题")
        self.new_question_button.clicked.connect(self.generate_question)
        self.layout.addWidget(self.new_question_button)

        self.central_widget.setLayout(self.layout)

        self.generate_question()

    def generate_question(self):
        a = random.randint(1, 20)
        b = random.randint(1, 20)
        operator = random.choice(['+', '-'])

        if operator == '+':
            self.answer = a + b
        else:
            self.answer = a - b

        self.label.setText(f"{a} {operator} {b} = ?")
        self.answer_input.clear()

    def check_answer(self):
        user_answer = self.answer_input.text()
        try:
            user_answer = int(user_answer)
            if user_answer == self.answer:
                self.label.setText("回答正确！")
            else:
                self.label.setText(f"回答错误，正确答案是 {self.answer}")
        except ValueError:
            self.label.setText("请输入一个整数作为答案！")

if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = ArithmeticApp()
    window.show()
    sys.exit(app.exec())
