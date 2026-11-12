import java.util.*;

class Solution {
    private final int MAX = 2_000_000;
    
    public int solution(int n) {
        int comp = Integer.bitCount(n);
        
        for (int i = n + 1; i < MAX; i++) {
            int curr = Integer.bitCount(i);
            
            if (comp != curr) {
                continue;
            }
            
            return i;
        }
        
        return -1;
    }
}