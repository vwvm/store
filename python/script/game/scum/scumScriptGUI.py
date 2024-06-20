import sys
import threading
from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, QHBoxLayout, QLabel, QLineEdit, QPushButton
from PySide6.QtCore import Qt, QThread, Signal
from pynput import keyboard

class ScriptThread(QThread):
    started = Signal()
    stopped = Signal()

    def __init__(self, parent=None):
        super().__init__(parent)
        self.is_running = False

    def run(self):
        self.is_running = True
        self.started.emit()

        while self.is_running:
            print("Hello, world!")
            self.sleep(1)

        self.stopped.emit()

    def stop(self):
        self.is_running = False


class MainWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()
        self.script_thread = ScriptThread()
        self.script_thread.started.connect(self.onScriptStarted)
        self.script_thread.stopped.connect(self.onScriptStopped)
        self.setupGlobalHotkeys()

    def initUI(self):
        # 设置窗口标题
        self.setWindowTitle('钓鱼脚本')

        # 创建垂直布局
        main_layout = QVBoxLayout(self)

        # 第一行：标题
        title_label = QLabel('47(BlackBox) 钓鱼脚本', self)
        title_label.setAlignment(Qt.AlignmentFlag.AlignCenter)
        main_layout.addWidget(title_label)

        # 第二行：启动
        start_layout = QHBoxLayout()
        start_label = QLabel('启动快捷键', self)
        self.start_input = QLineEdit(self)
        self.start_input.setText("Shift+F1")
        start_button = QPushButton('启动', self)
        start_button.clicked.connect(self.startScript)

        start_layout.addWidget(start_label)
        start_layout.addWidget(self.start_input)
        start_layout.addWidget(start_button)

        main_layout.addLayout(start_layout)

        # 第三行：停止
        stop_layout = QHBoxLayout()
        stop_label = QLabel('停止快捷键', self)
        self.stop_input = QLineEdit(self)
        self.stop_input.setText("Shift+F2")
        stop_button = QPushButton('停止', self)
        stop_button.clicked.connect(self.stopScript)

        stop_layout.addWidget(stop_label)
        stop_layout.addWidget(self.stop_input)
        stop_layout.addWidget(stop_button)

        main_layout.addLayout(stop_layout)

        # 设置窗口布局
        self.setLayout(main_layout)

        # 调整窗口大小并显示
        self.resize(300, 150)
        self.show()

    def setupGlobalHotkeys(self):
        # 监听全局按键事件
        self.listener = keyboard.GlobalHotKeys({
            '<shift>+<f1>': self.startScript,
            '<shift>+<f2>': self.stopScript,
        })
        self.listener.start()

    def startScript(self):
        # 启动脚本线程
        self.script_thread.start()

    def stopScript(self):
        # 停止脚本线程
        self.script_thread.stop()

    def onScriptStarted(self):
        print("Script started.")

    def onScriptStopped(self):
        print("Script stopped.")

    def closeEvent(self, event):
        # 关闭窗口时停止监听
        self.listener.stop()
        event.accept()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    mainWindow = MainWindow()
    sys.exit(app.exec())
