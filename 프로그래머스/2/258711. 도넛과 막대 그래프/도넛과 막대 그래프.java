import java.io.*;
import java.util.*;

class Solution {
    private static int N = 1_000_001;
    private static int[] in, out;
    
    public int[] solution(int[][] edges) {
        in = new int[N];
        out = new int[N];
        
        int max = 0;
        for(int[] edge : edges) {
            out[edge[0]]++;
            in[edge[1]]++;
            
            max = Math.max(max, Math.max(edge[0], edge[1]));
        }
        
        int start = 0;
        int eight = 0;
        int stick = 0;
        
        for(int i = 1; i<=max; i++) {
            
            if(out[i] >= 2) {
                if(in[i] == 0) {
                    start = i;
                }else {
                    eight++;
                }
            } else if(out[i] == 0 && in[i] > 0) {
                stick++;
            }
            
        }
        return new int[] {start, out[start] - (eight + stick), stick, eight};
    }
}