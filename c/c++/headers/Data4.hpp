//
// Created by BlackBox on 11/8/2023.
//

#ifndef C___DATA4_HPP
#define C___DATA4_HPP

#include "iostream"

using namespace std;

template<class T1, class T2>
class Data4 {
private:
    T1 a;
    T2 b;
public:
    Data4();

    Data4(T1 a, T2 b);

    void showData();
};


template<class T1, class T2>
Data4<T1, T2>::Data4() {
    cout << "无参构造" << endl;
}

template<class T1, class T2>
Data4<T1, T2>::Data4(T1 a, T2 b) {
    this->a = a;
    this->b = b;
    cout << "有参构造" << endl;
}

template<class T1, class T2>
void Data4<T1, T2>::showData() {
    cout << a << "  " << b << endl;
}


#endif //C___DATA4_HPP
