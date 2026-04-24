#include <iostream>

using namespace std;
int getGcd(int n, int m) {
    if(m == 0) {
        return n;
    }

    return getGcd(m, n % m);
}

int n, m;

int main() {
    cin >> n >> m;

    if (m > n) {
        int temp = n;
        n = m;
        m = temp;
    }

    int gcd = getGcd(n, m);

    cout << (n * m) / gcd;

    return 0;
}