//
// Created by BlackBox on 2023/11/12.
//

#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QWidget>

class QPushButton;
class QLabel;

class MainWindow : public QWidget {
Q_OBJECT
public:
    MainWindow();

private slots:
    void onButtonClicked();

private:
    QPushButton *button;
    QLabel *label;
};

#endif // MAINWINDOW_H

