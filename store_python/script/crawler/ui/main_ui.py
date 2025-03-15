import sys
import threading

from urllib.parse import urlparse
import requests
import os
import shutil
from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, QHBoxLayout, QLineEdit, QPushButton, QFileDialog, \
    QTextEdit, QLabel, QCheckBox, QGridLayout, QScrollArea, QFrame
from PySide6.QtGui import QPixmap
from PySide6.QtCore import Qt, QObject, Signal
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from contextlib import redirect_stdout
from script.crawler.controller import software_finder

class LogEmitter(QObject):
    log_updated = Signal(str)


class WebLauncher(QWidget):
    def __init__(self):
        super().__init__()

        self.init_ui()
        # 日志
        self.log_emitter = LogEmitter()
        self.log_emitter.log_updated.connect(self.update_log_output)

    def init_ui(self):
        self.setWindowTitle('Web Launcher')
        self.setGeometry(100, 100, 800, 600)

        layout = QVBoxLayout()

        # URL输入和按钮在一行
        url_layout = QHBoxLayout()
        save_url_label = QLabel("爬取的网址:")
        self.url_input = QLineEdit(self)
        self.url_input.setText("https://flbook.com.cn/c/ilJGbGEjF5")
        self.url_input.setPlaceholderText('请输入网址')
        self.launch_button = QPushButton('确认', self)
        self.launch_button.clicked.connect(self.launch_website)
        url_layout.addWidget(save_url_label)
        url_layout.addWidget(self.url_input)
        url_layout.addWidget(self.launch_button)
        layout.addLayout(url_layout)

        # 保存位置输入和按钮在一行
        save_layout = QHBoxLayout()
        save_path_label = QLabel("保存的路径:")
        self.save_input = QLineEdit(self)
        self.save_input.setText(os.getcwd())
        self.save_input.setPlaceholderText('请选择保存位置')
        self.save_button = QPushButton('浏览', self)
        self.save_button.clicked.connect(self.select_save_location)
        save_layout.addWidget(save_path_label)
        save_layout.addWidget(self.save_input)
        save_layout.addWidget(self.save_button)
        layout.addLayout(save_layout)

        # 保存位置输入和按钮在一行
        binary_location_layout = QHBoxLayout()
        web_app_path_label = QLabel("谷歌浏览器路径:")
        self.binary_location_input = QLineEdit(self)
        self.binary_location_input.setText("C:\Program Files\Google\Chrome\Application\chrome.exe")
        self.binary_location_input.setPlaceholderText('浏览器启动路径')
        self.binary_location_button = QPushButton('浏览', self)
        self.binary_location_button.clicked.connect(self.select_save_location2)
        binary_location_layout.addWidget(web_app_path_label)
        binary_location_layout.addWidget(self.binary_location_input)
        binary_location_layout.addWidget(self.binary_location_button)
        layout.addLayout(binary_location_layout)

        # 图片显示区域
        self.scroll_area = QScrollArea(self)
        self.scroll_area.setWidgetResizable(True)
        self.image_container = QWidget()
        self.image_layout = QGridLayout(self.image_container)
        self.scroll_area.setWidget(self.image_container)
        layout.addWidget(self.scroll_area)

        # 日志显示区域
        self.log_output = QTextEdit(self)
        self.log_output.setReadOnly(True)
        self.log_output.setText("信息：")
        layout.addWidget(self.log_output)

        self.setLayout(layout)

        self.image_infos = []  # 存储图片信息

    def launch_website(self):
        # 清空日志部分
        self.log_output.clear()
        url = self.url_input.text()
        if url:
            thread = threading.Thread(target=self.log_packaging, args=(url,))
            thread.start()

    def log_packaging(self, url):

        log_file = os.path.join(os.getcwd(), "log.txt")
        self.log_emitter.log_updated.emit("采集的线程启动前")
        with open(log_file, 'w') as f:
            with redirect_stdout(f):
                self.load_website(url=url)

    def load_website(self, url):
        self.log_emitter.log_updated.emit("采集的线程已启动")

        if not url.startswith(('http://', 'https://')):
            url = 'http://' + url
        # 解析 URL，提取域名
        parsed_url = urlparse(url)
        domain = parsed_url.netloc  # 获取域名部分
        if domain in software_finder.supported_domain_names:
            self.log_emitter.log_updated.emit("支持")
        else:
            return
        # 启动爬虫必须的环境
        options = webdriver.ChromeOptions()
        self.log_emitter.log_updated.emit(f"浏览器启动路径{self.binary_location_input.text()}")
        options.binary_location = self.binary_location_input.text()
        try:
            options.add_argument('--no-sandbox')
            options.add_argument('--start-maximized')
            options.add_argument("--disable-extensions")

            to_capabilities = options.to_capabilities()
            to_capabilities['pageLoadStrategy'] = 'none'
            to_capabilities['timeouts'] = {'implicit': 3000, 'pageLoad': 3000, 'script': 3000}
            driver = webdriver.Chrome(options=options)
            driver.get(url)

            # 根据域名启动不同的爬虫程序
            if domain == "detail.tmall.com":
                self.log_emitter.log_updated.emit("启动淘宝")
                software_finder.detail_tmall_com(driver, self.log_emitter)
            if domain == "flbook.com.cn":
                self.log_emitter.log_updated.emit("启动画册")
                software_finder.flbook_com_cn(driver, self.log_emitter, self)

        except Exception as e:
            print(f"启动错误: {e}")
            self.log_emitter.log_updated.emit(f"启动错误: {e}")


    def display_image(self, image_url, folder, row, col):
        try:
            response = requests.get(image_url)
            image_data = response.content

            image_name = os.path.basename(image_url)
            image_path = os.path.join(folder, image_name)
            print(f"图片保存在：{image_path}")
            self.log_emitter.log_updated.emit(f"图片保存在：{image_path}")
            with open(image_path, 'wb') as file:
                file.write(image_data)

            pixmap = QPixmap()
            pixmap.loadFromData(image_data)
            pixmap = pixmap.scaled(150, 150, Qt.KeepAspectRatio)

            image_label = QLabel()
            image_label.setPixmap(pixmap)

            checkbox = QCheckBox()
            checkbox.setChecked(True)
            checkbox.image_path = image_path

            container = QVBoxLayout()
            container.addWidget(image_label)
            container.addWidget(checkbox)

            frame = QFrame()
            frame.setLayout(container)

            self.image_layout.addWidget(frame, row, col)
        except Exception as e:
            print(f"无法显示图片: {e}")

    def select_save_location(self):
        options = QFileDialog.Option.DontUseNativeDialog
        folder = QFileDialog.getExistingDirectory(self, "选择保存位置", "", options=options)
        if folder:
            self.save_input.setText(folder)
            self.temp_folder = folder
            self.save_selected_images(folder)

    def select_save_location2(self):
        options = QFileDialog.Option.DontUseNativeDialog
        folder = QFileDialog.getExistingDirectory(self, "选择保存位置", "", options=options)
        if folder:
            self.binary_location_input.setText(folder)

    def save_selected_images(self, folder):
        for i in range(self.image_layout.count()):
            frame = self.image_layout.itemAt(i).widget()
            checkbox = frame.findChild(QCheckBox)
            if checkbox and checkbox.isChecked():
                image_path = checkbox.image_path
                shutil.move(image_path, folder)

    def update_log_output(self, message):
        self.log_output.append(message)

def start():
    app = QApplication(sys.argv)
    launcher = WebLauncher()
    launcher.show()
    sys.exit(app.exec())

if __name__ == '__main__':
    start()
