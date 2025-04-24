# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'ui_main.ui'
##
## Created by: Qt User Interface Compiler version 6.2.1
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################

from PySide6.QtCore import (QCoreApplication, QDate, QDateTime, QLocale,
    QMetaObject, QObject, QPoint, QRect,
    QSize, QTime, QUrl, Qt)
from PySide6.QtGui import (QBrush, QColor, QConicalGradient, QCursor,
    QFont, QFontDatabase, QGradient, QIcon,
    QImage, QKeySequence, QLinearGradient, QPainter,
    QPalette, QPixmap, QRadialGradient, QTransform)
from PySide6.QtWidgets import (QApplication, QFrame, QLabel, QLineEdit,
    QMainWindow, QMenuBar, QPushButton, QSizePolicy,
    QStatusBar, QVBoxLayout, QWidget)

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        if not MainWindow.objectName():
            MainWindow.setObjectName(u"MainWindow")
        MainWindow.resize(371, 359)
        self.centralwidget = QWidget(MainWindow)
        self.centralwidget.setObjectName(u"centralwidget")
        self.verticalLayout = QVBoxLayout(self.centralwidget)
        self.verticalLayout.setObjectName(u"verticalLayout")
        self.frame1 = QFrame(self.centralwidget)
        self.frame1.setObjectName(u"frame1")
        self.frame1.setFrameShape(QFrame.StyledPanel)
        self.frame1.setFrameShadow(QFrame.Raised)
        self.sent_btn = QPushButton(self.frame1)
        self.sent_btn.setObjectName(u"sent_btn")
        self.sent_btn.setGeometry(QRect(90, 70, 75, 24))
        self.recevice_text = QLineEdit(self.frame1)
        self.recevice_text.setObjectName(u"recevice_text")
        self.recevice_text.setGeometry(QRect(120, 190, 133, 21))
        self.label = QLabel(self.frame1)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(80, 190, 31, 16))
        self.sent_text = QLineEdit(self.frame1)
        self.sent_text.setObjectName(u"sent_text")
        self.sent_text.setGeometry(QRect(120, 130, 133, 21))
        self.label_2 = QLabel(self.frame1)
        self.label_2.setObjectName(u"label_2")
        self.label_2.setGeometry(QRect(80, 130, 31, 16))
        self.open_subwin = QPushButton(self.frame1)
        self.open_subwin.setObjectName(u"open_subwin")
        self.open_subwin.setGeometry(QRect(260, 260, 75, 24))

        self.verticalLayout.addWidget(self.frame1)

        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QMenuBar(MainWindow)
        self.menubar.setObjectName(u"menubar")
        self.menubar.setGeometry(QRect(0, 0, 371, 23))
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QStatusBar(MainWindow)
        self.statusbar.setObjectName(u"statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)

        QMetaObject.connectSlotsByName(MainWindow)
    # setupUi

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(QCoreApplication.translate("MainWindow", u"\u6263\u811a\u5927\u6c49", None))
        self.sent_btn.setText(QCoreApplication.translate("MainWindow", u"\u53d1\u9001", None))
        self.label.setText(QCoreApplication.translate("MainWindow", u"\u63a5\u6536\uff1a", None))
        self.label_2.setText(QCoreApplication.translate("MainWindow", u"\u53d1\u9001\uff1a", None))
        self.open_subwin.setText(QCoreApplication.translate("MainWindow", u"\u6253\u5f00\u5b50\u7a97\u53e3", None))
    # retranslateUi


