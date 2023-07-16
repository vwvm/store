from PySide6.QtCore import QThread, Signal, QObject
import time


class WorkThread(QObject):
    signal = Signal(str)

    def __init__(self):
        super().__init__()
        print("run")

    def run(self) -> None:
        for i in range(10):
            self.signal.emit(str(i))
            time.sleep(1)
            print(i)

    def work(self):
        for i in range(10):
            self.signal.emit(str(i))
            print(i)
            time.sleep(1)
