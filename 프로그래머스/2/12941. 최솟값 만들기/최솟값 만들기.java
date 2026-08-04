import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        
        int N = A.length;
        for (int i = 0; i < N; i++) {
            int x = A[i];
            int y = B[N - 1 - i];
            
            answer += (x * y);
        }

        return answer;
    }
}