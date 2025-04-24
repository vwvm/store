import sys
import random
import socket
import threading
from PySide6.QtWidgets import QApplication, QMainWindow, QLabel, QVBoxLayout, QWidget

class Server(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("局域网计算题比赛 - 服务器")
        self.init_ui()

    def init_ui(self):
        self.central_widget = QWidget()
        self.setCentralWidget(self.central_widget)

        self.layout = QVBoxLayout()

        self.question_label = QLabel()
        self.layout.addWidget(self.question_label)

        self.central_widget.setLayout(self.layout)

        self.generate_question()

    def generate_question(self):
        a = random.randint(1, 100)
        b = random.randint(1, 100)
        operator = random.choice(['+', '-', '*', '/'])

        if operator == '+':
            self.answer = a + b
        elif operator == '-':
            self.answer = a - b
        elif operator == '*':
            self.answer = a * b
        else:
            self.answer = a // b

        self.question_label.setText(f"{a} {operator} {b} = ?")

    def start_server(self):
        self.tcp_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.tcp_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.tcp_socket.bind(('localhost', 12345))
        self.tcp_socket.listen()

        self.client_socket, _ = self.tcp_socket.accept()

        self.send_question()

    def send_question(self):
        while True:
            self.generate_question()
            self.client_socket.sendall(self.question_label.text().encode())
            data = self.client_socket.recv(1024).decode()
            if not data:
                break

            try:
                user_answer = int(data)
                if user_answer == self.answer:
                    self.question_label.setText("回答正确！")
                else:
                    self.question_label.setText(f"回答错误，正确答案是 {self.answer}")
            except ValueError:
                self.question_label.setText("请输入一个整数作为答案！")

        self.client_socket.close()
        self.tcp_socket.close()

if __name__ == "__main__":
    app = QApplication(sys.argv)
    server = Server()
    server.show()
    server.start_server()
    sys.exit(app.exec())
