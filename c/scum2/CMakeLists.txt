cmake_minimum_required(VERSION 3.28)
project(scum2)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC ON)

# 添加WDK路径
set(WDK_PATH "C:/Program Files (x86)/Windows Kits/10")

# 包含WDK头文件路径
include_directories("${WDK_PATH}/Include/10.0.26100.0")
include_directories("${WDK_PATH}/Include/10.0.26100.0/shared")
include_directories("${WDK_PATH}/Include/10.0.26100.0/ucrt")
include_directories("${WDK_PATH}/Include/10.0.26100.0/um")
include_directories("${WDK_PATH}/Include/10.0.26100.0/winrt")



# 链接WDK库文件路径
link_directories("${WDK_PATH}/Lib/10.0.26100.0")

set(CMAKE_PREFIX_PATH "F:/ProgramData/Qt/6.7.1/mingw_64")

find_package(Qt6 COMPONENTS
        Core
        Gui
        Widgets
        REQUIRED)

add_executable(scum2 main.cpp
        mainwindow.cpp
        mainwindow.h
        globalhotkeylistener.cpp
        globalhotkeylistener.h
        scriptthread.cpp
        scriptthread.h
        main.h
)
target_link_libraries(scum2
        Qt::Core
        Qt::Gui
        Qt::Widgets
        kmdf
)

if (WIN32 AND NOT DEFINED CMAKE_TOOLCHAIN_FILE)
    set(DEBUG_SUFFIX)
    if (MSVC AND CMAKE_BUILD_TYPE MATCHES "Debug")
        set(DEBUG_SUFFIX "d")
    endif ()
    set(QT_INSTALL_PATH "${CMAKE_PREFIX_PATH}")
    if (NOT EXISTS "${QT_INSTALL_PATH}/bin")
        set(QT_INSTALL_PATH "${QT_INSTALL_PATH}/..")
        if (NOT EXISTS "${QT_INSTALL_PATH}/bin")
            set(QT_INSTALL_PATH "${QT_INSTALL_PATH}/..")
        endif ()
    endif ()
    if (EXISTS "${QT_INSTALL_PATH}/plugins/platforms/qwindows${DEBUG_SUFFIX}.dll")
        add_custom_command(TARGET ${PROJECT_NAME} POST_BUILD
                COMMAND ${CMAKE_COMMAND} -E make_directory
                "$<TARGET_FILE_DIR:${PROJECT_NAME}>/plugins/platforms/")
        add_custom_command(TARGET ${PROJECT_NAME} POST_BUILD
                COMMAND ${CMAKE_COMMAND} -E copy
                "${QT_INSTALL_PATH}/plugins/platforms/qwindows${DEBUG_SUFFIX}.dll"
                "$<TARGET_FILE_DIR:${PROJECT_NAME}>/plugins/platforms/")
    endif ()
    foreach (QT_LIB Core Gui Widgets)
        add_custom_command(TARGET ${PROJECT_NAME} POST_BUILD
                COMMAND ${CMAKE_COMMAND} -E copy
                "${QT_INSTALL_PATH}/bin/Qt6${QT_LIB}${DEBUG_SUFFIX}.dll"
                "$<TARGET_FILE_DIR:${PROJECT_NAME}>")
    endforeach (QT_LIB)
endif ()
