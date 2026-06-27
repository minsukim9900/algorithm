import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int solution(int n, int[] money) {
        int m = money.length;
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        for (int i = 0; i < m; i++) {
            int curr = money[i];
            
            for(int j = curr; j < n + 1; j++) {
                dp[j] += dp[j - curr];
                dp[j] %= MOD;
            }
        }
        
        int answer = dp[n];
        return answer;
    }
}