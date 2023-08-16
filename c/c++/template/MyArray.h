//
// Created by BlackBox on 14/8/2023.
//

#ifndef C___MYARRAY_H
#define C___MYARRAY_H

#include "iostream"
#include "string.h"

using namespace std;

template<class T>
class MyArray {
    template<class T1>
    friend ostream &operator<<(ostream &out, MyArray<T1> a);

private:
    T *arr;
    int size;
    int capacity;
public:
    MyArray();

    MyArray(int capacity);

    MyArray(const MyArray &ob);

    ~ MyArray();

    // 重载运算符=(深拷贝）
    MyArray<T> &operator=(const MyArray<T> &ob);


    /**
     * 插入函数
     * @param elem
     */
    void pushBack(T elem);

    void sortArray();
};


template<class T>
MyArray<T>::MyArray() {
    capacity = 5;
    size = 0;
    arr = new T[capacity];
    memset(arr, 0, sizeof(T) * capacity);
}

template<class T>
MyArray<T>::MyArray(int capacity) {
    this->capacity = capacity;
    size = 0;
    arr = new T[capacity];
    memset(arr, 0, sizeof(T) * capacity);
}

template<class T>
MyArray<T>::MyArray(const MyArray &ob) {
    capacity = ob.capacity;
    size = ob.size;
    arr = new T[capacity];
    memset(arr, 0, sizeof(T) * capacity);

    memcpy(arr, ob.arr, sizeof(T) * capacity);

}

template<class T>
MyArray<T>::~MyArray() {
    delete[] arr;
}

template<class T>
MyArray<T> &MyArray<T>::operator=(const MyArray<T> &ob) {
    // 证同测试
    if (this == &ob) { return *this; }
    // 判断源arr是否指向空间
    if (arr != NULL) {
        delete[] arr;
        arr = NULL;
    }
    capacity = ob.capacity;
    size = ob.size;
    arr = new T[capacity];
    memset(arr, 0, sizeof(T) * capacity);

    memcpy(arr, ob.arr, sizeof(T) * capacity);

    return *this;
}

template<class T>
void MyArray<T>::pushBack(T elem) {
    // 容量是否满
    if (size == capacity) {
        // 扩展容量
        capacity = 2 * capacity;
        // 申请临时空间
        T *temp = new T[capacity];

        if (arr != NULL) {
            // 将旧空间内容拷贝到新空间中
            memcpy(temp, arr, sizeof(T) * size);
            // 释放旧的空间
            delete[] arr;
        }

        arr = temp;
    }
    arr[size] = elem;
    size++;

}

template<class T>
void MyArray<T>::sortArray() {
    if (size <= 1) {
        return;
    }
    for (int i = 0; i < size - 1; ++i) {
        for (int j = 0; j < size - i - 1; ++j) {
            if (arr[j] > arr[j + 1]) {
                T temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

template<class T1>
ostream &operator<<(ostream &out, MyArray<T1> ob) {
    for (int i = 0; i < ob.size; ++i) {
        out<<ob.arr[i] << " ";
    }
    endl(out);

    return out;
}


#endif //C___MYARRAY_H
