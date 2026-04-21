#include <iostream>
using namespace std;

void printRectangle(int n) {
    int num = 0;

    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            cout << num + 1 << " ";

            num = (num + 1) % 9;
        }

        cout << "\n";
    }
}

int main() {
    // Please write your code here.
    int n;
    cin >> n;

    printRectangle(n);

    return 0;
}