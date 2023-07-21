from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, QScrollArea, QLabel

app = QApplication([])

# 创建一个 QWidget 作为主窗口
window = QWidget()
layout = QVBoxLayout(window)

# 创建一个 QScrollArea 作为滚动区域
scroll_area = QScrollArea()
scroll_widget = QWidget()

# 创建一个垂直布局
scroll_layout = QVBoxLayout()
scroll_widget.setLayout(scroll_layout)
# 向滚动布局中添加一些内容
for i in range(50):
    label = QLabel(f"Label {i}")
    scroll_layout.addWidget(label)

# 设置滚动区域的小部件
scroll_area.setWidget(scroll_widget)

# 将滚动区域添加到主布局中
layout.addWidget(scroll_area)

window.show()
app.exec()
