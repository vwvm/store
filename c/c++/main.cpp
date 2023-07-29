#include <iostream>
#include <random>
#include <ctime>

#define MY_ADD(a, b) a * b

void test01() {
    volatile int data = 10;
    srand(time((NULL)));
    std::cout << rand() << std::endl;
    std::cout << MY_ADD(10 + 10, 20 + 20) << std::endl;
    std::cout << sizeof(int **) << std::endl;
    std::cout << data << std::endl;
}

void test02(){
    int p = 1123123;
    std::cout << p << std::endl;
}

v

int main() {
    
    test02();
    return 0;
}

