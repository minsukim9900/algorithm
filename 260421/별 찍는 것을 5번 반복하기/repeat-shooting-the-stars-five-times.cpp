#include <iostream>
using namespace std;

void print5Stars() {
    for(int i = 0; i < 5; i++) {
        for(int j = 0; j < 10; j++) {
            cout << "*";
        }
        cout << "\n";
    }
}

int main() {
    // Please write your code here.

    print5Stars();
    return 0;
}