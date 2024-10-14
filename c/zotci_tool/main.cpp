#include <filesystem>
#include <iostream>
#include <string>
#include <QVBoxLayout>
#include <QLineEdit>
#include <QFileDialog>
#include <QQmlApplicationEngine>

#include "src/wx/WxUnlock.h"

int wx_multi()
{
    std::wstring directoryToMonitor = L"C:\\Users\\BlackBox\\Desktop\\a";
    std::wstring destinationDirectory = L"C:\\Users\\BlackBox\\Desktop\\b";

    std::string weChatPath = GetWeChatInstallPath();
    if (!weChatPath.empty()) {
        std::cout << "WeChat is installed at: " << weChatPath << std::endl;
    } else {
        std::cout << "WeChat path not found." << std::endl;
    }
    weChatPath.append("\\WeChat.exe");

    // 启动一个新的微信
    runNewWeChat(weChatPath);
    enableMultiWeChat();

    // 调用创建和显示主窗口的方法
    createAndShowMainWindow(weChatPath);


    return 0;
}

int pdf_disassemble()
{

    return 0;
}

int main(int argc, char* argv[])
{

    QGuiApplication app(argc, argv);

    QQmlApplicationEngine engine;

    // 加载 QML 文件+6
    const QUrl url(QStringLiteral("qrc:/resources/qml/main.qml"));
    QObject::connect(&engine, &QQmlApplicationEngine::objectCreated,
                     &app, [url](QObject *obj, const QUrl &objUrl) {
        if (!obj && url == objUrl)
            QCoreApplication::exit(-1); // 如果 QML 加载失败则退出
    }, Qt::QueuedConnection);

    engine.load(url);

    // 检查 rootObjects，如果为空则退出
    if (engine.rootObjects().isEmpty())
        return -1;

    return app.exec();

    QApplication app(argc, argv);
    return QApplication::exec();


    return 0;
}
