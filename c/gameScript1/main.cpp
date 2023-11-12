#include <QApplication>
#include "./mainWindow/MainWindow.h"

int main(int argc, char *argv[]) {
    // 解决乱码问题
    system("chcp 65001");

    QApplication app(argc, argv);

    MainWindow window;
    window.show();

    return app.exec();
}
