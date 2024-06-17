#include "mainwindow.h"
#include <QVBoxLayout>
#include <QPushButton>
#include <QLabel>

MainWindow::MainWindow(QWidget *parent) : QMainWindow(parent) {
    setupUi();
}

void MainWindow::setupUi() {
    // 创建中央部件和布局
    auto *centralWidget = new QWidget(this);
    auto *layout = new QVBoxLayout(centralWidget);

    // 创建标签和按钮
    auto *label = new QLabel("这是一个标签", centralWidget);
    label->setObjectName("label");  // 设置对象名称，便于查找
    auto *button1 = new QPushButton("按钮1", centralWidget);
    auto *button2 = new QPushButton("按钮2", centralWidget);

    // 将控件添加到布局
    layout->addWidget(label);
    layout->addWidget(button1);
    layout->addWidget(button2);

    // 设置中央部件的布局
    centralWidget->setLayout(layout);
    setCentralWidget(centralWidget);

    // 设置窗口标题和大小
    setWindowTitle("手写代码示例");
    resize(300, 200);

    // 连接信号和槽
    connect(button1, &QPushButton::clicked, this, &MainWindow::onButton1Clicked);
    connect(button2, &QPushButton::clicked, this, &MainWindow::onButton2Clicked);
}

void MainWindow::onButton1Clicked() {
    // 按钮1点击事件处理
    if (auto *label = findChild<QLabel *>("label")) {
        label->setText("按钮1被点击了");
    }
}

void MainWindow::onButton2Clicked() {
    // 按钮2点击事件处理
    if (auto *label = findChild<QLabel *>("label")) {
        label->setText("按钮2被点击了");
    }
}
