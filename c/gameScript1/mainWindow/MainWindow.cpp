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
    resize(1100, 600);

    // 创建一个下拉框
    comboBox = new QComboBox(this);

    // 一个示例数组
    QStringList items = {
            "k47", "k43", "k41", "k37", "k31", "k29", "k23", "k19", "k17", "k13", "k11", "k7", "k5", "k3", "k2"
    };
    kData = {1000000 , 999000 , 1001000 , 1010000 , 1020000 , 1500000 , 1020000 , 1010000 , 1010000 , 1020000 };
    dataMap.insert("k47", 998000);
    dataMap.insert("k43", 950000);
    dataMap.insert("k41", 900000);
    dataMap.insert("k37", 850000);
    dataMap.insert("k31", 800000);
    dataMap.insert("k29", 750000);
    dataMap.insert("k23", 700000);
    dataMap.insert("k19", 650000);
    dataMap.insert("k17", 600000);
    dataMap.insert("k13", 550000);
    dataMap.insert("k11", 500000);
    dataMap.insert("k7", 450000);
    dataMap.insert("k5", 400000);
    dataMap.insert("k3", 350000);
    dataMap.insert("k2", 300000);

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

    mainLayout->addLayout(layout);

    // 将布局设置到主窗口或某个容器控件中
    auto *centralWidget = new QWidget(this);
    centralWidget->setLayout(mainLayout);
    this->setCentralWidget(centralWidget);

    // 设置标题行
    QStringList headers = {"名称", "目标距离", "攻击距离", "速度", "到达所需天数", "最终攻击距离",
                           "出发年", "出发日", "到达年", "到达日",};
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

    // 调整大小和位置
    // tableWidget->setGeometry(10, 50, 1080, 400);  // x, y, width, height

    onComboBoxIndexChanged("k47");

    // 连接下拉框的信号到槽函数
    connect(comboBox, &QComboBox::currentTextChanged, this, &MainWindow::onComboBoxIndexChanged);
    connect(tableWidget, &QTableWidget::itemChanged, this, &MainWindow::calculateDaysAndAttackDistance);

}


void MainWindow::onComboBoxIndexChanged(const QString &text) {
    // 检查是否有与当前选项对应的数据
    if (dataMap.contains(text)) {
        int data = dataMap.value(text);
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

    if (column != 1 && column != 2 && column != 3) {
        return;
    }

    // 先断开信号与槽的连接，以防止无限循环
    disconnect(tableWidget, &QTableWidget::itemChanged, this, &MainWindow::calculateDaysAndAttackDistance);

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

            cout << "目标距离" << targetDistance << "攻击距离" << attackDistance << "速度" << speed << endl;

            if (speed <= 0) {
                // 确保速度大于0，以避免除以零的错误
                continue;
            }

            int daysRequired = targetDistance / speed; // 计算所需天数
            int remainingDistance = targetDistance % speed; // 计算剩余距离

            // 如果剩余距离小于攻击距离，天数 +1
            if (remainingDistance > 0 && remainingDistance < attackDistance) {
                daysRequired += 1;
                remainingDistance = 10; // 最终攻击距离变为10
            } else {
                // 否则，最终攻击距离为余下的距离
                remainingDistance = (remainingDistance > 0) ? remainingDistance : speed;
            }

            cout << "到达所需天数" << daysRequired << "最终攻击距离" << remainingDistance << endl;

            // 更新 "到达所需天数" 和 "最终攻击距离" 列
            tableWidget->setItem(row, 4, new QTableWidgetItem(QString::number(daysRequired)));
            tableWidget->setItem(row, 5, new QTableWidgetItem(QString::number(remainingDistance)));

            cout << "设置完成" << endl;
        }
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
