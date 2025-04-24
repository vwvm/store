import time

import requests
from PySide6.QtCore import Qt, QThread, Signal
from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, QLabel


class Spider(QThread):
    webCode = Signal(int)

    def __init__(self, url="https://www.baidu.com"):
        super().__init__()
        self.url = url
        print(f"当前请求的url为:{url}")
        print(f"当前线程为:{self.currentThread()}")
        print(f"当前网页的状态码为:{requests.get(url=url).status_code}")

    def run(self):
        self.webCode.emit(requests.get(url=self.url).status_code)


