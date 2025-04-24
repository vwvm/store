import sys
from PySide6.QtWidgets import QApplication, QVBoxLayout, QWidget, QCheckBox, QLabel, QPushButton

class TwoGroupsCheckboxApp(QWidget):
    def __init__(self):
        super().__init__()

        self.init_ui()

    def init_ui(self):
        layout = QVBoxLayout()

        # 创建第一组复选框
        group1_label = QLabel('Group 1:')
        self.checkbox1_1 = QCheckBox('Option 1')
        self.checkbox1_2 = QCheckBox('Option 2')
        self.checkbox1_3 = QCheckBox('Option 3')

        layout.addWidget(group1_label)
        layout.addWidget(self.checkbox1_1)
        layout.addWidget(self.checkbox1_2)
        layout.addWidget(self.checkbox1_3)

        # 创建第二组复选框
        group2_label = QLabel('Group 2:')
        self.checkbox2_1 = QCheckBox('Option A')
        self.checkbox2_2 = QCheckBox('Option B')
        self.checkbox2_3 = QCheckBox('Option C')

        layout.addWidget(group2_label)
        layout.addWidget(self.checkbox2_1)
        layout.addWidget(self.checkbox2_2)
        layout.addWidget(self.checkbox2_3)

        # 创建按钮
        self.print_button = QPushButton('Print Selected')
        layout.addWidget(self.print_button)

        # 连接按钮点击事件
        self.print_button.clicked.connect(self.print_selected)

        self.setLayout(layout)
        self.setWindowTitle('Two Groups of Checkboxes')

    def print_selected(self):
        selected_options = []

        if self.checkbox1_1.isChecked():
            selected_options.append(self.checkbox1_1.text())
        if self.checkbox1_2.isChecked():
            selected_options.append(self.checkbox1_2.text())
        if self.checkbox1_3.isChecked():
            selected_options.append(self.checkbox1_3.text())

        if self.checkbox2_1.isChecked():
            selected_options.append(self.checkbox2_1.text())
        if self.checkbox2_2.isChecked():
            selected_options.append(self.checkbox2_2.text())
        if self.checkbox2_3.isChecked():
            selected_options.append(self.checkbox2_3.text())

        print('Selected Options:', selected_options)

if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = TwoGroupsCheckboxApp()
    window.show()
    sys.exit(app.exec())
