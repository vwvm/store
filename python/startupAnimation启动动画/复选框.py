import sys
from PySide6.QtWidgets import QApplication, QVBoxLayout, QWidget, QPushButton, QCheckBox, QLabel


class CalculatorApp(QWidget):
    def __init__(self):
        super().__init__()

        self.init_ui()

    def init_ui(self):
        layout = QVBoxLayout()

        # 创建复选框
        add_checkbox = QCheckBox('Addition')
        sub_checkbox = QCheckBox('Subtraction')
        mul_checkbox = QCheckBox('Multiplication')
        div_checkbox = QCheckBox('Division')

        # 创建按钮
        calculate_button = QPushButton('Calculate')

        # 创建结果标签
        self.result_label = QLabel('Result:')
        self.operation_label = QLabel('Selected Operation:')

        layout.addWidget(add_checkbox)
        layout.addWidget(sub_checkbox)
        layout.addWidget(mul_checkbox)
        layout.addWidget(div_checkbox)
        layout.addWidget(calculate_button)
        layout.addWidget(self.result_label)
        layout.addWidget(self.operation_label)

        # 连接按钮点击事件
        calculate_button.clicked.connect(self.calculate)

        # 连接复选框点击事件，显示选择的操作
        add_checkbox.clicked.connect(self.show_selected_operation)
        sub_checkbox.clicked.connect(self.show_selected_operation)
        mul_checkbox.clicked.connect(self.show_selected_operation)
        div_checkbox.clicked.connect(self.show_selected_operation)

        self.setLayout(layout)
        self.setWindowTitle('Calculator')

    def calculate(self):
        result = 0

        # 在 calculate 函数中获取选中的操作
        operation = self.operation_label.text().split(': ')[1]

        if operation == 'Addition':
            result = 10 + 20
        elif operation == 'Subtraction':
            result = 30 - 15
        elif operation == 'Multiplication':
            result = 5 * 6
        elif operation == 'Division':
            result = 25 / 5

        self.result_label.setText(f'Result: {result}')

    def show_selected_operation(self):
        # 显示选择的操作
        selected_operation = ', '.join(
            [checkbox.text() for checkbox in self.findChildren(QCheckBox) if checkbox.isChecked()])
        self.operation_label.setText(f'Selected Operation: {selected_operation}')


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = CalculatorApp()
    window.show()
    sys.exit(app.exec())
