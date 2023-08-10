#!/usr/bin/env python
# coding=utf-8
import random
import sys
import time

from PySide6.QtCore import QTimer, Qt
from PySide6.QtGui import QKeyEvent
from PySide6.QtWidgets import (QApplication, QMainWindow, QLabel,
                               QLineEdit, QPushButton, QVBoxLayout,
                               QWidget, QCheckBox, QMessageBox)

import App


class SelectWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.init_ui()

    def init_ui(self):
        self.setWindowTitle("配置界面")
        self.resize(300, 225)
        self.cleck_box_add = QCheckBox("+")
        self.cleck_box_sub = QCheckBox("-")
        self.cleck_box_rid = QCheckBox("×")
        self.cleck_box_div = QCheckBox("÷")

        self.line = QLineEdit()
        self.line.setPlaceholderText("请输入一个整数以控制程序出题的范围")
        self.button = QPushButton("启动程序")
        self.button.clicked.connect(self.start_window)

        layout = QVBoxLayout()

        layout.addWidget(self.cleck_box_add)
        layout.addWidget(self.cleck_box_sub)
        layout.addWidget(self.cleck_box_rid)
        layout.addWidget(self.cleck_box_div)

        layout.addWidget(self.line)
        layout.addWidget(self.button)
        main_widget = QWidget()
        main_widget.setLayout(layout)
        self.setCentralWidget(main_widget)
        # self.add_question()
        # self.judge_question()
        pass

    def add_question(self):
        """
        添加题目
        """
        while True:
            one_num = random.randint(1, 100)
            two_num = random.randint(1, 100)
            operator = random.choice(['+', '-', '×', '÷'])

            match operator:
                case '+':
                    self.ans = one_num + two_num
                case '-':
                    self.ans = one_num - two_num
                case '×':
                    self.ans = one_num * two_num
                case '÷':
                    self.ans = one_num / two_num
                    if one_num % two_num != 0:
                        continue
            if self.ans <= 0 or self.ans >= 100:
                continue
            break
        pass

    def start_window(self):
        """
        判断是否为整数
        判断是否大于1
        判断是否全不选
        """
        edit_value = self.line.text()
        try:
            edit_value = int(edit_value)
            if edit_value <= 1:
                self.start_message_box("请输入大于1的整数")
                self.line.clear()
            operator = []
            if self.cleck_box_add.isChecked():
                operator.append('+')
            if self.cleck_box_sub.isChecked():
                operator.append('-')
            if self.cleck_box_rid.isChecked():
                operator.append('×')
            if self.cleck_box_div.isChecked():
                operator.append('÷')
            if len(operator) == 0:
                operator = ['+', '-', '×', '÷']
            config_list = [operator, edit_value]
            self.close()

            self.window = App.Main(config_list)
            self.window.show()

        except:
            self.start_message_box("请输入整数")
            self.line.clear()
        QTimer.singleShot(1000, self.add_question)
        pass

    def start_message_box(self, msg: str, title_msg: str = "提示"):
        msg_box = QMessageBox()
        msg_box.setWindowTitle(title_msg)
        msg_box.setText(msg)
        msg_box.setIcon(QMessageBox.Information)
        msg_box.exec()

    def keyPressEvent(self, event: QKeyEvent) -> None:
        if event.key() == int(Qt.Key.Key_Return) or event.key() == int(Qt.Key.Key_Enter):
            self.start_window()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = SelectWindow()
    window.show()
    sys.exit(app.exec())
    pass
