#include <iostream>
using namespace std;

int main() {
    // Please write your code here.

    int n;
    cin >> n;

    string state = "no";

    if(n >= 3000) {
        state = "book";
    } else if(n >= 1000) {
        state = "mask";
    }

    cout << state;

    return 0;
}