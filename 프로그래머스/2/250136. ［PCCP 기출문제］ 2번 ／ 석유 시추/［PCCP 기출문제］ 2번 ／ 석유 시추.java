import java.io.*;
import java.util.*;

class Solution {
    private static int N, M;
    private static int[] count;
    private static int[][] board;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        board = land;
        count = new int[M];
        
        boolean[][] visited = new boolean[N][M];
        
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(board[r][c] == 1 && !visited[r][c]) {
                    bfs(r, c, visited);
                }
            }
        }
        
        int answer = 0;
        
        for(int c = 0; c < M; c++) {
            answer = Math.max(answer, count[c]);
        }
        return answer;
    }
    
    private static void bfs(int sr, int sc, boolean[][] visited) {       
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {sr, sc});
        
        visited[sr][sc] = true;
        
        int minC = sc;
        int maxC = sc;
        
        int cnt = 0;
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            
            int r = curr[0];
            int c = curr[1];
            
            minC = Math.min(minC, c);
            maxC = Math.max(maxC, c);
            
            cnt++;
            
            for(int i = 0; i < 4; i++) {
                
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                
                if(isRange(nr, nc) && board[nr][nc] == 1 
                   && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                }
            }
        }
        
        for(int c = minC; c <= maxC; c++) {
            count[c] += cnt;
        }
    }
    
    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}