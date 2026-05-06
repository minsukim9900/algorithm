import java.util.*;

class Solution {
    
    private static final int MOD = 1_000_000_007;
    
    public long solution(int n) {
        
        long[] dp = new long[n + 1];
        if (n % 2 == 0 && n > 0) {
            dp[2] = 3;
            
            for(int i = 4; i < n + 1; i += 2) {
                dp[i] = dp[i - 2] * dp[2] + 2;
                
                for(int j = 2; j <= i - 4; j += 2) {
                    dp[i] += dp[j] * 2;
                }
                
                dp[i] %= MOD;
            }
        }
        
        return dp[n];
    }
}