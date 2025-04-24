//
// Created by BlackBox on 25-3-17.
//
#include <stdio.h>

// 自定义函数
void greet() {
    printf("Hello, World!\n");
}

// main 函数
int main() {
    int 整型 = 10;
    printf("整型的值：%d", 整型);

    greet();  // 调用自定义函数
    return 0;  // 程序正常结束
}