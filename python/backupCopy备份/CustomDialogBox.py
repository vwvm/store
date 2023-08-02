import sys
from PySide6.QtWidgets import QApplication, QPushButton, QMessageBox, QVBoxLayout, QWidget

class WarningBoxApp(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("警告框示例")
        self.init_ui()

    def init_ui(self):
        layout = QVBoxLayout()

        button = QPushButton("点击弹出警告框")
        button.clicked.connect(self.show_warning_box)
        layout.addWidget(button)

        self.setLayout(layout)

    def show_warning_box(self):
        reply = QMessageBox.warning(self, "警告", "确定要执行此操作吗？", QMessageBox.Yes | QMessageBox.No, QMessageBox.No)

        if reply == QMessageBox.Yes:
            print("用户点击了确定按钮")
        else:
            print("用户点击了取消按钮")

if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = WarningBoxApp()
    window.show()
    sys.exit(app.exec())
