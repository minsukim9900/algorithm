import java.util.*;

class Solution {
    public int solution(int[] a) {
        int N = a.length;
        
        int[] left = new int[N];
        int[] right = new int[N];
        
        left[0] = a[0];
        right[N - 1] = a[N - 1];
        
        for(int i = 1; i < N; i++) {
            left[i] = Math.min(left[i - 1], a[i]);
            right[N - 1 - i] = Math.min(right[N - i], a[N - 1 - i]);
        }
        
        int answer = 2;
        
        for(int i = 1; i < N - 1; i++) {
            if (left[i] < a[i] && right[i] < a[i]) {
                continue;
            }
            
            answer++;
        }
        return answer;
    }
}