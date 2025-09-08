import java.io.*;
import java.util.*;

class Solution {
    private static int K;
    private static final int INF = 100_000;
    private static int[][] infos;
    public int solution(int[][] info, int n, int m) {
        int sum = 0;
        for(int[] w : info) {
            sum += w[0];
        }
        
        int[] dp = new int[m];
        for(int[] w : info) {
            for(int j = m - 1; j >= w[1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - w[1]] + w[0]);
            }
        }
        
        int save = 0;
        for(int w : dp) {
            save = Math.max(save, w);
        }
        
        int ans = sum - save;
        return ans >= n ? -1 : ans;
    }
}