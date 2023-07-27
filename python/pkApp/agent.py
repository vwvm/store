import sys
from PySide6.QtWidgets import QApplication, QWidget, QLabel, QVBoxLayout
from PySide6.QtCore import Qt


class EnterEventApp(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("回车事件示例")
        self.init_ui()

    def init_ui(self):
        layout = QVBoxLayout()

        self.label = QLabel("按下回车键可以触发事件！")
        layout.addWidget(self.label)

        self.setLayout(layout)

    def keyPressEvent(self, event):
        if event.key() == int(Qt.Key_Return) or event.key() == int(Qt.Key_Enter):
            self.label.setText("回车键被按下了！")


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = EnterEventApp()
    window.show()
    sys.exit(app.exec())
