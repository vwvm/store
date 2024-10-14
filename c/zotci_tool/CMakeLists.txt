cmake_minimum_required(VERSION 3.28)

project(zotci_tool LANGUAGES CXX)

# 设置C++标准和自动MOC、RCC、UIC支持
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC ON)

# 设置 Qt 的安装路径
set(QT_ROOT "F:/ProgramData/Qt/6.7.2/mingw_64")

# 设置 CMake 前缀路径
set(CMAKE_PREFIX_PATH "${QT_ROOT}")

# 查找 Qt6 库
find_package(Qt6 REQUIRED COMPONENTS Core Gui Widgets Qml Quick QuickDialogs2)

# 设置 Qt 策略 QTP0001
qt_policy(SET QTP0001 NEW)

# 添加可执行文件
add_executable(${PROJECT_NAME}
        main.cpp
        src/wx/WxUnlock.cpp
        src/wx/WxUnlock.h
        src/copy_files/CopyFiles.cpp
        src/copy_files/CopyFiles.h
        src/file_hook/WatchData.cpp
        src/file_hook/Win32FSHook.cpp
        resources/resources.qrc
        resources/resources.rc
        src/main_window/MainWindow.cpp
        src/main_window/MainWindow.h
        src/qpdf/SplitPDF.cpp
        src/qpdf/SplitPDF.h
)

# 设置 qpdf 的包含目录
include_directories("F:\\Users\\BlackBox\\project\\C\\qpdf\\include")

# 链接 Qt 库
target_link_libraries(${PROJECT_NAME} PRIVATE
        Qt6::Core
        Qt6::Gui
        Qt6::Widgets
        Qt6::Quick
        Qt6::Qml
        Qt6::QuickDialogs2
)

# 添加 QML 模块
qt_add_qml_module(${PROJECT_NAME}
        URI ${PROJECT_NAME}
        VERSION 1.0
        QML_FILES resources/qml/main.qml
)

# 查找 windeployqt 工具
find_program(WINDEPLOYQT_EXECUTABLE windeployqt HINTS "${QT_ROOT}/bin")

# 如果找到 windeployqt，则添加自定义命令
if(WINDEPLOYQT_EXECUTABLE)
    add_custom_command(TARGET ${PROJECT_NAME} POST_BUILD
            COMMAND "${WINDEPLOYQT_EXECUTABLE}" "--compiler-runtime" "$<TARGET_FILE:${PROJECT_NAME}>"
            COMMENT "正在运行 windeployqt..."
    )
else()
    message(WARNING "未找到 windeployqt 工具，运行时可能会缺少必要的 DLL。")
endif()

# 自动复制 MinGW 运行时 DLL 文件
if (WIN32 AND CMAKE_CXX_COMPILER_ID STREQUAL "GNU")
    # 获取编译器所在目录
    get_filename_component(COMPILER_DIR "${CMAKE_CXX_COMPILER}" DIRECTORY)
    set(MINGW_BIN_DIR "${COMPILER_DIR}")

    # 定义需要复制的 DLL 文件列表
    set(MINGW_DLLS libgcc_s_seh-1.dll libstdc++-6.dll libwinpthread-1.dll)

    foreach(DLL ${MINGW_DLLS})
        add_custom_command(TARGET ${PROJECT_NAME} POST_BUILD
                COMMAND ${CMAKE_COMMAND} -E copy_if_different
                "${MINGW_BIN_DIR}/${DLL}"
                "$<TARGET_FILE_DIR:${PROJECT_NAME}>"
                COMMENT "正在复制 ${DLL} 到输出目录...")
    endforeach()
endif()
