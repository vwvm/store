import sys
from PySide6.QtWidgets import QApplication, QMainWindow, QSystemTrayIcon, QMenu
from PySide6.QtGui import QIcon, QAction

class MinimizeToTrayApp(QMainWindow):
    def __init__(self):
        super().__init__()

        self.init_ui()

    def init_ui(self):
        self.setWindowTitle("Minimize to Tray")

        self.tray_icon = QSystemTrayIcon(QIcon("vwvmn.png"), self)
        self.tray_icon.activated.connect(self.toggle_window)

        self.show_action = QAction("Show", self)
        self.show_action.triggered.connect(self.show_window)

        self.quit_action = QAction("Quit", self)
        self.quit_action.triggered.connect(self.quit_app)

        self.tray_menu = QMenu()
        self.tray_menu.addAction(self.show_action)
        self.tray_menu.addAction(self.quit_action)
        self.tray_icon.setContextMenu(self.tray_menu)

    def toggle_window(self, reason):
        if reason == QSystemTrayIcon.Trigger:
            if self.isHidden():
                self.show_window()
            else:
                self.hide()

    def show_window(self):
        self.showNormal()
        self.activateWindow()

    def quit_app(self):
        self.tray_icon.hide()
        self.close()

if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = MinimizeToTrayApp()
    window.show()
    sys.exit(app.exec())
