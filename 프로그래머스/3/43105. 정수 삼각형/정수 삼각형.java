import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int N = triangle.length;
        int M = triangle[N - 1].length;
        int[][] dp = new int[N][M];
        
        dp[0][0] = triangle[0][0];
        
        for (int r = 1; r < N; r++) {
            int max = 0;
            for (int c = 0; c <= r; c++) {
                int left = 0;
                int right = 0;
                
                if (c != 0) {
                    left = dp[r - 1][c - 1];
                }
                
                if (c != r) {
                    right = dp[r - 1][c];
                }
                
                dp[r][c] = Math.max(dp[r][c], Math.max(left, right) + triangle[r][c]);
                max = Math.max(max, dp[r][c]);
            }
            
            answer = Math.max(answer, max);
        }
        return answer;
    }
}