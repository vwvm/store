import QtQuick
import QtQuick.Controls
import QtQuick.Window

// 主窗口
Window {
    visible: true
    width: 400
    height: 300
    title: "基础交互示例"

    // 垂直布局
    Column {
        anchors.centerIn: parent
        spacing: 20
        width: 300

        // 输入框
        TextField {
            id: inputField
            placeholderText: "输入一些文字..."
            width: parent.width
        }

        // 发送按钮
        Button {
            text: "发送给Python"
            width: parent.width
            onClicked: {
                // 发送输入框内容到Python
                backend.fromQml(inputField.text)
                inputField.text = ""  // 清空输入框
            }
        }

        // 显示Python返回的消息
        Text {
            id: responseText
            text: "等待消息..."
            color: "blue"
            font.bold: true
        }
    }

    // 接收Python发送的消息
    Connections {
        target: backend
        function onMessageToQml(text) {
            responseText.text = text  // 更新显示
        }
    }
}
