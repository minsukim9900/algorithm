import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int[][] dp = new int[info.length + 1][m];
        
        for(int i = 0; i <= info.length; i++) {
            Arrays.fill(dp[i], 100_000_000);
        }
        
        dp[0][0] = 0;
        
        for(int i = 1; i < info.length + 1; i++) {
            int aCost = info[i - 1][0];
            int bCost = info[i - 1][1];
            
            for(int j = 0; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + aCost, dp[i][j]);
                
                if(j + bCost < m) {
                    dp[i][j + bCost] = Math.min(dp[i - 1][j], dp[i][j + bCost]);
                }
            }
        }
        
        
        int answer = 100_000_000;
        for(int j = 0; j < m; j++) {
            answer = Math.min(dp[info.length][j], answer);
        }
        return answer >= n ? -1 : answer;
    }
}