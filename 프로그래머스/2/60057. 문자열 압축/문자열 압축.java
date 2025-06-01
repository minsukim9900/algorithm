import java.io.*;
import java.util.*;

class Solution {
    private static int answer, M;
    public int solution(String s) {
        M = s.length();
        answer = s.length();
        
        for(int i = 1; i <= M >> 1; i++) {
            dfs(0, i, s, "", "", 1);   
        }
        return answer;
    }
    
    private static void dfs(int preIdx, int increase, String s, String str, String preStr, int count) {
        if(preIdx >= M) {
            if(count > 1) {
                str += count;
            }
            str += preStr;
            answer = Math.min(answer, str.length());
            return;
        }
        int range = preIdx + increase;
        if(range > M) {
            range = M;
        }
        String tmp = s.substring(preIdx, range);
        
        if(preStr.equals(tmp)) {
            dfs(range, increase, s, str, preStr, count + 1);
        }else {
            if(count > 1) {
                str += count;
            }
            str += preStr;
            dfs(range, increase, s, str, tmp, 1);
        }
    }
}
