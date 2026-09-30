import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int n = B.length;
        int answer = 0;
        
        int aIdx = 0;
        int bIdx = 0;
        
        while (aIdx < n && bIdx < n) {
            int a = A[aIdx];
            int b = B[bIdx];
            
            if (b > a) {
                aIdx++;
                answer++;
            }
            
            bIdx++;
        }
        
        return answer;
    }
}