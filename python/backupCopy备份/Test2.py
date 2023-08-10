from PySide6.QtWidgets import QApplication, QWidget, QPushButton, QVBoxLayout, QFrame
from PySide6.QtCore import Qt

app = QApplication([])

parent_widget = QWidget()

layout1 = QVBoxLayout()
parent_widget2 = QWidget()
parent_widget3 = QFrame()

layout2 = QVBoxLayout()
parent_widget21 = QWidget()
parent_widget22 = QWidget()
# ... 创建子部件并添加到父部件 ...
parent_widget2.setLayout(layout2)

layout1.addWidget(parent_widget2)
layout1.addWidget(parent_widget3)

parent_widget.setLayout(layout1)

# 查找并打印父部件的所有子部件
children = parent_widget.findChildren(QWidget)
children = [child for child in children if type(child) is QWidget]
for child in children:
    print(f"Child Widget: {child}")

# 查找并打印父部件的所有 QPushButton 子部件，包括递归查找
buttons = parent_widget.findChildren(QWidget, options=Qt.FindChildrenRecursively)
for button in buttons:
    print(f"PushButton Child: {button}")

parent_widget.show()
app.exec()
