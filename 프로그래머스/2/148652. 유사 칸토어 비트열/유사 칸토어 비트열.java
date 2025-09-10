import java.util.*;

class Solution {
    private static long[] p5, p4;
    public long solution(int n, long l, long r) {
        init(n);
        return prefix(r, n) - prefix(l - 1, n);
    }
    
    private static long prefix(long point, int size) {
        if(size <= 0) {
           return 0;
        }
        
        long page = point / p5[size - 1];
        long rem = point % p5[size - 1];
        long prePageOneCount = p4[size - 1] * (page >= 3 ? page - 1 : page);
        
        long sum = prePageOneCount;
        if(page == 2) {
            return sum;
        }else {
            return sum += prefix(rem, size - 1);
        }
    }
    
    private static void init(int n) {
        p5 = new long[n + 1];
        p4 = new long[n + 1];
        p5[0] = 1; p4[0] = 1;
        for(int i = 1; i <= n; i++) {
            p4[i] = p4[i - 1] * 4;
            p5[i] = p5[i - 1] * 5;
        }
    }
}