#ifndef BROWSERAUTOMATIONAPP_H
#define BROWSERAUTOMATIONAPP_H

#include <QWidget>
#include <QLineEdit>
#include "BrowserAutomation.h"

class BrowserAutomationApp : public QWidget {
    Q_OBJECT

public:
    BrowserAutomationApp(QWidget* parent = nullptr);

    private slots:
        void onOpenBrowserClicked();

private:
    QLineEdit* urlLineEdit;
    BrowserAutomation* browserAutomation;
};

#endif // BROWSERAUTOMATIONAPP_H
