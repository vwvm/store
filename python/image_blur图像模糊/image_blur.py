from PySide6.QtWidgets import QApplication, QWidget, \
    QVBoxLayout, QTextEdit, QPushButton, QFileDialog, \
    QLabel, QSlider
from PySide6.QtCore import Qt
from PySide6.QtGui import QPixmap

from PIL import Image, ImageFilter, ImageQt
import sys


class MyWindow(QWidget):
    def __init__(self):
        super().__init__()

        self.resize(800, 600)

        self.btn = QPushButton("点我导入图像")
        self.btn.clicked.connect(self.getImg)

        self.lbShowImg = QLabel()

        self.slider = QSlider(Qt.Orientation.Horizontal)
        self.slider.setRange(0, 20)
        self.slider.setTickPosition(QSlider.TickPosition.TicksBelow)
        self.slider.setTickInterval(3)
        self.slider.valueChanged.connect(self.silderValueChanged)

        self.mainLayout = QVBoxLayout()
        self.mainLayout.addWidget(self.btn)
        self.mainLayout.addWidget(self.lbShowImg)
        self.mainLayout.addWidget(self.slider)
        self.setLayout(self.mainLayout)

    def getImg(self):
        self.img = Image.open(QFileDialog
                              .getOpenFileName(self, "全职图像", "./", "图像文件(*.png *.jpg)")[0])
        self.lbShowImg.setPixmap(ImageQt.toqpixmap(self.img))

        pass

    def silderValueChanged(self, value):
        self.blurPic = self.img.filter(ImageFilter.GaussianBlur(value))
        self.lbShowImg.setPixmap(ImageQt.toqpixmap(self.blurPic))

if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = MyWindow()
    window.show()
    app.exec()
