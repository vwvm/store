import QtQuick 2.15
import QtQuick.Controls 2.15
import QtQuick.Window 2.15

Window {
    visible: true
    width: 400
    height: 300
    title: qsTr("Basic Qt Quick Controls Example")

    Column {
        spacing: 20
        anchors.centerIn: parent

        Label {
            text: "Enter your name:"
            font.pointSize: 14
            horizontalAlignment: Text.AlignHCenter
            anchors.horizontalCenter: parent.horizontalCenter
        }

        TextField {
            id: nameInput
            placeholderText: "Type your name here"
            width: 200
            horizontalAlignment: Text.AlignHCenter
            anchors.horizontalCenter: parent.horizontalCenter
        }

        Button {
            text: "Submit"
            anchors.horizontalCenter: parent.horizontalCenter
            onClicked: {
                if (nameInput.text !== "") {
                    resultLabel.text = "Hello, " + nameInput.text + "!"
                } else {
                    resultLabel.text = "Please enter your name."
                }
            }
        }

        Label {
            id: resultLabel
            text: ""
            font.pointSize: 14
            color: "blue"
            horizontalAlignment: Text.AlignHCenter
            anchors.horizontalCenter: parent.horizontalCenter
        }
    }
}
