#include <filesystem>
#include <iostream>
#include <string>

#include "src/wx/WxUnlock.h"
#include <QApplication>
#include <QWebEngineView>
#include <QWebChannel>


int wx_multi()
{
    std::wstring directoryToMonitor = L"C:\\Users\\BlackBox\\Desktop\\a";
    std::wstring destinationDirectory = L"C:\\Users\\BlackBox\\Desktop\\b";

    std::string weChatPath = GetWeChatInstallPath();
    if (!weChatPath.empty())
    {
        std::cout << "WeChat is installed at: " << weChatPath << std::endl;
    }
    else
    {
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

// 定义一个可以与 JavaScript 交互的 C++ 对象
class MyObject : public QObject
{
    Q_OBJECT

public slots:
    void receiveMessageFromJS(const QString& message)
    {
        qDebug() << "Received message from JavaScript:" << message;
        sendMessageToJS("Hello from C++"); // 调用发送方法
    }

signals:
    void sendMessageToJS(const QString& message);

public:
    MyObject(QObject* parent = nullptr) : QObject(parent)
    {
    }
};


int main(int argc, char* argv[])
{
    QApplication app(argc, argv);

    QWebEngineView view;
    view.load(QUrl("qrc:/index")); // 确保文件路径正确
    view.resize(1024, 750);

    QWebChannel* channel = new QWebChannel(&view);
    MyObject* myObject = new MyObject();
    channel->registerObject(QStringLiteral("myObject"), myObject);
    view.page()->setWebChannel(channel); // 将 QWebChannel 连接到页面

    view.show();

    return QApplication::exec();
    // QGuiApplication app(argc, argv);
    //
    // QQmlApplicationEngine engine;
    //
    // // 加载 QML 文件
    // const QUrl url(QStringLiteral("qrc:/resources/qml/main.qml"));
    // QObject::connect(&engine, &QQmlApplicationEngine::objectCreated,
    //                  &app, [url](QObject *obj, const QUrl &objUrl) {
    //     if (!obj && url == objUrl)
    //         QCoreApplication::exit(-1); // 如果 QML 加载失败则退出
    // }, Qt::QueuedConnection);
    //
    // engine.load(url);
    //
    // // 检查 rootObjects，如果为空则退出
    // if (engine.rootObjects().isEmpty())
    //     return -1;
    //
    // return app.exec();
}

#include "main.moc"