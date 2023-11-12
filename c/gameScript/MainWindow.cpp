//
// Created by BlackBox on 2023/11/12.
//

#include "MainWindow.h"
#include <QPushButton>
#include <QLabel>
#include <QApplication>

MainWindow::MainWindow() {
    // 创建一个按钮
    button = new QPushButton("Click me", this);
    connect(button, &QPushButton::clicked, this, &MainWindow::onButtonClicked);

    // 创建一个标签
    label = new QLabel("Hello, World!", this);
    label->move(100, 50);

    // 设置窗口大小
    resize(300, 200);
}

void MainWindow::onButtonClicked() {
    // 当按钮被点击时调用
    qApp->quit();
}

