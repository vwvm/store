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

void test02() {
    int p = 1123123;
    std::cout << p << std::endl;
}

void test03() {
    int num = 100;
    std::cout << &num << std::endl;
    short *p = (short *) &num;

    std::cout << p << std::endl;
    char *c = (char *) &num;
    std::cout << c << std::endl;

    std::cout << sizeof(char) << std::endl;
    std::cout << sizeof(short) << std::endl;
    std::cout << sizeof(int) << std::endl;
    std::cout << sizeof(long) << std::endl;
    std::cout << sizeof(long long) << std::endl;
    std::cout << sizeof(double) << std::endl;
}

void test04() {
    int arr[] = {1, 2, 3, 4, 5};\
    int *p = arr + 3;
    std::cout << p[1] << std::endl;
}

void test05() {
    char *arr[] = {"123", "234", "345", "456"};
    std::cout << sizeof(arr) / sizeof(arr[0]) << std::endl;
    std::cout << arr[2][2] << std::endl;
}

void test06() {
    int arr[] = {1, 2, 3, 5};
    int (*p)[4] = &arr;
    std::cout << (*p)[2] << std::endl;
}

int myAdd(int a, int b) {
    return a + b;
}

void test07() {
    int (*p)(int a, int b) = NULL;
    std::cout << "sizeof = " << sizeof(p) << std::endl;
    p = myAdd;

}

struct Student {
    int age;
    char name[32];
};

void test08() {
    Student student = Student{1, 89};
    printf("%s", student.name);
}

struct compress {
    unsigned char a:2;
    // 另起一个存储单元
    unsigned char :0;
    // 无意义位段
    char :3;
    unsigned char b:2;
    unsigned char c:4;
};
using namespace std;

void test09() {
    cout << sizeof(compress) << endl;
}

int main() {
    test09();
    return 0;
}

