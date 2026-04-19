#include <iostream>
#include <string>

using namespace std;

int main() {
    int n;
    cin >> n;

    string state = "water";

    if(n < 0) {
        state = "ice";
    } else if(n >= 100) {
        state = "vapor";
    }

    cout << state;
    return 0;
}