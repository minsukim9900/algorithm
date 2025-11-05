import java.io.*;
import java.util.*;

class Solution {
    private static boolean isPoss = false;
    private static String answer = "impossible";
    private static int[][] delta = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int distance = Math.abs(x - r) + Math.abs(y - c);
        if(distance > k || ((k - distance) % 2) == 1)  {
            return answer;
        }
        
        int cr = x;
        int cc = y;
        char[] ch = {'d', 'l', 'r', 'u'};
        StringBuilder sb = new StringBuilder();
        
        for(int step = 0; step < k; step++) {
            for(int i = 0; i < 4; i++) {
                int nr = cr + delta[i][0];
                int nc = cc + delta[i][1];
                if(!isRange(nr, nc, n, m)) continue;
                
                int remain = k - step - 1;
                int md = Math.abs(nr - r) + Math.abs(nc - c);
                
                if(md <= remain && ((remain - md) % 2) == 0) {
                    sb.append(ch[i]);
                    cr = nr;
                    cc = nc;
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    
    private static boolean isRange(int r, int c, int n, int m) {
        return r >= 1 && r <= n && c >= 1 && c <= m;
    }
}