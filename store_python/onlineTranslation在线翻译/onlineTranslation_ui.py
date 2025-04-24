# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'onlineTranslation_ui.ui'
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
from PySide6.QtWidgets import (QApplication, QComboBox, QFrame, QHBoxLayout,
    QLabel, QPushButton, QRadioButton, QSizePolicy,
    QTextEdit, QVBoxLayout, QWidget)

class Ui_Form(object):
    def setupUi(self, Form):
        if not Form.objectName():
            Form.setObjectName(u"Form")
        Form.resize(800, 600)
        self.verticalLayout_6 = QVBoxLayout(Form)
        self.verticalLayout_6.setObjectName(u"verticalLayout_6")
        self.horizontalLayout_3 = QHBoxLayout()
        self.horizontalLayout_3.setObjectName(u"horizontalLayout_3")
        self.frame = QFrame(Form)
        self.frame.setObjectName(u"frame")
        self.frame.setStyleSheet(u"#frame{\n"
"border: 1px solid green;\n"
"}")
        self.frame.setFrameShape(QFrame.StyledPanel)
        self.frame.setFrameShadow(QFrame.Raised)
        self.verticalLayout_2 = QVBoxLayout(self.frame)
        self.verticalLayout_2.setObjectName(u"verticalLayout_2")
        self.verticalLayout = QVBoxLayout()
        self.verticalLayout.setObjectName(u"verticalLayout")
        self.label = QLabel(self.frame)
        self.label.setObjectName(u"label")

        self.verticalLayout.addWidget(self.label)

        self.horizontalLayout = QHBoxLayout()
        self.horizontalLayout.setObjectName(u"horizontalLayout")
        self.radioButton_auto = QRadioButton(self.frame)
        self.radioButton_auto.setObjectName(u"radioButton_auto")
        self.radioButton_auto.setChecked(True)

        self.horizontalLayout.addWidget(self.radioButton_auto)

        self.radioButton_ch = QRadioButton(self.frame)
        self.radioButton_ch.setObjectName(u"radioButton_ch")

        self.horizontalLayout.addWidget(self.radioButton_ch)

        self.radioButton_en = QRadioButton(self.frame)
        self.radioButton_en.setObjectName(u"radioButton_en")

        self.horizontalLayout.addWidget(self.radioButton_en)


        self.verticalLayout.addLayout(self.horizontalLayout)

        self.textEdit = QTextEdit(self.frame)
        self.textEdit.setObjectName(u"textEdit")
        self.textEdit.setStyleSheet(u"#textEdit{\n"
"border: 1px solid green;\n"
"}")

        self.verticalLayout.addWidget(self.textEdit)

        self.pushButton = QPushButton(self.frame)
        self.pushButton.setObjectName(u"pushButton")
        self.pushButton.setStyleSheet(u"QPushButton{\n"
"broder: 1px solid green\n"
"}")

        self.verticalLayout.addWidget(self.pushButton)


        self.verticalLayout_2.addLayout(self.verticalLayout)


        self.horizontalLayout_3.addWidget(self.frame)

        self.frame_3 = QFrame(Form)
        self.frame_3.setObjectName(u"frame_3")
        self.frame_3.setMinimumSize(QSize(3, 0))
        self.frame_3.setMaximumSize(QSize(5, 16777215))
        self.frame_3.setStyleSheet(u"QFrame{\n"
"border: 1px solid green;\n"
"background: green;\n"
"}")
        self.frame_3.setFrameShape(QFrame.StyledPanel)
        self.frame_3.setFrameShadow(QFrame.Raised)

        self.horizontalLayout_3.addWidget(self.frame_3)

        self.frame_2 = QFrame(Form)
        self.frame_2.setObjectName(u"frame_2")
        self.frame_2.setStyleSheet(u"QFrame{\n"
"border: 1px solid green;\n"
"}")
        self.frame_2.setFrameShape(QFrame.StyledPanel)
        self.frame_2.setFrameShadow(QFrame.Raised)
        self.verticalLayout_5 = QVBoxLayout(self.frame_2)
        self.verticalLayout_5.setObjectName(u"verticalLayout_5")
        self.comboBox = QComboBox(self.frame_2)
        self.comboBox.addItem("")
        self.comboBox.addItem("")
        self.comboBox.addItem("")
        self.comboBox.setObjectName(u"comboBox")

        self.verticalLayout_5.addWidget(self.comboBox)

        self.textEdit_3 = QTextEdit(self.frame_2)
        self.textEdit_3.setObjectName(u"textEdit_3")

        self.verticalLayout_5.addWidget(self.textEdit_3)


        self.horizontalLayout_3.addWidget(self.frame_2)


        self.verticalLayout_6.addLayout(self.horizontalLayout_3)

        self.frame_5 = QFrame(Form)
        self.frame_5.setObjectName(u"frame_5")
        self.frame_5.setMinimumSize(QSize(0, 5))
        self.frame_5.setMaximumSize(QSize(123456, 5))
        self.frame_5.setStyleSheet(u"QFrame{\n"
"border: 3px solid green;\n"
"background: green;\n"
"}")
        self.frame_5.setFrameShape(QFrame.StyledPanel)
        self.frame_5.setFrameShadow(QFrame.Raised)

        self.verticalLayout_6.addWidget(self.frame_5)

        self.textEdit_4 = QTextEdit(Form)
        self.textEdit_4.setObjectName(u"textEdit_4")
        self.textEdit_4.setMinimumSize(QSize(0, 5))
        self.textEdit_4.setStyleSheet(u"QTextEdit{\n"
"border: 1px solid green;\n"
"\n"
"}")

        self.verticalLayout_6.addWidget(self.textEdit_4)


        self.retranslateUi(Form)

        QMetaObject.connectSlotsByName(Form)
    # setupUi

    def retranslateUi(self, Form):
        Form.setWindowTitle(QCoreApplication.translate("Form", u"Form", None))
        self.label.setText(QCoreApplication.translate("Form", u"\u8f93\u5165\u8bed\u8a00", None))
        self.radioButton_auto.setText(QCoreApplication.translate("Form", u"\u81ea\u52a8\u8bc6\u522b", None))
        self.radioButton_ch.setText(QCoreApplication.translate("Form", u"\u6c49\u8bed", None))
        self.radioButton_en.setText(QCoreApplication.translate("Form", u"\u82f1\u8bed", None))
        self.pushButton.setText(QCoreApplication.translate("Form", u"\u7ffb\u8bd1", None))
        self.comboBox.setItemText(0, QCoreApplication.translate("Form", u"\u81ea\u52a8\u8bc6\u522b", None))
        self.comboBox.setItemText(1, QCoreApplication.translate("Form", u"\u6c49\u8bed", None))
        self.comboBox.setItemText(2, QCoreApplication.translate("Form", u"\u82f1\u8bed", None))

        self.textEdit_4.setHtml(QCoreApplication.translate("Form", u"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0//EN\" \"http://www.w3.org/TR/REC-html40/strict.dtd\">\n"
"<html><head><meta name=\"qrichtext\" content=\"1\" /><meta charset=\"utf-8\" /><style type=\"text/css\">\n"
"p, li { white-space: pre-wrap; }\n"
"hr { height: 1px; border-width: 0; }\n"
"li.unchecked::marker { content: \"\\2610\"; }\n"
"li.checked::marker { content: \"\\2612\"; }\n"
"</style></head><body style=\" font-family:'Microsoft YaHei UI'; font-size:9pt; font-weight:400; font-style:normal;\">\n"
"<p style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">\u663e\u793a\u66f4\u591a</p></body></html>", None))
    # retranslateUi

