#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QThread>

class ScriptThread;

class MainWindow : public QMainWindow {
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = nullptr);
    ~MainWindow() override;

protected:
    bool eventFilter(QObject *obj, QEvent *event) override; // 事件过滤器

private:
    void setupUi(); // 方法来设置UI

    void startScript(); // 启动脚本
    void stopScript(); // 停止脚本

    ScriptThread *scriptThread; // 脚本线程
    bool listening1; // 第一个输入框的监听状态
    bool listening2; // 第二个输入框的监听状态

    private slots:
    void onButtonClicked(); // 按钮1的槽函数

};





class ScriptThread : public QThread {
    Q_OBJECT

protected:
    void run() override;

    signals:
        void updateLog(const QString &text);
};

#endif // MAINWINDOW_H
