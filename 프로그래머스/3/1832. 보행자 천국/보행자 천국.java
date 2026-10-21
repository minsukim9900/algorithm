import java.util.*;

class Solution {
    final int MOD = 20170805;
    
    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2];
        
        dp[0][0][0] = 1;
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (cityMap[r][c] == 1) {
                    continue;
                }
                
                if (r > 0) {
                    if (cityMap[r - 1][c] == 2) {
                        dp[r][c][0] = dp[r - 1][c][0];
                    } else {
                        dp[r][c][0] = (dp[r - 1][c][0] + dp[r - 1][c][1]) % MOD;
                    }
                }
                
                if (c > 0) {
                    if (cityMap[r][c - 1] == 2) {
                        dp[r][c][1] = dp[r][c - 1][1];
                    } else {
                        dp[r][c][1] = (dp[r][c - 1][0] + dp[r][c - 1][1]) % MOD;
                    }
                }
            }
        }
        
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}