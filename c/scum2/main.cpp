#include <QApplication>
#include "mainwindow.h"
#include <ntddkbd.h>
#include <ntddmou.h>


int main(int argc, char* argv[])
{

    QApplication app(argc, argv);

    MainWindow window;
    window.show();

    return QApplication::exec();
}
