cmake_minimum_required(VERSION 3.26)
project(c__)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

add_executable(c__ main.cpp
        c/fun.c
        c/fun.h
)
