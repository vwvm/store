//
// Created by BlackBox on 2023/11/12.
//

#include "MainWindow.h"
#include "iostream"
#include <QApplication>
#include <QStyledItemDelegate>
#include <QTableWidget>
#include <QMainWindow>
#include <QComboBox>
#include <QLineEdit>
#include <QIntValidator>
#include <QHBoxLayout>
#include <QVBoxLayout>

using namespace std;

// 自定义委托类
class IntegerDelegate : public QStyledItemDelegate {
public:
    QWidget *
    createEditor(QWidget *parent, const QStyleOptionViewItem &option, const QModelIndex &index) const override {
        auto *editor = new QLineEdit(parent);
        // 设置只接受整数的验证器
        editor->setValidator(new QIntValidator(editor));
        return editor;
    }
};

MainWindow::MainWindow(QWidget *parent) {
    // 设置窗口大小
    resize(1300, 700);

    // 创建一个下拉框
    comboBox = new QComboBox(this);

    // 一个示例数组
    QStringList items = {
            "k47", "k43", "k41", "k37", "k31", "k29", "k23", "k19", "k17", "k13", "k11", "k7", "k5", "k3", "k2", "母星"
    };

    kData = {1000000, 999000, 1001000, 1010000, 1020000, 1500000, 1020000, 1010000, 1010000, 1020000};
    speedData = {300, 300, 300, 150, 200, 150, 100, 120, 50, 130};
    lineDamageData = {};
    dataMap.insert("k47", {998000, 10, 5000});
    dataMap.insert("k43", {950000, 100, 10000});
    dataMap.insert("k41", {900000, 200, 40000});
    dataMap.insert("k37",
                   {850000, 300, 80000});
    dataMap.insert("k31",
                   {800000, 400, 200000});
    dataMap.insert("k29",
                   {750000, 500, 400000});
    dataMap.insert("k23",
                   {700000, 750, 800000});
    dataMap.insert("k19",
                   {650000, 1000, 1400000});
    dataMap.insert("k17",
                   {600000, 1250, 2560000});
    dataMap.insert("k13",
                   {550000, 1500, 5120000});
    dataMap.insert("k11",
                   {500000, 1750, 10240000});
    dataMap.insert("k7",
                   {450000, 2000, 20480000});
    dataMap.insert("k5",
                   {400000, 2250, 30720000});
    dataMap.insert("k3",
                   {350000, 2500, 46080000});
    dataMap.insert("k2",
                   {300000, 2750, 69120000});
    dataMap.insert("母星",
                   {0, 5000, 100000000});


    // 添加数组数据到下拉框
    comboBox->addItems(items);
    // 设置下拉框的位置和大小
    // comboBox->setGeometry(10, 10, 100, 30); // x, y, width, height

    // 创建水平布局
    auto *layout = new QHBoxLayout();
    // 创建垂直布局
    auto *mainLayout = new QVBoxLayout();
    // 将下拉框添加到布局中
    layout->addWidget(comboBox);

    // 创建并添加 "现在年" 标签和输入框
    auto *currentYearLabel = new QLabel("现在年", this);
    auto *currentYearEdit = new QLineEdit(this);
    layout->addWidget(currentYearLabel);
    layout->addWidget(currentYearEdit);

    // 创建并添加 "现在日" 标签和输入框
    auto *currentDayLabel = new QLabel("现在日", this);
    auto *currentDayEdit = new QLineEdit(this);
    layout->addWidget(currentDayLabel);
    layout->addWidget(currentDayEdit);

    // 创建并添加 "现在日" 标签和输入框
    auto *currentLineOfDefenseLabel = new QLabel("防线血量", this);
    currentLineOfDefenseEdit = new QLineEdit(this);
    layout->addWidget(currentLineOfDefenseLabel);
    layout->addWidget(currentLineOfDefenseEdit);

    // 水平布局添加到垂直布局中
    mainLayout->addLayout(layout);

    // 创建结果的水平布局,并左对齐
    // 最终结果
    auto *resultLayout = new QHBoxLayout();
    resultLayout->setAlignment(Qt::AlignLeft);
    auto *resultTitle = new QLabel("结果:");
    resultValue = new QLabel("不知道能不能打过,把数据填完试试");
    resultLayout->addWidget(resultTitle);
    resultLayout->addWidget(resultValue);
    mainLayout->addLayout(resultLayout);



    // 将布局设置到主窗口或某个容器控件中
    auto *centralWidget = new QWidget(this);
    centralWidget->setLayout(mainLayout);
    this->setCentralWidget(centralWidget);

    // 设置标题行
    QStringList headers = {"名称", "目标距离", "攻击距离", "速度", "到达所需天数",
                           "最终攻击距离", "出发年(顺序)", "出发日(延时)", "到达年", "到达日",
                           "血量", "承受几次不死", "飞船攻击伤害"};
    int rowCount = 10;
    int columnCount = static_cast<int>(headers.size());
    // 创建表格控件
    tableWidget = new QTableWidget(rowCount, columnCount, this);

    // 添加到布局中
    mainLayout->addWidget(tableWidget);

    // 设置头
    tableWidget->setHorizontalHeaderLabels(headers);

    // 控制只能输入整数
    for (int j = 1; j < columnCount; ++j) {
        auto *integerDelegate = new IntegerDelegate();
        tableWidget->setItemDelegateForColumn(j, integerDelegate);
    }

    // 设置固定名称
    QStringList names = {"联合", "虚空", "反判", "天启", "木木", "破峰", "洪荒", "曙光", "星穹", "永恒"};
    for (int i = 0; i < names.size(); ++i) {
        tableWidget->setItem(i, 0, new QTableWidgetItem(names[i]));
    }

    // 设置默认攻击距离
    for (int i = 0; i < speedData.size(); ++i) {
        tableWidget->setItem(i, 2, new QTableWidgetItem(QString::number(speedData[i])));
    }

    // 调整大小和位置
    // tableWidget->setGeometry(10, 50, 1080, 400);  // x, y, width, height

    onComboBoxIndexChanged("k47");

    // 连接信号到槽函数
    connect(comboBox, &QComboBox::currentTextChanged, this, &MainWindow::onComboBoxIndexChanged);
    connect(tableWidget, &QTableWidget::itemChanged, this, &MainWindow::calculateDaysAndAttackDistance);

}


