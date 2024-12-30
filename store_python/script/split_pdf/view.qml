import QtQuick 6.6
import QtQuick.Controls 6.6
import QtQuick.Layouts 6.6

ApplicationWindow {
    visible: true
    width: 800
    height: 600
    title: "zotci"

    GridLayout {
        anchors.fill: parent
        columns: 5
        columnSpacing: 10
        rowSpacing: 10

        // 第一部分：输入框和按钮
        TextField {
            id: filePathInput1
            objectName: "filePathInput1"
            placeholderText: "点击选择一个PDF文件"
            Layout.fillWidth: true
            Layout.columnSpan: 2
            readOnly: true  // 使输入框只读，用户不能手动修改
        }

        Button {
            text: "选择文件"
            Layout.columnSpan: 1
            onClicked: {
                backend.open_file_dialog()  // 调用 Python 后端的方法来打开文件选择对话框
            }
        }

        // 第二部分：输入框和文本框
        TextField {
            id: input_page
            objectName: "input_page"
            placeholderText: "输入拆分的位置"
            Layout.fillWidth: true
            Layout.columnSpan: 1
        }
        Text {
            text: "共xx页"
            objectName: "page_long"
            verticalAlignment: Text.AlignVCenter
            Layout.columnSpan: 1
        }

        // 第三部分：左侧占两行高度的按钮，右侧上下对齐的输入框
        Button {
            text: "拆分pdf"
            Layout.columnSpan: 2
            Layout.rowSpan: 2
            Layout.fillWidth: true
            Layout.fillHeight: true
            onClicked: {
                backend.split_pdf()
            }
        }

        TextField {
            id: pdf_out_path1
            objectName: "pdf_out_path1"

            placeholderText: "输入文本..."
            Layout.columnSpan: 3
            Layout.fillWidth: true
            Layout.fillHeight: true
        }
        TextField {
            id: pdf_out_path2
            objectName: "pdf_out_path2"
            Layout.columnSpan: 3
            Layout.fillWidth: true
            Layout.fillHeight: true
        }
    }
}
