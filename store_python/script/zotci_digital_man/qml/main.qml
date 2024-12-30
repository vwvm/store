import QtQuick
import QtQuick.Controls

ApplicationWindow {
    visible: true
    width: 800
    height: 600
    title: "zotci"

    Loader {
        id: pageLoader
        anchors.fill: parent
        // source: "verify_view.qml"
        source: "view.qml"
        // source: "test.qml"
    }

    Connections {
        target: backend

        onSwitchPage: function (path) {
            pageLoader.source = path;

        }

    }
}
