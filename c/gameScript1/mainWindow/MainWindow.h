//
// Created by BlackBox on 2023/11/12.
//

#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include "iostream"

#include <QTableWidget>
#include <QMainWindow>
#include <QComboBox>
#include <QLineEdit>
#include <QIntValidator>
#include <QLabel>
#include <QHBoxLayout>
#include <QVBoxLayout>

// #include <QSqlDatabase>
// #include <QSqlQuery>
// #include <QSqlError>
// #include <QDebug>

class QPushButton;

class QLabel;

class MainWindow : public QMainWindow  {
Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = nullptr);

private slots:
    // 选择防线的槽函数
    void onComboBoxIndexChanged(const QString &text);
    // 表的计算槽函数
    void calculateDaysAndAttackDistance(QTableWidgetItem *item);
    void onButtonClicked();
    // void createDatabaseConnection();
    // void createTable();
    // void insertData(const QString& name, int speed);

private:
    // QPushButton *button;
    // QLabel *label;
    QComboBox *comboBox;
    QTableWidget *tableWidget;
    QMap<QString, int> dataMap;
    QVector<int> kData;


};

#endif // MAINWINDOW_H

