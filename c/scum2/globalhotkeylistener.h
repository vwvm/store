#ifndef GLOBALHOTKEYLISTENER_H
#define GLOBALHOTKEYLISTENER_H

#include <QAbstractNativeEventFilter>
#include <QApplication>

class MainWindow;

class GlobalHotkeyListener : public QAbstractNativeEventFilter {
public:
    static GlobalHotkeyListener* getInstance();
    void setMainWindow(MainWindow *mainWindow);

    void registerHotKeys(); // 注册全局热键
    void unregisterHotKeys(); // 注销全局热键

protected:
    bool nativeEventFilter(const QByteArray &eventType, void *message, qintptr *result) override; // 确保方法签名正确

private:
    GlobalHotkeyListener() : mainWindow(nullptr) {}
    static GlobalHotkeyListener *instance;
    MainWindow *mainWindow;
};

#endif // GLOBALHOTKEYLISTENER_H
