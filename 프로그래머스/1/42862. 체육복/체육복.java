import java.io.*;
import java.util.*;

class Solution {
    
    private static boolean[] visited;
    private static int[] p;
    
    public int solution(int n, int[] lost, int[] reserve) {
        
        p = new int[n+2];
        Arrays.fill(p, 1);
        
        for(int i : lost) {
            p[i]--;
        }
        for(int w : reserve) {
            p[w]++;
        }
        
        for(int i = 1; i<=n; i++) {
            
            if(p[i] > 1) {
                if(p[i-1] == 0) {
                    p[i-1]++;
                    p[i]--;
                }else if(p[i+1] == 0) {
                    p[i+1]++;
                    p[i]--;
                }
            }
            
        }
        
        int cnt = 0;
        for(int i = 1; i<=n; i++) {
            if(p[i] != 0) cnt++;
        }
        
        int answer = cnt;
        return answer;
    }
}