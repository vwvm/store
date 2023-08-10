import sys
from PySide6.QtWidgets import QApplication, QVBoxLayout, QWidget, QLineEdit

class DefaultTextApp(QWidget):
    def __init__(self):
        super().__init__()

        self.init_ui()

    def init_ui(self):
        layout = QVBoxLayout()

        # 创建输入框
        self.input_text = QLineEdit()
        self.default_text = 'Enter text here...'
        self.input_text.setPlaceholderText(self.default_text)  # 设置默认文字
        self.input_text.installEventFilter(self)  # 安装事件过滤器

        layout.addWidget(self.input_text)

        self.setLayout(layout)
        self.setWindowTitle('Default Text Clearing')

    def eventFilter(self, source, event):
        if source == self.input_text and event.type() == event.FocusIn:
            if self.input_text.text() == self.default_text:
                self.input_text.clear()
        return super().eventFilter(source, event)

if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = DefaultTextApp()
    window.show()
    sys.exit(app.exec())
