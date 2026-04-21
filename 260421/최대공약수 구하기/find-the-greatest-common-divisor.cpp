#include <iostream>
using namespace std;

int cal(int n, int m) {
    if(m == 0) {
        return n;
    } else {
        return cal(m, n % m);
    }
}

int main() {
    // Please write your code here.

    int n, m;

    cin >> n >> m;

    if(n < m) {
        int temp = n;
        n = m;
        m = temp;
    }

    cout << cal(n, m);
    return 0;
}