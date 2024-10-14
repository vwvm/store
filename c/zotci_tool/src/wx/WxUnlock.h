//
// Created by BlackBox on 24-9-26.
//

#ifndef WXUNLOCK_H
#define WXUNLOCK_H

#include <QApplication>
#include <QPushButton>

#include <aclapi.h>
#include <shlwapi.h>

#include <windows.h>
#include <iostream>

#include <string>
#include <filesystem>

void enableMultiWeChat();

std::string GetWeChatInstallPath();
bool runNewWeChat(const std::string& weChatPath);
void createAndShowMainWindow(const std::string& weChatPath);

#endif //WXUNLOCK_H
