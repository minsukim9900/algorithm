import java.util.*;

class Solution {
    public int solution(int sticker[]) {
        int n = sticker.length;
        
        if (n == 1) {
            return sticker[0];
        }
        
        int max1 = getMaxValue(sticker[0], sticker[0], n - 1, sticker);
        int max2 = getMaxValue(0, sticker[1], n, sticker);
        
        return Math.max(max1, max2);
    }
    
    private int getMaxValue(int prev2, int prev1, int end, int[] sticker) {
        for (int i = 2; i < end; i++) {
            int curr = Math.max(prev1, prev2 + sticker[i]);
            
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }
}