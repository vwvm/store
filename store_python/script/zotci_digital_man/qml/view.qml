import QtQuick
import QtQuick.Controls
import QtQuick.Layouts
import Qt.labs.folderlistmodel

Page {
    title: "zotci"

    ListModel {
        id: fileModel
        Component.onCompleted: {
            for (var i = 0; i < 10; i++) {
                fileModel.append({
                    digitalHumanName: "",
                    filePath: "",
                    scriptText: "",
                    outputPath1: "",
                    outputPath2: "",
                    state_label: "等待启动中。。。",
                    image_url: ""
                });
            }
        }
    }

    ScrollView {
        anchors.fill: parent

        ColumnLayout {
            anchors.fill: parent
            anchors.leftMargin: 25  // 调大左间距，可以根据需求调整数值
            spacing: 20

            Repeater {
                model: fileModel
                delegate: ColumnLayout {
                    spacing: 20

                    // 第一部分：选择源文件部分
                    Rectangle {
                        Layout.fillWidth: true
                        Layout.preferredHeight: 150
                        border.width: 1
                        radius: 5
                        color: "transparent"

                        RowLayout {
                            anchors.fill: parent
                            spacing: 10

                            Rectangle {
                                width: 120
                                height: 120
                                border.width: 1
                                radius: 5
                                color: "transparent"
                                Image {
                                    id: avatarImage
                                    objectName: "avatarImage"
                                    anchors.centerIn: parent
                                    source: model.image_url
                                    width: 100
                                    height: 100
                                    Layout.fillWidth: false
                                    fillMode: Image.PreserveAspectFit
                                }
                            }

                            ColumnLayout {
                                Layout.fillWidth: true
                                spacing: 10

                                TextField {
                                    id: digitalHumanName
                                    placeholderText: "数字人名称"
                                    Layout.fillWidth: true
                                    text: model.digitalHumanName
                                    onTextChanged: {
                                        fileModel.set(index, {digitalHumanName: text});
                                    }
                                }
                                RowLayout {
                                    spacing: 10

                                    TextField {
                                        id: filePathInput1
                                        placeholderText: "数字人视频源文件"
                                        Layout.fillWidth: true
                                        readOnly: true
                                        text: model.filePath
                                    }

                                    Button {
                                        id: selectFileButton
                                        text: "选择文件 "
                                        Layout.fillWidth: false
                                        onClicked: {
                                            backend.slot_print("点击了选择文件");
                                            backend.open_file_dialog(index);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // 第二部分：长文本输入框，占整行且三行高
                    Rectangle {
                        Layout.fillWidth: true
                        Layout.preferredHeight: 150
                        border.width: 1
                        radius: 5
                        color: "transparent"

                        ScrollView {
                            anchors {
                                left: parent.left
                                right: parent.right
                                top: parent.top
                                bottom: parent.bottom
                                margins: 1 // 设置边距以避免覆盖边框
                            }

                            TextArea {
                                id: inputScript
                                placeholderText: "请在此输入数字人文案"
                                Layout.fillWidth: true
                                Layout.fillHeight: true
                                wrapMode: TextEdit.Wrap
                                padding: 10
                                textFormat: TextEdit.PlainText
                                text: model.scriptText
                                onTextChanged: {
                                    fileModel.set(index, {scriptText: text});
                                }
                            }
                        }
                    }


                    // 第三部分：左侧位于整行的按钮，右侧上下对齐的输入框
                    Rectangle {
                        Layout.fillWidth: true
                        Layout.preferredHeight: 150
                        border.width: 1
                        radius: 5
                        color: "transparent"

                        GridLayout {
                            anchors.fill: parent
                            columns: 5
                            columnSpacing: 10
                            rowSpacing: 10


                            TextField {
                                id: outputVideoPath1
                                placeholderText: "数字人线上视频地址"
                                Layout.columnSpan: 4
                                Layout.fillWidth: true
                                text: model.outputPath1
                                onTextChanged: {
                                    fileModel.set(index, {outputPath1: text});
                                }
                            }
                            Button {
                                id: copyDigitalHumanUrlButton
                                text: "复制数值人线上视频地址"
                                Layout.columnSpan: 1
                                onClicked: {
                                    backend.copy_to_clipboard(outputVideoPath1.text);
                                }
                            }

                            Label {
                                text: "视频保存路径："
                                Layout.columnSpan: 1
                                Layout.alignment: Qt.AlignRight
                            }
                            TextField {
                                id: outputVideoPath2
                                placeholderText: "数字人视频输出路径..."
                                Layout.columnSpan: 3
                                Layout.fillWidth: true
                                text: model.outputPath2
                                onTextChanged: {
                                    fileModel.set(index, {outputPath2: text});
                                }
                            }
                            Button {
                                id: generateDigitalHumanButton2
                                text: "设置视频保存的路径"
                                Layout.columnSpan: 1
                                onClicked: {
                                    backend.slot_print("点击了选择文件");
                                    backend.set_save_file_path(index);
                                }
                            }

                            Button {
                                id: generateDigitalHumanButton
                                text: "生成数字人"
                                Layout.columnSpan: 1
                                Layout.fillHeight: true
                                onClicked: {
                                    backend.generate_digital_human_video(model);
                                }
                            }

                            Label {
                                text: "数值人生成状态："
                                Layout.columnSpan: 1
                                Layout.alignment: Qt.AlignRight
                            }
                            Label {
                                id: state_label
                                Layout.columnSpan: 3
                                Layout.fillWidth: true
                                text: model.state_label
                            }
                        }
                    }

                    // 使用 Rectangle 包裹 Canvas 并绘制双虚线
                    Rectangle {
                        Layout.fillWidth: true
                        Layout.preferredHeight: 40
                        color: "transparent" // 透明背景

                        Canvas {
                            id: doubleDashedLineCanvas
                            anchors.fill: parent

                            onPaint: {
                                var ctx = getContext("2d");
                                ctx.clearRect(0, 0, width, height);

                                // 绘制第一条虚线
                                ctx.setLineDash([6, 6]); // 6像素的线段和间隔
                                ctx.beginPath();
                                ctx.moveTo(0, height / 3); // 第一条虚线在高度的 1/3 处
                                ctx.lineTo(width, height / 3);
                                ctx.strokeStyle = "black";
                                ctx.lineWidth = 2;
                                ctx.stroke();

                                // 绘制第二条虚线
                                ctx.setLineDash([6, 6]); // 6像素的线段和间隔
                                ctx.beginPath();
                                ctx.moveTo(0, (2 * height) / 3); // 第二条虚线在高度的 2/3 处
                                ctx.lineTo(width, (2 * height) / 3);
                                ctx.strokeStyle = "black";
                                ctx.lineWidth = 2;
                                ctx.stroke();
                            }
                        }
                    }
                }
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

        onSavePathSelected: function (index, filePath) {
            backend.slot_print("设置文件夹完毕");
            // 更新 ListModel 中的文件路径
            fileModel.set(index, {outputPath2: filePath});
        }

        onSetModelValue: function (index, key, value) {
            backend.slot_print("选择文件完毕" + index + "&" + key + "&" + value);
            // 获取当前项
            let currentItem = fileModel.get(index);
            if (currentItem) {
                // 更新当前项的值
                currentItem[key] = value;
                // 使用 set() 更新 ListModel 的这一项
                fileModel.set(index, currentItem);
            }

        }

        onGenerateDigitalHumanResult: {
            outputVideoPath1.text = resultPath1;
            outputVideoPath2.text = resultPath2;
        }
    }
}
