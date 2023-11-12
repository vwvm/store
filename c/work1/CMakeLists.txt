cmake_minimum_required(VERSION 3.26)
project(work1)

set(CMAKE_CXX_STANDARD 17)

add_library(work1 library.cpp
        window/SerialInterface.cpp
        window/SerialInterface.h
        library.h
        d.cpp
        d.h)

TARGET_LINK_LIBRARIES(work1 dl)