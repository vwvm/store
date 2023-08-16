//
// Created by BlackBox on 10/8/2023.
//

#include <cstring>
#include <iostream>
#include "../headers/learn1.h"

using namespace std;

class Data2 {
public:
    char *name;
public:
    Data2() {}

    Data2(char *str) {
        name = new char[strlen(str) + 1];
        strcpy(name, str);
        cout << " name:" << name << endl;
    }
};