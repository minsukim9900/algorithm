import java.util.*;

class Solution {
    private static int MOD = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n + 1][m + 1];
        
        for (int i = 0; i < puddles.length; i++) {
            int r = puddles[i][1];
            int c = puddles[i][0];
            
            dp[r][c] = -1;
        }
        
        dp[1][1] = 1;
        
        for (int r = 1; r < n + 1; r++) {
            for (int c = 1; c < m + 1; c++) {
                if (dp[r][c] != 0) {
                    continue;
                }
                
                int up = dp[r - 1][c] == -1 ? 0 : dp[r - 1][c] % MOD;
                int left = dp[r][c - 1] == -1 ? 0 : dp[r][c - 1] % MOD;
                
                dp[r][c] = up + left;
                dp[r][c] %= MOD;
            }
        }
        
        return dp[n][m];
    }
}