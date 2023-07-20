# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'ui_subwin.ui'
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
from PySide6.QtWidgets import (QApplication, QDialog, QFrame, QLabel,
    QLineEdit, QPushButton, QSizePolicy, QVBoxLayout)

class Ui_Dialog(object):
    def setupUi(self, Dialog):
        if not Dialog.objectName():
            Dialog.setObjectName(u"Dialog")
        Dialog.resize(386, 360)
        self.verticalLayout = QVBoxLayout(Dialog)
        self.verticalLayout.setObjectName(u"verticalLayout")
        self.sub_frame1 = QFrame(Dialog)
        self.sub_frame1.setObjectName(u"sub_frame1")
        self.sub_frame1.setFrameShape(QFrame.StyledPanel)
        self.sub_frame1.setFrameShadow(QFrame.Raised)
        self.sub_sent_btn = QPushButton(self.sub_frame1)
        self.sub_sent_btn.setObjectName(u"sub_sent_btn")
        self.sub_sent_btn.setGeometry(QRect(90, 70, 75, 24))
        self.sub_recevice_text = QLineEdit(self.sub_frame1)
        self.sub_recevice_text.setObjectName(u"sub_recevice_text")
        self.sub_recevice_text.setGeometry(QRect(120, 190, 133, 21))
        self.label = QLabel(self.sub_frame1)
        self.label.setObjectName(u"label")
        self.label.setGeometry(QRect(30, 190, 81, 16))
        self.sub_sent_text = QLineEdit(self.sub_frame1)
        self.sub_sent_text.setObjectName(u"sub_sent_text")
        self.sub_sent_text.setGeometry(QRect(120, 130, 133, 21))
        self.label_2 = QLabel(self.sub_frame1)
        self.label_2.setObjectName(u"label_2")
        self.label_2.setGeometry(QRect(30, 130, 81, 16))

        self.verticalLayout.addWidget(self.sub_frame1)


        self.retranslateUi(Dialog)

        QMetaObject.connectSlotsByName(Dialog)
    # setupUi

    def retranslateUi(self, Dialog):
        Dialog.setWindowTitle(QCoreApplication.translate("Dialog", u"Dialog", None))
        self.sub_sent_btn.setText(QCoreApplication.translate("Dialog", u"\u53d1\u9001", None))
        self.label.setText(QCoreApplication.translate("Dialog", u"\u5b50\u7a97\u53e3\u63a5\u6536\uff1a", None))
        self.label_2.setText(QCoreApplication.translate("Dialog", u"\u5b50\u7a97\u53e3\u53d1\u9001\uff1a", None))
    # retranslateUi


