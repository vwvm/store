#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

class MainWindow : public QMainWindow {
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = nullptr);
    ~MainWindow() override = default; // 使用 = default

private:
    void setupUi(); // 方法来设置UI

    private slots:
        void onButton1Clicked(); // 按钮1的槽函数
    void onButton2Clicked(); // 按钮2的槽函数
};

#endif // MAINWINDOW_H
