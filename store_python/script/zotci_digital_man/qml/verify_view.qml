import QtQuick
import QtQuick.Controls
import QtQuick.Layouts
import Qt.labs.folderlistmodel

Page {
    title: "zotci"

    // 使用 Column 来确保内容居中对齐
    Column {
        anchors.centerIn: parent // 将内容完全居中
        spacing: 20 // 两行之间的间距

        // 标题文本
        Text {
            text: "请输入您的验证信息："
            font.pixelSize: 24
        }

        // 输入框用于输入验证内容
        TextField {
            id: inputTextField
            placeholderText: "输入内容..."
            width: 200
            font.pixelSize: 20
        }


        // 验证按钮
        Button {
            text: "验证"
            font.pixelSize: 20
            onClicked: {
                backend.verify_input(inputTextField.text) // 调用 Python 后端的验证方法
            }
        }
    }

    // 连接到 Python 后端的方法，通过 Qt 的信号槽机制进行通信
    Connections {
        target: backend

        onFilePathSelected: function (index, filePath) {
            backend.slot_print("选择文件完毕");
            // 更新 ListModel 中的文件路径
            fileModel.set(index, {filePath: filePath});
        }

        onGenerateDigitalHumanResult: {
            outputVideoPath1.text = resultPath1;
            outputVideoPath2.text = resultPath2;
        }
    }
}
