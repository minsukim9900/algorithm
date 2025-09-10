import java.util.*;

class Solution {
    private static long[] d5, d4;

    public long solution(int n, long l, long r) {
        init(n);
        return prefix(n, r) - prefix(n, l - 1);
    }
    
    private static long prefix(int n, long x) {
        if(x <= 0) return 0;
        if(n == 0) return Math.min(1L, x);
        if(x >= d5[n]) return d4[n];
        
        long blk = d5[n - 1];
        long full = x / blk;
        long rem = x % blk;
        
        long blocks = full - (full >= 3 ? 1 : 0);
        long sum = blocks * d4[n - 1];
        
        if(full != 2) sum += prefix(n - 1, rem);
        return sum;
    }
    
    private void init(int n) {
        d5 = new long[n + 1];
        d4 = new long[n + 1];
        d5[0] = 1L; d4[0] = 1L;
        
        for(int i = 1; i <= n; i++) {
            d5[i] = d5[i - 1] * 5;
            d4[i] = d4[i - 1] * 4;
        }
    }
}