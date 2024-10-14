//
// Created by BlackBox on 24-9-26.
//

#ifndef COPYFILES_H
#define COPYFILES_H

#include <QApplication>
#include <QPushButton>

#include <aclapi.h>
#include <shlwapi.h>

#include <windows.h>
#include <iostream>
#include <string>
#include <filesystem>

void SafeCopyFile(const std::wstring& sourcePath, const std::wstring& destinationPath);
void MonitorDirectory(const std::wstring& directoryPath, const std::wstring& destinationPath);



#endif //COPYFILES_H
