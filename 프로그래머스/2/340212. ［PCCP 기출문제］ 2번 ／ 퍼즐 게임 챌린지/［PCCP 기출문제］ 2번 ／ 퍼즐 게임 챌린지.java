import java.util.*;

class Solution {
    
    public long solution(int[] diffs, int[] times, long limit) {
        long answer = binarySearch(diffs, times, limit);
        return answer;
    }
    
    private long binarySearch(int[] diffs, int[] times, long limit) {
        long s = 1;
        long e = 1 << 60;
        long answer = 0;
        
        while(s <= e) {
            long mid = (s + e) >> 1;
            
            if(check(mid, diffs, times, limit)) {
                answer = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        
        return answer;
    }
    
    private boolean check(long mid, int[] diffs, int[] times, long limit) {
        long result = times[0];
        
        for(int i = 1; i < diffs.length; i++) {
            if(diffs[i] > mid) {
                long temp = diffs[i] - mid;
                
                result += (times[i] + times[i - 1]) * temp + times[i];
                
            } else {
                result += times[i];
            }
            
            if(result > limit) {
                return false;
            }
        }
        
        return true;
    }
}