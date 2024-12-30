import QtQuick
import QtQuick.Controls
import QtQuick.Window

Page {
    id: mainWindow
    width: 1400
    height: 800
    visible: true
    title: qsTr("QmlDock Demo")

    // 主容器，包含所有Dock窗口
    Column {
        id: dockHome
        anchors.fill: parent
        spacing: 2

        // 第一个Dock窗口
        Frame {
            id: dock1
            width: 600
            height: 550
            property string name: "dock1"
            visible: true
            Label {
                anchors.centerIn: parent
                text: "Dock 1"
            }
            Button {
                text: "Close"
                anchors.bottom: parent.bottom
                onClicked: dock1.visible = false
            }
        }

        // 第二个Dock窗口
        Frame {
            id: dock2
            width: 300
            height: 600
            property string name: "dock2"
            visible: true
            Label {
                anchors.centerIn: parent
                text: "Dock 2"
            }
            Button {
                text: "Close"
                anchors.bottom: parent.bottom
                onClicked: dock2.visible = false
            }
        }

        // 第三个Dock窗口，充当其他Dock的中心
        Frame {
            id: dock3
            width: 500
            height: 400
            property string name: "dock3"
            visible: true
            Label {
                anchors.centerIn: parent
                text: "Dock 3"
            }
        }

        // 第四个Dock窗口
        Frame {
            id: dock4
            width: 400
            height: 400
            property string name: "dock4"
            visible: true
            Label {
                anchors.centerIn: parent
                text: "Dock 4"
            }
            Button {
                text: "Close"
                anchors.bottom: parent.bottom
                onClicked: dock4.visible = false
            }
        }
    }
}

// 说明：
// 1. 使用了官方的QtQuick.Controls组件替换了自定义的Dock组件。
// 2. 将每个窗口替换为Frame组件并添加关闭按钮，确保可以显示和隐藏窗口。
// 3. 使用标准组件以便更好地与Qt官方提供的功能集成。
