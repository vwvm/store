import pathlib
from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, \
    QTextEdit, QMainWindow, QPushButton, QHBoxLayout, QFileDialog, \
    QLineEdit, QFrame
from qt_material import apply_stylesheet
import sys


class NewSelectFolderWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        self.resize(800, 600)
        main_layout = QVBoxLayout()

        line_edit_a = QLineEdit("输入源文件夹")
        btn_a = QPushButton("+")
        btn_a.clicked.connect(self.select_folder)
        line_edit_b = QLineEdit("输入目标文件夹")
        btn_b = QPushButton("+")
        btn_b.clicked.connect(self.select_folder)

        # 左右布局
        layout = QHBoxLayout()
        layout2 = QHBoxLayout()

        layout.addWidget(line_edit_a)
        layout.addWidget(btn_a)
        layout.addWidget(line_edit_b)
        layout.addWidget(btn_b)

        frame = QFrame()
        frame.setLayout(layout)

        main_layout.addLayout(layout)
        main_layout.addLayout(layout2)

        widget = QWidget()
        widget.setLayout(layout)

        self.setCentralWidget(widget)

    def select_folder(self):
        self.folderName: str = str(pathlib.Path.home())
        self.folderName = QFileDialog.getExistingDirectory(self, "选择文件夹", self.folderName)
        print(self.folderName)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    apply_stylesheet(app, theme="dark_teal.xml")
    window = NewSelectFolderWindow()
    window.show()
    app.exec()
