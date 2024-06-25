#include "globalhotkeylistener.h"
#include "mainwindow.h"
#include <windows.h>

#define HOTKEY_START 1
#define HOTKEY_STOP 2

GlobalHotkeyListener* GlobalHotkeyListener::instance = nullptr;

GlobalHotkeyListener* GlobalHotkeyListener::getInstance() {
    if (!instance) {
        instance = new GlobalHotkeyListener();
    }
    return instance;
}

void GlobalHotkeyListener::setMainWindow(MainWindow *mainWindow) {
    this->mainWindow = mainWindow;
}

bool GlobalHotkeyListener::nativeEventFilter(const QByteArray &eventType, void *message, qintptr *result) { // 确保方法签名正确
    MSG *msg = static_cast<MSG*>(message);
    if (msg->message == WM_HOTKEY) {
        int hotkeyId = msg->wParam;
        if (mainWindow) {
            if (hotkeyId == HOTKEY_START) {
                mainWindow->startScript();
            } else if (hotkeyId == HOTKEY_STOP) {
                mainWindow->stopScript();
            }
        }
        return true;
    }
    return false;
}

void GlobalHotkeyListener::registerHotKeys() {
    RegisterHotKey(NULL, HOTKEY_START, MOD_SHIFT, VK_F1);
    RegisterHotKey(NULL, HOTKEY_STOP, MOD_SHIFT, VK_F2);
    qApp->installNativeEventFilter(this);
}

void GlobalHotkeyListener::unregisterHotKeys() {
    UnregisterHotKey(NULL, HOTKEY_START);
    UnregisterHotKey(NULL, HOTKEY_STOP);
    qApp->removeNativeEventFilter(this);
}
