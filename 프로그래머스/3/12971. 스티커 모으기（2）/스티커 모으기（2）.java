import java.util.*;

class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int n = sticker.length;
        
        if (n <= 3) {
            int max = 0;
            
            for (int i = 0; i < n; i++) {
                max = Math.max(max, sticker[i]);
            }
            
            return max;
        }
        
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        
        dp1[0] = sticker[0];
        dp1[1] = sticker[0];
        dp2[1] = sticker[1];
        
        for (int i = 2; i < n; i++) {
            if (i < n - 1) {
                dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
            }
            
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
        }
        
        
        return Math.max(dp1[n - 2], dp2[n - 1]);
    }
}