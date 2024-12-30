import QtQuick 2.15
import QtQuick.Controls 2.15

ApplicationWindow {
    visible: true
    width: 300
    height: 400
    title: "输入框和列表垂直排列"

    Column {
        anchors.fill: parent
        spacing: 10
        padding: 20

        TextField {
            id: inputField
            placeholderText: "请输入文本..."
            width: parent.width
        }

        ListView {
            id: listView
            width: parent.width
            height: parent.height - inputField.height - 40 // 剩余的高度给 ListView
            model: ListModel {
                ListElement { name: "Item 1" }
                ListElement { name: "Item 2" }
                ListElement { name: "Item 3" }
            }

            delegate: Item {
                width: listView.width
                height: 40

                Rectangle {
                    width: parent.width
                    height: parent.height
                    color: "lightgray"
                    radius: 5

                    Text {
                        anchors.centerIn: parent
                        text: model.name
                    }
                }
            }
        }
    }
}
