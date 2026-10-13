import java.util.*;

class Solution {
    public int solution(int[] money) {
        int n = money.length;
        
        int max1 = getMaxValue(money, money[0], money[0], n - 1);
        int max2 = getMaxValue(money, 0, money[1], n);
        
        return Math.max(max1, max2);
    }
    
    private int getMaxValue(int[] money, int prev2, int prev1, int end) {
        for (int i = 2; i < end; i++) {
            int curr = Math.max(prev1, prev2 + money[i]);
            
            prev2 = prev1;
            prev1 = curr;
        }
        
        return prev1;
    }
}
