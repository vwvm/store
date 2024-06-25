//
// Created by BlackBox on 24-6-18.
//

#include "scriptthread.h"
#include <QDateTime>
#include <windows.h>

ScriptThread::ScriptThread(QObject *parent) : QThread(parent) {}

void ScriptThread::run() {
    while (!isInterruptionRequested()) {
        QString currentTime = QDateTime::currentDateTime().toString("yyyy-MM-dd hh:mm:ss");
        emit updateLog(currentTime);

        // 模拟滚轮滚动
        simulateMouseWheel(120);  // 向上滚动
        simulateMouseWheel(-120); // 向下滚动

        QThread::sleep(1); // 每秒输出一次当前时间并模拟按键和鼠标点击
    }
}

void ScriptThread::simulateMouseClick() {
    INPUT input = {0};
    input.type = INPUT_MOUSE;
    input.mi.dwFlags = MOUSEEVENTF_LEFTDOWN;
    SendInput(1, &input, sizeof(INPUT));

    ZeroMemory(&input, sizeof(INPUT));
    input.type = INPUT_MOUSE;
    input.mi.dwFlags = MOUSEEVENTF_LEFTUP;
    SendInput(1, &input, sizeof(INPUT));
}

void ScriptThread::simulateKeyPress(WORD key) {
    INPUT input = {0};
    input.type = INPUT_KEYBOARD;
    input.ki.wVk = key;
    SendInput(1, &input, sizeof(INPUT));

    ZeroMemory(&input, sizeof(INPUT));
    input.type = INPUT_KEYBOARD;
    input.ki.wVk = key;
    input.ki.dwFlags = KEYEVENTF_KEYUP;
    SendInput(1, &input, sizeof(INPUT));
}

void ScriptThread::simulateMouseWheel(int delta) {
    INPUT input = {0};
    input.type = INPUT_MOUSE;
    input.mi.dwFlags = MOUSEEVENTF_WHEEL;
    input.mi.mouseData = delta;
    SendInput(1, &input, sizeof(INPUT));
}