void MainWindow::onComboBoxIndexChanged(const QString &text) {
    // 检查是否有与当前选项对应的数据
    if (dataMap.contains(text)) {
        int data = dataMap.value(text)[0];
        int hp = dataMap.value(text)[2];
        currentLineOfDefenseEdit->setText(QString::number(hp));
        cout << kData[1] + data;
        int column = 1;
        for (int i = 0; i < kData.size(); ++i) {
            // 填写数据到表格的第 i 行，目标距离列
            tableWidget->setItem(i, column, new QTableWidgetItem(QString::number(kData[i] - data)));
        }
    }
}

void MainWindow::onButtonClicked() {

}

void MainWindow::calculateDaysAndAttackDistance(QTableWidgetItem *item) {

    int column = item->column();
    // 先断开信号与槽的连接，以防止无限循环
    disconnect(tableWidget, &QTableWidget::itemChanged, this, &MainWindow::calculateDaysAndAttackDistance);

    // 计算距离
    if (column == 1 || column == 2 || column == 3) {

        for (int row = 0; row < tableWidget->rowCount(); ++row) {
            QTableWidgetItem *targetDistanceItem = tableWidget->item(row, 1); // "目标距离" 列
            QTableWidgetItem *attackDistanceItem = tableWidget->item(row, 2); // "攻击距离" 列
            QTableWidgetItem *speedItem = tableWidget->item(row, 3); // "速度" 列

            // 确保所有需要的数据都存在
            if (targetDistanceItem && attackDistanceItem && speedItem &&
                !targetDistanceItem->text().isEmpty() &&
                !attackDistanceItem->text().isEmpty() &&
                !speedItem->text().isEmpty()) {


                int targetDistance = targetDistanceItem->text().toInt();
                int attackDistance = attackDistanceItem->text().toInt();
                int speed = speedItem->text().toInt();

                if (speed <= 0) {
                    // 确保速度大于0，以避免除以零的错误
                    continue;
                }

                int daysRequired = targetDistance / speed; // 计算所需天数
                int remainingDistance = targetDistance % speed; // 计算剩余距离

                // 如果剩余距离大于攻击距离，天数 +1
                if (remainingDistance > 0 && remainingDistance > attackDistance) {
                    daysRequired += 1;
                    remainingDistance = 10; // 最终攻击距离变为10
                } else {
                    // 否则，最终攻击距离为余下的距离
                    remainingDistance = (remainingDistance > 0) ? remainingDistance : speed;
                }

                // 更新 "到达所需天数" 和 "最终攻击距离" 列
                tableWidget->setItem(row, 4, new QTableWidgetItem(QString::number(daysRequired)));
                tableWidget->setItem(row, 5, new QTableWidgetItem(QString::number(remainingDistance)));

            }
        }

        // 计算出发日子
        int minValue = 0;

        // 出发顺序数组
        QVector<int> goOn(tableWidget->rowCount(),0);
        // 出发日子数组
        QVector<int> goOn2(tableWidget->rowCount(),0);
        // 所需要天数数组
        QVector<int> goOnList(tableWidget->rowCount(),0);
        // 最终攻击距离数组
        QVector<int> goOnList2(tableWidget->rowCount(),0);

        // 读取数据到数组
        for (int i = 0; i < tableWidget->rowCount(); i++) {
            QTableWidgetItem *targetDistanceItem = tableWidget->item(i, 4); // "所需要天数" 列
            QTableWidgetItem *attackDistanceItem = tableWidget->item(i, 5); // "最终攻击距离" 列
            if (targetDistanceItem && !targetDistanceItem->text().isEmpty()) {
                int value = targetDistanceItem->text().toInt();
                int value2 = attackDistanceItem->text().toInt();

                if (value < minValue) {
                    minValue = value;
                }
                goOnList[i] = value;
                goOnList2[i] = value2;
            }
        }

        // 生成临时数组,并拷贝数据
        QVector<int> indices2(goOnList2.size());
        for (int i = 0; i < goOnList2.size(); i++) {
            indices2[i] = goOnList2[i];
        }

        // 从大到小排序下标数组
        sort(indices2.rbegin(), indices2.rend());

        cout<< indices2.toList().

        // 去重
        unique(indices2.rbegin(), indices2.rend());

        // 根据下标数组输出原始数组中从大到小的元素
        for (int i = 0; i < indices2.size(); i++) {
            for (int j = 0; j < goOn.size(); ++j) {
                if (goOnList2[j] == indices2[i]) {
                    goOn[j] = i;
                    goOn2[j] = minValue - goOnList[j];
                }
            }
        }

        int value = 0;
        for (int i = 0; i < tableWidget->rowCount(); ++i) {
            if (value > goOn2[i]) {
                value = goOn2[i];
            }
        }
        for (int i = 0; i < tableWidget->rowCount(); ++i) {
            goOn2[i] = goOn2[i] - value;
        }
        // 更新数据
        // 更新 "次序" 和 "延时" 列
        for (int row = 0; row < tableWidget->rowCount(); ++row) {
            tableWidget->setItem(row, 6, new QTableWidgetItem(QString::number(goOn[row])));
            tableWidget->setItem(row, 7, new QTableWidgetItem(QString::number(goOn2[row])));

        }
    }

    // 计算承受次数
    if (column == 10) {
        for (int row = 0; row < tableWidget->rowCount(); ++row) {
            // "血量" 列
            QTableWidgetItem *hpItem = tableWidget->item(row, 10);
            int bossAtk = dataMap.value(comboBox->currentText())[1];
            // 确保所有需要的数据都存在
            if (hpItem && !hpItem->text().isEmpty()) {

                int targetDistance = hpItem->text().toInt();

                // 计算次数
                int count = targetDistance / bossAtk;
                // 求余
                int hpDistance = targetDistance % bossAtk;

                // 最后一下攻击刚好消失
                if (hpDistance == 0) {
                    count--;
                }

                // 跟新数据
                tableWidget->setItem(row, 11, new QTableWidgetItem(QString::number(count)));

            }
        }
    }

    // 计数是否会能击破
    // 剩余血量
    int bossResidueHp = dataMap.value(comboBox->currentText())[2];
    for (int row = 0; row < tableWidget->rowCount(); ++row) {
        QTableWidgetItem *atkItem = tableWidget->item(row, 12);
        QTableWidgetItem *countItem = tableWidget->item(row, 11);
        QTableWidgetItem *hpItem = tableWidget->item(row, 10);
        if (atkItem && !atkItem->text().isEmpty() && countItem && !countItem->text().isEmpty()) {
               bossResidueHp = bossResidueHp - atkItem->text().toInt() * (countItem->text().toInt() + 1);
        }
    }

    if (bossResidueHp <= 0) {
        resultValue->setText(QString("恭喜,成功击破防线"));
    } else {
        resultValue->setText(QString("还剩下") + QString::number(bossResidueHp) + QString("hp哦"));
    }



    // 链接槽
    connect(tableWidget, &QTableWidget::itemChanged, this, &MainWindow::calculateDaysAndAttackDistance);
}



// void MainWindow::createDatabaseConnection() {
//     QSqlDatabase db = QSqlDatabase::addDatabase("QSQLITE");
//     db.setDatabaseName("your_database_name.db");
//
//     if (!db.open()) {
//         qDebug() << "Error: connection with database failed:" << db.lastError();
//     } else {
//         qDebug() << "Database: connection ok";
//     }
// }
//
// void MainWindow::createTable() {
//     QSqlQuery query;
//     if (!query.exec("CREATE TABLE IF NOT EXISTS my_table ("
//                     "id INTEGER PRIMARY KEY AUTOINCREMENT, "
//                     "name TEXT, "
//                     "speed INTEGER, "
//                     "day INTEGER)")) {
//         qDebug() << "Error: table creation failed:" << query.lastError();
//     }
// }
//
// void MainWindow::insertData(const QString &name, int speed) {
//     QSqlQuery query;
//     query.prepare("INSERT INTO my_table (name, speed, /* ... */) "
//                   "VALUES (:name, :speed, /* ... */)");
//     query.bindValue(":name", name);
//     query.bindValue(":speed", speed);
//     // ... 绑定其他参数 ...
//
//     if (!query.exec()) {
//         qDebug() << "Error: data insert failed:" << query.lastError();
//     }
// }
