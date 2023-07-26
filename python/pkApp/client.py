import sys
import socket
import threading

from PySide6.QtWidgets import QApplication, QMainWindow, QLabel, QVBoxLayout, QWidget, QLineEdit, QPushButton

class Client(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("局域网计算题比赛 - 客户端")
        self.init_ui()

    def init_ui(self):
        self.central_widget = QWidget()
        self.setCentralWidget(self.central_widget)

        self.layout = QVBoxLayout()

        self.question_label = QLabel()
        self.layout.addWidget(self.question_label)

        self.answer_input = QLineEdit()
        self.layout.addWidget(self.answer_input)

        self.submit_button = QPushButton("提交答案")
        self.submit_button.clicked.connect(self.submit_answer)
        self.layout.addWidget(self.submit_button)

        self.central_widget.setLayout(self.layout)

        self.tcp_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.tcp_socket.connect(('localhost', 12345))

        self.receive_thread = threading.Thread(target=self.receive_question)
        self.receive_thread.start()

    def receive_question(self):
        while True:
            data = self.tcp_socket.recv(1024).decode()
            if not data:
                break

            self.question_label.setText(data)

    def submit_answer(self):
        user_answer = self.answer_input.text()
        try:
            user_answer = int(user_answer)
            self.tcp_socket.sendall(str(user_answer).encode())
        except ValueError:
            self.question_label.setText("请输入一个整数作为答案！")

if __name__ == "__main__":
    app = QApplication(sys.argv)
    client = Client()
    client.show()
    sys.exit(app.exec())
