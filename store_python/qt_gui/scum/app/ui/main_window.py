import sys
import os
from PySide6.QtWidgets import QApplication
from PySide6.QtQml import QQmlApplicationEngine
from PySide6.QtCore import QObject, Signal, Slot, QUrl, QMessageLogContext, QtMsgType
from PySide6.QtCore import qInstallMessageHandler


class Backend(QObject):
    """简单的后端交互类"""
    # 发送消息到QML的信号
    messageToQml = Signal(str)

    @Slot(str)  # 接收QML发送的字符串
    def fromQml(self, text):
        """处理来自QML的消息"""
        print(f"Python收到: {text}")  # 控制台输出
        # 处理后发送回QML
        response = f"已收到: {text}"
        self.messageToQml.emit(response)



def main_start():
    app = QApplication(sys.argv)

    # 创建QML引擎
    engine = QQmlApplicationEngine()

    # 打印路径信息（保持不变）
    current_working_dir = os.getcwd()
    print(f"当前运行根目录: {current_working_dir}")

    current_script_dir = os.path.dirname(os.path.abspath(__file__))
    print(f"当前脚本所在目录: {current_script_dir}")

    qml_path = os.path.join(current_script_dir, "qml", "main_window.qml")
    print(f"QML文件查找路径: {qml_path}")

    if not os.path.exists(qml_path):
        print(f"错误：QML文件不存在 - {qml_path}")
        sys.exit(-1)

    # 创建后端实例并暴露给QML
    backend = Backend()
    engine.rootContext().setContextProperty("backend", backend)

    # 加载QML
    engine.load(QUrl.fromLocalFile(qml_path))

    if not engine.rootObjects():
        print("错误：无法加载QML界面（见上方错误详情）")
        sys.exit(-1)

    sys.exit(app.exec())


if __name__ == "__main__":
    main_start()
