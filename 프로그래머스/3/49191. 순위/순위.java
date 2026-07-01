import java.util.*;

class Solution {
    private static final int INF = 100_000_000;
    
    public int solution(int n, int[][] results) {
        int[][] adj = new int[n + 1][n + 1];
        
        for(int r = 1; r < n + 1; r++) {
            for(int c = 1; c < n + 1; c++) {
                adj[r][c] = INF;
            }
        }
        
        for(int[] result : results) {
            int A = result[0];
            int B = result[1];
            
            adj[A][B] = 1;
        }
        
        for(int k = 1; k < n + 1; k++) {
            for(int i = 1; i < n + 1; i++) {
                if(k == i) continue;
                for(int j = 1; j < n + 1; j++) {
                    if(k == j || i == j) {
                        continue;
                    }
                    
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }
        
        for(int r = 1; r < n + 1; r++) {
            for(int c = 1; c < n + 1; c++) {
                if(adj[r][c] != INF) {
                    adj[0][c]++;
                    adj[r][0]++;
                }
            }
        }
        
        int answer = 0;
        
        for(int num = 1; num < n + 1; num++) {
            if(adj[num][0] + adj[0][num] == n - 1) {
                answer++;
            }
        }
        return answer;
    }
}