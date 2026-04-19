#include <iostream>
using namespace std;

int main() {
    // Please write your code here.

    int N;
    cin >> N;

    char state = 'F';

    if(N >= 90) {
        state = 'A';
    } else if(N >= 80) {
        state = 'B';
    } else if(N >= 70) {
        state = 'C';
    } else if(N >= 60) {
        state = 'D';
    }

    cout << state;

    return 0;
}