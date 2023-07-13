from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, QListWidget
from PySide6.QtCore import Qt
from PySide6.QtGui import QAction
from faker import Faker


class MyWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.fake = Faker(locale="zh_CN")

        self.listWidget = QListWidget()
        self.listWidget.addItems([self.fake.name() for _ in range(20)])

        self.listWidget.insertItems(2, ['120', '110', '333'])

        self.listWidget.setContextMenuPolicy(Qt.ContextMenuPolicy.ActionsContextMenu)
        self.outputCurrentValue = QAction("输出当前值")
        self.outputCurrentValue.triggered.connect(self.outputCurrent)
        self.deleteCurrentValue = QAction("删除当前值")
        self.deleteCurrentValue.triggered.connect(self.deleteCurrent)
        self.listWidget.addActions([self.outputCurrentValue, self.deleteCurrentValue])

        self.mainLayout = QVBoxLayout()
        self.mainLayout.addWidget(self.listWidget)
        self.setLayout(self.mainLayout)

        self.bind()

    def bind(self):
        self.listWidget.currentItemChanged.connect(self.listChange)

    def outputCurrent(self):
        print(self.listWidget.currentItem().text())

    def deleteCurrent(self):
        self.listWidget.takeItem(self.listWidget.currentRow())

    def listChange(self, item, previous):
        print(f"当前选中的是{item.text()},上一个选中的是{previous.text()}")


if __name__ == '__main__':
    app = QApplication([])
    window = MyWindow()
    window.show()
    app.exec()
