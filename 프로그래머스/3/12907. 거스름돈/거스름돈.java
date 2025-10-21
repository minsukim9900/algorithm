import java.io.*;
import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        
        dp[0] = 1;
        for(int i = 0; i < money.length; i++) {
            for(int j = money[i]; j <= n; j++) {
                dp[j] = dp[j] + dp[j - money[i]];
                dp[j] %= MOD;
            }
        }
        
        return dp[n];
    }
}