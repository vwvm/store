import sys
import random

from PySide6.QtCore import Qt, QTimer
from PySide6.QtGui import QKeyEvent
from PySide6.QtWidgets import QApplication, QMainWindow, QLabel, QLineEdit, QPushButton, QVBoxLayout, QWidget


class MathQuizApp(QMainWindow):
    def __init__(self):
        super().__init__()
        self.init_ui()

    def init_ui(self):
        self.setWindowTitle("做题软件")
        self.resize(200, 150)
        self.central_widget = QWidget()
        self.setCentralWidget(self.central_widget)
        self.layout = QVBoxLayout()
        self.question_label = QLabel()
        self.layout.addWidget(self.question_label)
        self.answer_input = QLineEdit()
        self.layout.addWidget(self.answer_input)
        self.submit_button = QPushButton("提交答案")
        self.submit_button.clicked.connect(self.check_answer)
        self.layout.addWidget(self.submit_button)
        self.central_widget.setLayout(self.layout)
        self.generate_question()

    def generate_question(self):
        a = random.randint(1, 100)
        b = random.randint(1, 100)
        operator = random.choice(['+', '-'])
        if operator == '+':
            self.answer = a + b
        else:
            self.answer = a - b
        self.question_label.setText(f"{a} {operator} {b} = ?")
        self.answer_input.clear()

    def check_answer(self):
        user_answer = self.answer_input.text()
        try:
            user_answer = int(user_answer)
            if user_answer == self.answer:
                self.question_label.setText("回答正确！")

            else:
                self.question_label.setText(f"回答错误，正确答案是 {self.answer}")
        except ValueError:
            self.question_label.setText("请输入一个整数作为答案！")

        QTimer.singleShot(1000, self.generate_question)


    def keyPressEvent(self, event: QKeyEvent) -> None:
        print(event.key())
        print(Qt.Key.Key_Return)
        if event.key() == int(Qt.Key.Key_Enter):
            print("按下了enter")

    def keyReleaseEvent(self, event: QKeyEvent) -> None:
        if event.key() == int(Qt.Key.Key_Enter):
            print("放开了enter")


if __name__ == "__main__":
    app = QApplication(sys.argv)
    math_quiz_app = MathQuizApp()
    math_quiz_app.show()
    sys.exit(app.exec())
