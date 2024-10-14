//
// Created by BlackBox on 24-9-26.
//

#include "CopyFiles.h"
#include <windows.h>
#include <iostream>
#include <filesystem>
#include <thread>

namespace fs = std::filesystem;

void SafeCopyFile(const std::wstring& sourcePath, const std::wstring& destinationPath) {
    try {
        if (fs::exists(sourcePath)) {
            std::wstring tempPath = destinationPath + L".tmp";
            fs::copy(sourcePath, tempPath, fs::copy_options::overwrite_existing);
            fs::rename(tempPath, destinationPath);
            std::wcout << L"File copied from " << sourcePath << L" to " << destinationPath << std::endl;
        }
    } catch (const fs::filesystem_error& e) {
        std::wcerr << L"Copy failed: " << e.what() << std::endl;
    }
}

void MonitorDirectory(const std::wstring& directoryPath, const std::wstring& destinationPath) {
    HANDLE hDir = CreateFile(
        directoryPath.c_str(),
        FILE_LIST_DIRECTORY,
        FILE_SHARE_READ | FILE_SHARE_WRITE | FILE_SHARE_DELETE,
        NULL,
        OPEN_EXISTING,
        FILE_FLAG_BACKUP_SEMANTICS,
        NULL
    );

    if (hDir == INVALID_HANDLE_VALUE) {
        std::cerr << "Failed to open directory for monitoring." << std::endl;
        return;
    }

    char buffer[1024];
    DWORD bytesReturned;

    while (true) {
        if (ReadDirectoryChangesW(
            hDir,
            &buffer,
            sizeof(buffer),
            TRUE,
            FILE_NOTIFY_CHANGE_FILE_NAME | FILE_NOTIFY_CHANGE_LAST_WRITE,
            &bytesReturned,
            NULL,
            NULL
        )) {
            FILE_NOTIFY_INFORMATION* pNotify;
            int offset = 0;
            do {
                pNotify = (FILE_NOTIFY_INFORMATION*)((char*)buffer + offset);
                std::wstring fileName(pNotify->FileName, pNotify->FileNameLength / sizeof(WCHAR));
                std::wstring fullPath = directoryPath + L"\\" + fileName;
                std::wstring destinationFullPath = destinationPath + L"\\" + fileName;

                if (pNotify->Action == FILE_ACTION_ADDED || pNotify->Action == FILE_ACTION_MODIFIED) {
                    SafeCopyFile(fullPath, destinationFullPath);
                }

                offset += pNotify->NextEntryOffset;

            } while (pNotify->NextEntryOffset != 0);
        }
    }

    CloseHandle(hDir);
}