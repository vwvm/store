from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, QLineEdit, \
    QTableWidget, QTableWidgetItem, QHeaderView, QPushButton, QHBoxLayout
import sys
from faker import Faker


class MyWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.resize(800, 600)

        self.fake = Faker(locale="zh_Cn")
        self.data = [[self.fake.name(), self.fake.address(), self.fake.ascii_free_email()] for _ in range(80)]

        # 搜索栏
        self.searchLayout = QHBoxLayout()
        self.lineSearch = QLineEdit()

        self.btn = QPushButton("输出")
        self.btn.clicked.connect(self.outputSelection)

        self.table = QTableWidget()
        self.table.setRowCount(80)
        self.table.setColumnCount(3)
        self.table.setHorizontalHeaderLabels(["姓名", "地址", "邮箱"])
        self.table.horizontalHeader().setSectionResizeMode(QHeaderView.ResizeMode.Stretch)
        # 设置表头的排序
        self.table.setSortingEnabled(True)
        self.table.itemClicked.connect(lambda item: print(f"当前点击了第{item.row()}行，第{item.column()}列，元素叫{item.text()}"))

        for rowIndex, row in enumerate(self.data):
            for columnIndex, item in enumerate(row):
                self.table.setItem(rowIndex, columnIndex, QTableWidgetItem(item))

        self.mainLayout = QVBoxLayout()
        self.mainLayout.addWidget(self.table)
        self.mainLayout.addWidget(self.btn)
        self.setLayout(self.mainLayout)

    def outputSelection(self):
        for i in self.table.selectedItems():
            print(i.text())


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = MyWindow()
    window.show()
    app.exec()
