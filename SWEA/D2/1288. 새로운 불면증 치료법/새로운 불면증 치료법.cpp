#include <iostream>

using namespace std;

int main() {
    int T;

    cin >> T;

    for (int t = 1; t <= T; t++) {
        int N;
        cin >> N;

        int fullMask = (1 << 10 )- 1;

        int mask = 0;

        for (int i = 1; i < 101; i++) {
            long number = (long) N * i;

            while (number > 0) {
                int x = number % 10;
                mask |= (1 << x);
                number /= 10;
            }

            if (mask == fullMask) {
                cout << "#" << t << " " << (i * N) << "\n";
                break;
            }
        }
    }
    return 0;
}