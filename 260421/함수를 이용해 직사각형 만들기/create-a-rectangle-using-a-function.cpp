#include <iostream>
using namespace std;

void printRectangle(int n, int m) {
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++) {
            cout << 1;
        }
        cout << "\n";
    }
}

int main() {
    // Please write your code here.

    int n, m;
    cin >> n >> m;

    printRectangle(n, m);
    
    return 0;
}