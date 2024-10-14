#include "mainwindow.h"
#include <QFile>
#include <QVBoxLayout>
#include <QDebug>
#include <QApplication>
MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent)
{


    // 将加载的 UI 设置为主窗口的 central widget
}

MainWindow::~MainWindow() {
    // 析构函数自动处理删除动态分配的 QWidget
}