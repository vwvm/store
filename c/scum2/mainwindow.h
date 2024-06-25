#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QThread>
#include "scriptthread.h"
#include "globalhotkeylistener.h"

class ScriptThread;

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget* parent = nullptr);
    ~MainWindow() override;

    void startScript(); // 启动脚本
    void stopScript(); // 停止脚本

protected:
    bool eventFilter(QObject* obj, QEvent* event) override; // 事件过滤器

private:
    void setupUi(); // 方法来设置UI


    ScriptThread* scriptThread; // 脚本线程
    bool listening1; // 第一个输入框的监听状态
    bool listening2; // 第二个输入框的监听状态

private slots:
    void onButtonClicked(); // 按钮1的槽函数
};

#endif // MAINWINDOW_H
