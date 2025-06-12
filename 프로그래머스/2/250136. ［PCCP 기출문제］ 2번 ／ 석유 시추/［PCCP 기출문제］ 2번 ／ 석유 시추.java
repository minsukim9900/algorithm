import java.io.*;
import java.util.*;

class Solution {
    private static int N, M;
    private static int[] amount;
    private static boolean[][] visited;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1},{0,1}};
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        visited = new boolean[N][M];
        amount = new int[M];
        
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(!visited[r][c] && land[r][c] == 1) {
                    searchOil(r, c, land);
                }
            }
        }
        
        
        int answer = 0;
        for(int w : amount) {
            answer = Math.max(answer, w);
        }
        
        return answer;
    }
    
    private static void searchOil(int r, int c, int[][] land) {
        int maxC = c;
        int minC = c;
        int cnt = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {r, c});
        visited[r][c] = true;
           
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            cnt++;
            
            for(int i = 0; i < 4; i++) {
                int nr = curr[0] + delta[i][0];
                int nc = curr[1] + delta[i][1];
                
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && 
                   !visited[nr][nc] && land[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    maxC = Math.max(maxC, nc);
                    minC = Math.min(minC, nc);
                    q.add(new int[] {nr, nc});
                }
            }   
        }
        
        for(int i = minC; i <= maxC; i++) {
            amount[i] += cnt;
        }
    }
}