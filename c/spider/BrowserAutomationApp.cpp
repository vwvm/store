#include "BrowserAutomationApp.h"
#include <QVBoxLayout>
#include <QPushButton>
#include <QLabel>
#include <QDebug>

BrowserAutomationApp::BrowserAutomationApp(QWidget* parent) : QWidget(parent) {
    QVBoxLayout* layout = new QVBoxLayout(this);

    QLabel* label = new QLabel("Enter URL:", this);
    layout->addWidget(label);

    urlLineEdit = new QLineEdit(this);
    layout->addWidget(urlLineEdit);

    QPushButton* openBrowserButton = new QPushButton("Open Browser", this);
    layout->addWidget(openBrowserButton);

    browserAutomation = new BrowserAutomation("path_to_chromedriver"); // Update with your ChromeDriver path

    connect(openBrowserButton, &QPushButton::clicked, this, &BrowserAutomationApp::onOpenBrowserClicked);

    setLayout(layout);
}

void BrowserAutomationApp::onOpenBrowserClicked() {
    QString url = urlLineEdit->text();
    if (url.isEmpty()) {
        qDebug() << "URL is empty!";
        return;
    }
    browserAutomation->openUrl(url.toStdString());
}
