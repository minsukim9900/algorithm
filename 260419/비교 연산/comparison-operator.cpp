#include <iostream>
using namespace std;

int main() {
    // Please write your code here.

    int A, B;

    cin >> A >> B;
    
    int C = 0;
    int D = 0;
    int E = 0;
    int F = 0;
    int G = 0;
    int H = 0;

    if(A >= B) {
        C = 1;
    }

    if (A > B) {
        D = 1;
    }

    if (B >= A) {
        E = 1;
    }

    if (B > A) {
        F = 1;
    }

    if (A == B) {
        G = 1;
    }

    if (A != B) {
        H = 1;
    }

    cout << C << "\n" << D << "\n" << E << "\n" << F << "\n" << G << "\n" << H;
    
    return 0;
}