import java.util.*;

class Solution {
    
    public int solution(int n, int[] cores) {
        int coreCount = cores.length;
        
        if (n <= coreCount) {
            return n;
        }
        
        long time = findMinimumTime(n , cores);
        long countBefore = countWorksUntil(time - 1, cores);
        
        return findAnswerCore(n, countBefore, time, cores);
    }
    
    private long findMinimumTime(int n, int[] cores) {
        long left = 0L;
        long right = getMaxTime(n, cores);
        long answer = 0L;
        
        while(left <= right) {
            long mid = (left + right) / 2;
            
            long count = countWorksUntil(mid, cores);
            
            if (count >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    private long countWorksUntil(long time, int[] cores) {
        if (time < 0) {
            return 0;
        }
        
        long count = cores.length;
        
        for(int core : cores) {
            count += time / core;
        }
        
        return count;
    }
    
    private long getMaxTime(int n, int[] cores) {
        int minCore = Integer.MAX_VALUE;
        
        for(int core : cores) {
            minCore = Math.min(minCore, core);
        }
        
        return (long) minCore * n;
    }
    
    private int findAnswerCore(int n, long countBefore, long time, int[] cores) {
        long remain = n - countBefore;
        
        for(int i = 0; i < cores.length; i++) {
            if (time % cores[i] == 0) {
                remain--;
                
                if (remain == 0) {
                    return i + 1;
                }
            }
        }
        
        return -1;
    }
}