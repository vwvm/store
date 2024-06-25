#include "mainwindow.h"
#include <QVBoxLayout>
#include <QHBoxLayout>
#include <QPushButton>
#include <QLabel>
#include <QLineEdit>
#include <QKeyEvent>
#include <QDebug>
#include <QDateTime>
#include <windows.h>

#define HOTKEY_START 1
#define HOTKEY_STOP 2

MainWindow::MainWindow(QWidget *parent) : QMainWindow(parent), scriptThread(nullptr), listening1(false), listening2(false) {
    setupUi();
    GlobalHotkeyListener::getInstance()->setMainWindow(this); // 设置全局热键监听器的主窗口
    GlobalHotkeyListener::getInstance()->registerHotKeys(); // 注册全局热键
}

MainWindow::~MainWindow() {
    stopScript();
    GlobalHotkeyListener::getInstance()->unregisterHotKeys(); // 注销全局热键
}

void MainWindow::setupUi() {
    // 创建中央部件和布局
    auto *centralWidget = new QWidget(this);
    auto *mainLayout = new QVBoxLayout(centralWidget);

    // 创建标签
    auto *label = new QLabel("47(BlackBox)钓鱼脚本v1.0", centralWidget);
    label->setObjectName("label");
    mainLayout->addWidget(label);

    // 创建第一个按钮和输入框的布局
    auto *layout1 = new QHBoxLayout();
    auto *label1 = new QLabel("启动");
    auto *lineEdit1 = new QLineEdit(centralWidget);
    lineEdit1->setObjectName("lineEdit1");
    lineEdit1->setText("Shift+F1");
    auto *button1 = new QPushButton("修改", centralWidget);
    button1->setObjectName("button1");
    layout1->addWidget(label1);
    layout1->addWidget(lineEdit1);
    layout1->addWidget(button1);

    // 创建第二个按钮和输入框的布局
    auto *layout2 = new QHBoxLayout();
    auto *label2 = new QLabel("停止");
    auto *lineEdit2 = new QLineEdit(centralWidget);
    lineEdit2->setObjectName("lineEdit2");
    lineEdit2->setText("Shift+F2");
    auto *button2 = new QPushButton("修改", centralWidget);
    button2->setObjectName("button2");
    layout2->addWidget(label2);
    layout2->addWidget(lineEdit2);
    layout2->addWidget(button2);

    // 将水平布局添加到主垂直布局
    mainLayout->addLayout(layout1);
    mainLayout->addLayout(layout2);

    // 设置中央部件的布局
    centralWidget->setLayout(mainLayout);
    setCentralWidget(centralWidget);

    // 设置窗口标题和大小
    setWindowTitle("钓鱼脚本");
    resize(300, 200);

    // 连接信号和槽
    connect(button1, &QPushButton::clicked, this, &MainWindow::onButtonClicked);
    connect(button2, &QPushButton::clicked, this, &MainWindow::onButtonClicked);

    // 安装事件过滤器
    lineEdit1->installEventFilter(this);
    lineEdit2->installEventFilter(this);
}

void MainWindow::onButtonClicked() {
    auto *button = qobject_cast<QPushButton*>(sender());
    if (!button) return;

    if (button->objectName() == "button1") {
        listening1 = !listening1;
        button->setText(listening1 ? "确认" : "修改");
    } else if (button->objectName() == "button2") {
        listening2 = !listening2;
        button->setText(listening2 ? "确认" : "修改");
    }
}

bool MainWindow::eventFilter(QObject *obj, QEvent *event) {
    if (event->type() == QEvent::KeyPress) {
        auto *keyEvent = static_cast<QKeyEvent*>(event);
        if (!keyEvent) return false;

        QString outputText;
        bool hasModifier = false;

        // 检查修饰键并避免重复
        if (keyEvent->modifiers() & Qt::ControlModifier) {
            outputText += "Ctrl+";
            hasModifier = true;
        }
        if (keyEvent->modifiers() & Qt::AltModifier) {
            outputText += "Alt+";
            hasModifier = true;
        }
        if (keyEvent->modifiers() & Qt::ShiftModifier) {
            outputText += "Shift+";
            hasModifier = true;
        }

        // 处理F1-F12键和普通按键
        int key = keyEvent->key();
        if (key >= Qt::Key_F1 && key <= Qt::Key_F12) {
            outputText += QKeySequence(key).toString();
        } else if (!keyEvent->text().isEmpty() && keyEvent->text().at(0).isLetter()) {
            outputText += keyEvent->text().toUpper(); // 将字母转换为大写
        } else if (key == Qt::Key_Escape || key == Qt::Key_Tab || key == Qt::Key_Backspace || key == Qt::Key_Return) {
            outputText += QKeySequence(key).toString();
        } else if (!hasModifier) {
            // 如果没有修饰键，且按下的是普通按键
            outputText += QKeySequence(key).toString();
        }

        // 更新输入框内容
        if (listening1 && obj->objectName() == "lineEdit1") {
            if (auto *lineEdit = findChild<QLineEdit *>("lineEdit1")) {
                lineEdit->setText(outputText);
                return true;
            }
        } else if (listening2 && obj->objectName() == "lineEdit2") {
            if (auto *lineEdit = findChild<QLineEdit *>("lineEdit2")) {
                lineEdit->setText(outputText);
                return true;
            }
        }


        return true; // 事件已经处理
    }
    return QMainWindow::eventFilter(obj, event);
}

void MainWindow::startScript() {
    if (scriptThread && scriptThread->isRunning()) {
        return; // 脚本已经在运行
    }

    scriptThread = new ScriptThread(this);
    connect(scriptThread, &ScriptThread::updateLog, this, [](const QString &text) {
        qDebug() << text; // 输出日志信息
    });
    scriptThread->start();
}

void MainWindow::stopScript() {
    if (scriptThread && scriptThread->isRunning()) {
        scriptThread->requestInterruption();
        scriptThread->wait();
        delete scriptThread;
        scriptThread = nullptr;
    }
}
