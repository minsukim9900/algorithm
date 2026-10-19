import java.util.*;

class Solution {
    private boolean check(int n, long totalTime, int[] times) {
        long count = 0;
        
        for (int i = 0; i < times.length; i++) {
            count += totalTime / times[i];
            
            if (count >= n) {
                return true;
            }
        }
        
        return false;
    }
    
    public long solution(int n, int[] times) {
        long s = 0L;
        long e = 1L << 61;
        long answer = 0L;
        
        Arrays.sort(times);
        
        while (s <= e) {
            long mid = s + (e - s) / 2;
            
            if (check(n, mid, times)) {
                answer = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        
        return answer;
    }
}