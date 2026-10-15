import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        
        long positive = 0;
        long negative = 0;
        long answer = Long.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            long num = (i & 1) == 1 ? -(long) sequence[i] : sequence[i];
            
            positive = Math.max(num, positive + num);
            negative = Math.max(-num, negative - num);
            
            answer = Math.max(answer, Math.max(positive, negative));
        }
        
        return answer;
    }
}