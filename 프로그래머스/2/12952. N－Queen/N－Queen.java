import java.io.*;
import java.util.*;

class Solution {
    private static int fullMask, N, answer;
    
    public int solution(int n) {
        N = n;
        fullMask = (1 << (n + 1)) - 1;
        
        answer = 0;
        simulate(0, 0, 0, 0);
        return answer;
    }
    
    private static void simulate(
        int depth, int curr, int leftSide, int rightSide) {
        if(depth == N) {
            answer++;
            return;
        }
        
        for(int i = 0; i < N; i++) {
            int idx = 1 << i;
            
            if((curr & idx) != 0 || (leftSide & idx) != 0 || (rightSide & idx) != 0) {
                continue;
            }
            
            simulate(depth + 1, curr | idx, (leftSide | idx) << 1, (rightSide | idx) >> 1);
        }
    }
}