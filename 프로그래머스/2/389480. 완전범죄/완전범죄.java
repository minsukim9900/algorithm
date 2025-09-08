import java.io.*;
import java.util.*;

class Solution {
    private static int K;
    private static final int INF = 100_000;
    private static int[][] infos;
    public int solution(int[][] info, int n, int m) {
        K = info.length;
        int[][] dp = new int[K + 1][m];
        
        for(int i = 0; i <= K; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][0] = 0;
        
        for(int i = 1; i <= K; i++) {
            int a = info[i - 1][0];
            int b = info[i - 1][1];
            for(int j = 0; j < m; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);
                
                if(j + b < m) {
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
                }
            }
        }
        
        int answer = INF;
        for(int i = 0; i < m; i++) {
            answer = Math.min(dp[K][i], answer);
        }
        
        return answer >= n ? -1 : answer;
    }
    
    
}