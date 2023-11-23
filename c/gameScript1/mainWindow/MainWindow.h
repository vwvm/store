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
    // 每条防线的的数据 QVector: 距离 攻击 血量
    QMap<QString, QVector<int>> dataMap;
    // 飞船初始防线距离
    QVector<int> kData;
    // 飞船初始攻击
    QVector<int> speedData;
    // 防线伤害
    QVector<int> lineDamageData;
    QLineEdit *currentLineOfDefenseEdit;

    //最终结果值
    QLabel *resultValue;


};

#endif // MAINWINDOW_H

