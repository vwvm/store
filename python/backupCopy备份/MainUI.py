# -*- coding: utf-8 -*-
import pathlib

################################################################################
## Form generated from reading UI file 'MainWindow.ui'
##
## Created by: Qt User Interface Compiler version 6.5.1
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
from PySide6.QtWidgets import (QApplication, QFrame, QHBoxLayout, QLineEdit,
                               QListWidget, QListWidgetItem, QMainWindow, QMenuBar,
                               QPushButton, QSizePolicy, QStatusBar, QVBoxLayout,
                               QWidget, QStyle, QFileDialog)


class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        if not MainWindow.objectName():
            MainWindow.setObjectName(u"MainWindow")
        MainWindow.resize(800, 600)
        self.centralwidget = QWidget(MainWindow)
        self.centralwidget.setObjectName(u"centralwidget")
        self.main_horizontal_layout = QHBoxLayout()
        self.main_horizontal_layout.setObjectName(u"main_horizontal_layout")
        self.frame = QFrame(self.centralwidget)
        self.frame.setObjectName(u"frame")
        self.frame.setStyleSheet(u"#frame{\n"
                                 "border-right: 4px dashed blue;\n"
                                 "background-color: rgb(218, 218, 218);\n"
                                 "}")
        self.frame.setFrameShape(QFrame.StyledPanel)
        self.frame.setFrameShadow(QFrame.Raised)
        self.verticalLayout = QVBoxLayout(self.frame)
        self.verticalLayout.setObjectName(u"verticalLayout")
        self.horizontalLayout = QHBoxLayout()
        self.horizontalLayout.setObjectName(u"horizontalLayout")
        source_path_input_box = QLineEdit(self.frame)
        source_path_input_box.setText("请输入源路径")
        source_path_input_box.setObjectName(u"lineEdit")

        self.horizontalLayout.addWidget(source_path_input_box)

        self.pushButton = QPushButton(self.frame)
        self.pushButton.setObjectName(u"pushButton")
        self.pushButton.setText("选择文件夹")

        # icon = QIcon(QIcon.fromTheme(u"accessories-calculator"))
        # self.pushButton.setIcon(icon)

        self.horizontalLayout.addWidget(self.pushButton)

        self.verticalLayout.addLayout(self.horizontalLayout)

        self.listWidget = QListWidget(self.frame)
        QListWidgetItem(self.listWidget)
        QListWidgetItem(self.listWidget)
        QListWidgetItem(self.listWidget)
        QListWidgetItem(self.listWidget)
        self.listWidget.setObjectName(u"listWidget")
        self.listWidget.setMouseTracking(False)
        self.listWidget.setAutoFillBackground(False)

        self.verticalLayout.addWidget(self.listWidget)

        self.main_horizontal_layout.addWidget(self.frame)

        self.frame_2 = QFrame(self.centralwidget)
        self.frame_2.setObjectName(u"frame_2")
        self.frame_2.setStyleSheet(u"#frame_2{\n"
                                   "border-left: 4px dashed blue;\n"
                                   "background-color: rgb(218, 218, 218);\n"
                                   "}")
        self.frame_2.setFrameShape(QFrame.StyledPanel)
        self.frame_2.setFrameShadow(QFrame.Raised)
        self.verticalLayout_2 = QVBoxLayout(self.frame_2)
        self.verticalLayout_2.setObjectName(u"verticalLayout_2")
        self.horizontalLayout_3 = QHBoxLayout()
        self.horizontalLayout_3.setObjectName(u"horizontalLayout_3")
        destination_folder_input_box = QLineEdit(self.frame_2)
        destination_folder_input_box.setObjectName(u"lineEdit_3")
        destination_folder_input_box.setText("目标文件夹输入框")

        self.horizontalLayout_3.addWidget(destination_folder_input_box)

        self.pushButton_3 = QPushButton(self.frame_2)
        self.pushButton_3.setObjectName(u"pushButton_3")
        icon1 = QIcon()
        iconThemeName = u"accessories-calculator"
        if QIcon.hasThemeIcon(iconThemeName):
            icon1 = QIcon.fromTheme(iconThemeName)
        else:
            icon1.addFile(u".", QSize(), QIcon.Normal, QIcon.Off)

        self.pushButton_3.setIcon(icon1)

        self.horizontalLayout_3.addWidget(self.pushButton_3)

        self.verticalLayout_2.addLayout(self.horizontalLayout_3)

        self.listWidget_2 = QListWidget(self.frame_2)
        QListWidgetItem(self.listWidget_2)
        QListWidgetItem(self.listWidget_2)
        QListWidgetItem(self.listWidget_2)
        QListWidgetItem(self.listWidget_2)
        self.listWidget_2.setObjectName(u"listWidget_2")

        self.verticalLayout_2.addWidget(self.listWidget_2)

        self.main_horizontal_layout.addWidget(self.frame_2)

        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QMenuBar(MainWindow)
        self.menubar.setObjectName(u"menubar")
        self.menubar.setGeometry(QRect(0, 0, 800, 21))
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QStatusBar(MainWindow)
        self.statusbar.setObjectName(u"statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)

        ok_button = QPushButton("确认")
        qv_layout = QVBoxLayout(self.centralwidget)
        qv_layout.addLayout(self.main_horizontal_layout)
        qv_layout.addWidget(ok_button)

        self.centralwidget.setLayout(qv_layout)

        QMetaObject.connectSlotsByName(MainWindow)

    # setupUi

    def retranslateUi(self, MainWindow):
        self.left_hand_count = 0
        self.right_hand_count = 0
        MainWindow.setWindowTitle(QCoreApplication.translate("MainWindow", u"MainWindow", None))
        self.pushButton.setText(QCoreApplication.translate("MainWindow", "点击选择文件夹", None))
        self.pushButton.clicked.connect(lambda: self.select_folder(1))

        __sortingEnabled = self.listWidget.isSortingEnabled()
        self.listWidget.setSortingEnabled(False)

        self.listWidget.setSortingEnabled(__sortingEnabled)

        self.pushButton_3.setText(QCoreApplication.translate("MainWindow", "点击选择文件夹", None))
        self.pushButton_3.clicked.connect(lambda: self.select_folder(2))

        __sortingEnabled1 = self.listWidget_2.isSortingEnabled()
        self.listWidget_2.setSortingEnabled(False)
        self.listWidget_2.setSortingEnabled(__sortingEnabled1)

    # retranslateUi

    def select_folder(self, select_type: int) -> str:
        folder_name: str = str(pathlib.Path.home())
        folder_name = QFileDialog.getExistingDirectory(None, "选择文件夹", folder_name)
        print(folder_name)

        if select_type == 1:
            list_item = self.listWidget.item(self.left_hand_count)
            self.left_hand_count += 1
            list_item.setText(folder_name)
        if select_type == 2:
            list_item = self.listWidget_2.item(self.right_hand_count)
            self.right_hand_count += 1
            list_item.setText(folder_name)

        return folder_name
