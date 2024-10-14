//
// Created by BlackBox on 24-9-26.
//

#include "WxUnlock.h"

#include <QFileDialog>
#include <QLineEdit>
#include <QVBoxLayout>

// 解锁_WeChat_App_Instance_Identity_Mutex_Name
void enableMultiWeChat()
{
    HANDLE hMutex = CreateMutexW(NULL, FALSE, L"_WeChat_App_Instance_Identity_Mutex_Name");
    SID_IDENTIFIER_AUTHORITY SIDAuthWorld = SECURITY_WORLD_SID_AUTHORITY;
    PSID pEveryoneSID = NULL;
    char szBuffer[4096] = {0};
    PACL pAcl = (PACL)szBuffer;

    AllocateAndInitializeSid(&SIDAuthWorld, 1, SECURITY_WORLD_RID, 0, 0, 0, 0, 0, 0, 0, &pEveryoneSID);
    InitializeAcl(pAcl, sizeof(szBuffer), ACL_REVISION);
    AddAccessDeniedAce(pAcl, ACL_REVISION, MUTEX_ALL_ACCESS, pEveryoneSID);
    SetSecurityInfo(hMutex, SE_KERNEL_OBJECT, DACL_SECURITY_INFORMATION, NULL, NULL, pAcl, NULL);
}

/*使用CreateProcess启动程序，返回启动结果*/
bool runNewWeChat(const std::string& weChatPath) {
    STARTUPINFOA si;
    PROCESS_INFORMATION pi;
    ZeroMemory(&si, sizeof(si));
    si.cb = sizeof(si);
    ZeroMemory(&pi, sizeof(pi));

    // 尝试启动微信进程
    if (CreateProcessA(
            weChatPath.c_str(),
            nullptr, // 命令行参数
            nullptr, // 进程句柄不继承
            nullptr, // 线程句柄不继承
            FALSE,   // 不继承句柄
            0,       // 没有创建标志
            nullptr, // 使用父进程的环境块
            nullptr, // 使用父进程的当前目录
            &si,     // 指向 STARTUPINFO 结构体的指针
            &pi      // 指向 PROCESS_INFORMATION 结构体的指针
    )) {
        CloseHandle(pi.hProcess);
        CloseHandle(pi.hThread);
        return true;
    } else {
        DWORD dwError = GetLastError();
        std::cerr << "runNewWeChat failed with error code: " << dwError << std::endl;
        return false;
    }
}

std::string GetWeChatInstallPath()
{
    HKEY hKey;
    const char* regPath = "SOFTWARE\\Tencent\\WeChat";
    const char* valueName = "InstallPath";
    char installPath[MAX_PATH];
    DWORD pathLen = sizeof(installPath);

    // 打开注册表键
    if (RegOpenKeyExA(HKEY_CURRENT_USER, regPath, 0, KEY_READ, &hKey) == ERROR_SUCCESS)
    {
        // 查询值
        if (RegQueryValueExA(hKey, valueName, NULL, NULL, (LPBYTE)installPath, &pathLen) == ERROR_SUCCESS)
        {
            RegCloseKey(hKey);
            return std::string(installPath);
        }
        RegCloseKey(hKey);
    }
    return "";
}

void createAndShowMainWindow(const std::string& weChatPath) {
    int argc = 0;
    const char *argv[] = {};

    QApplication application(argc, const_cast<char **>(argv));

    QApplication::setApplicationName("zotci-tool");
    QApplication::setWindowIcon(QIcon(":/resources/zotci.png"));

    QWidget window;
    QVBoxLayout layout(&window);

    QLineEdit lineEdit;
    lineEdit.setText(QString::fromStdString(weChatPath));
    layout.addWidget(&lineEdit);

    QPushButton browseButton("选择路径", nullptr);
    QObject::connect(&browseButton, &QPushButton::clicked, [&lineEdit]() {
        QString path = QFileDialog::getOpenFileName(nullptr, "选择WeChat路径");
        if (!path.isEmpty()) {
            lineEdit.setText(path);
        }
    });
    layout.addWidget(&browseButton);

    QPushButton button("单击启动多开微信,之后可以多次点击微信启动", nullptr);
    QObject::connect(&button, &QPushButton::clicked, &application, &QApplication::quit);
    layout.addWidget(&button);

    window.resize(300, 200);
    window.show();

    runNewWeChat(weChatPath);

    // 进入事件循环
    QApplication::exec();
}

