import java.util.*;

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        long dSqr = (long) d * d;
        
        for (long x = 0; x <= d; x += k) {
            long xSqr = x * x;
            
            long maxYSqr = dSqr - xSqr;
            long maxY = (long) Math.sqrt(maxYSqr);
            
            answer += (maxY) / k + 1;
        }
        
        return answer;
    }
}