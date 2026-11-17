import java.util.*;

class Solution {
    private int[] count;
    
    private final int MAX = 10_000_001;
    
    public int solution(int k, int[] tangerine) {
        count = new int[MAX];
        
        for (int i = 0; i < tangerine.length; i++) {
            count[tangerine[i]]++;
        }
        
        Arrays.sort(count);
        
        int sum = 0;
        
        for (int i = MAX - 1; i >= 0; i--) {
            sum += count[i];
            
            if (sum >= k) {
                return MAX - i;
            }
        }
        
        return -1;
    }
}