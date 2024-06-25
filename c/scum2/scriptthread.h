//
// Created by BlackBox on 24-6-18.
//

#ifndef SCRIPTTHREAD_H
#define SCRIPTTHREAD_H

#include <bemapiset.h>
#include <QThread>

class ScriptThread : public QThread {
    Q_OBJECT

public:
    explicit ScriptThread(QObject *parent = nullptr);

protected:
    void run() override;

    signals:
        void updateLog(const QString &text);

private:
    void simulateMouseClick();
    void simulateKeyPress(WORD key);
    void simulateMouseWheel(int delta);
};

#endif // SCRIPTTHREAD_H

