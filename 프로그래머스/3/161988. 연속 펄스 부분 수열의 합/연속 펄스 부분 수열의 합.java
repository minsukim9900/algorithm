import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        long[][] dp = new long[2][n];
        
        dp[0][0] = sequence[0];
        dp[1][0] = -1 * sequence[0];
        
        for (int i = 1; i < n; i++) {
            int state = (i & 1) == 1 ? -1 : 1;
            
            int num = sequence[i] * state;
            dp[0][i] = Math.max(num, dp[0][i - 1] + num);
            
            num *= -1;
            
            dp[1][i] = Math.max(num, dp[1][i - 1] + num);
        }
        
        long answer = Long.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, Math.max(dp[0][i], dp[1][i]));
        }
        
        return answer;
    }
}