import os
import sys
from PySide6.QtCore import QTimer, Qt
from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, QLabel, QScrollArea, QGridLayout
from PySide6.QtGui import QPixmap, QMouseEvent


class ClickableLabel(QLabel):
    def __init__(self, image_path, slideshow, parent=None):
        super().__init__(parent)
        self.image_path = image_path
        self.slideshow = slideshow
        self.selected = False

    def mousePressEvent(self, event: QMouseEvent):
        self.selected = not self.selected
        if self.selected:
            self.setStyleSheet("border: 2px solid red;")
            self.slideshow.addSelectedImage(self.image_path)
        else:
            self.setStyleSheet("")
            self.slideshow.removeSelectedImage(self.image_path)
        super().mousePressEvent(event)


class ImageSlideshow(QWidget):
    def __init__(self, images):
        super().__init__()
        self.images = images
        self.current_index = 0
        self.selected_images = []

        self.initUI()
        self.startSlideshow()

    def initUI(self):
        main_layout = QVBoxLayout()
        scroll_area = QScrollArea()
        self.scroll_widget = QWidget()
        self.grid = QGridLayout()
        self.scroll_widget.setLayout(self.grid)

        scroll_area.setWidget(self.scroll_widget)
        scroll_area.setWidgetResizable(True)

        main_layout.addWidget(scroll_area)
        self.setLayout(main_layout)

        self.setWindowTitle('Image Slideshow')
        self.setGeometry(300, 300, 800, 600)

    def startSlideshow(self):
        self.timer = QTimer(self)
        self.timer.timeout.connect(self.showNextImage)
        self.timer.start(1000)  # 每秒显示一张图片

    def showNextImage(self):
        if not self.images:
            return

        image_path = self.images[self.current_index]
        pixmap = QPixmap(image_path)
        if pixmap.isNull():
            print(f"Failed to load image: {image_path}")
        else:
            label = ClickableLabel(image_path, self, self)
            label.setPixmap(pixmap.scaled(120, 160, Qt.AspectRatioMode.KeepAspectRatio, Qt.TransformationMode.SmoothTransformation))
            label.setScaledContents(True)
            row = self.current_index // 5
            col = self.current_index % 5
            self.grid.addWidget(label, row, col)

        self.current_index += 1
        if self.current_index >= len(self.images):
            self.current_index = 0
            # 清空网格布局
            for i in reversed(range(self.grid.count())):
                widget = self.grid.itemAt(i).widget()
                if widget is not None:
                    widget.deleteLater()

    def addSelectedImage(self, image_path):
        if image_path not in self.selected_images:
            self.selected_images.append(image_path)
        print("Selected images:", self.selected_images)

    def removeSelectedImage(self, image_path):
        if image_path in self.selected_images:
            self.selected_images.remove(image_path)
        print("Selected images:", self.selected_images)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    base_path = os.path.join(os.getcwd(), "temp_ENF级环保板材2024")
    print(os.listdir(os.getcwd()))
    # 替换为你的图片路径列表
    images = [os.path.join(base_path, image) for image in os.listdir(base_path) if
              image.endswith(('.png', '.jpg', '.jpeg', '.bmp', '.gif'))]
    ex = ImageSlideshow(images)
    ex.show()
    sys.exit(app.exec())
