import java.io.*;
import java.util.*;

class Solution {
    
    private static int[][] dp;
    private static final int MOD = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        
        dp = new int[n + 1][m + 1];
       
        for(int i = 0; i < puddles.length; i++) {
            int r = puddles[i][1];
            int c = puddles[i][0];
            dp[r][c] = -1;
        }
        
        dp[1][1] = 1;
        for(int r = 1; r<=n; r++) {
            for(int c = 1; c<=m; c++) {
                if(dp[r][c] == -1) continue;
                if(r == 1 && c == 1) continue;
                
                if(dp[r-1][c] == -1) {
                    dp[r][c] = dp[r][c-1] % MOD;
                }else if(dp[r][c-1] == -1) {
                    dp[r][c] = dp[r-1][c] % MOD;
                }else {
                    dp[r][c] = ((dp[r][c-1] % MOD) + (dp[r-1][c] % MOD)) % MOD;
                }
                
            }
        }
               
        
        int answer = dp[n][m] % MOD;
        return answer;
    }
}