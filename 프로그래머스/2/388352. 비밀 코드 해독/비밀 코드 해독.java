import java.io.*;
import java.util.*;

class Solution {
    private static int[] choice;
    private static int answer = 0;
    private static boolean[][] visited;
    
    public int solution(int n, int[][] q, int[] ans) {
        choice = new int[5];
        
        visited = new boolean[q.length][n + 1];
        
        for(int i = 0; i < q.length; i++) {
            for(int j = 0; j < 5; j++) {
                visited[i][q[i][j]] = true;
            }
        }
        
        
        cal(n, ans, 1, 0, new int[5]);
        return answer;
    }
    
    // 조합을 구하고 q의 갯수가 맞는지 검증
    private static void cal(int n, int[] ans, int idx, int depth, int[] choice) {
        if(depth == 5) {
            if(isMatch(choice, ans)) {
                answer++;
            }
            return;
        }
        
        for(int i = idx; i <= n; i++) {
            choice[depth] = i;
            cal(n, ans, i + 1, depth + 1, choice);
        }
    }
    
    private static boolean isMatch(int[] choice, int[] ans) {
        for(int i = 0; i < visited.length; i++) {
            int cnt = 0;
            
            for(int j = 0; j < 5; j++) {
                if(visited[i][choice[j]]) {
                    cnt++;
                }
            }
            
            if(cnt != ans[i]) {
                return false;
            }
        }
        
        return true;
    }
}