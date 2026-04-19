#include <iostream>
using namespace std;

int main() {
    // Please write your code here.


    int A, B;

    cin >> A >> B;

    int answerA = 0;
    int answerB = 0;

    if(A < B) {
        answerA = 1;
    }

    if(A == B) {
        answerB = 1;
    }

    cout << answerA << " " << answerB;
    return 0;
}