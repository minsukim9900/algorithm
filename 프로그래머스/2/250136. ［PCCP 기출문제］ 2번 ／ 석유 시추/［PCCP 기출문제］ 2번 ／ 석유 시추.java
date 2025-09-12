import java.io.*;
import java.util.*;

class Solution {
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int[] diff;
    private static int N, M;
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        diff = new int[M];
        
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(land[r][c] == 1) {
                    bfs(r, c, land);
                }
            }
        }
        int answer = 0;
        int run = 0;
        
        for(int c = 0; c < M; c++) {
            run += diff[c];
            answer = Math.max(answer, run);
        }
        
        return answer;
    }
    
    private static void bfs(int r, int c, int[][] land) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {r, c});
        int minC = M;
        int maxC = 0;
        int cnt= 0;
        land[r][c] = 0;
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            
            cnt++;
            minC = Math.min(curr[1], minC);
            maxC = Math.max(curr[1], maxC);
            
            for(int i = 0; i < 4; i++) {
                int nr = curr[0] + delta[i][0];
                int nc = curr[1] + delta[i][1];
                
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && land[nr][nc] == 1) {
                    land[nr][nc] = 0;
                    q.add(new int[] {nr, nc});
                }
            }
        }
        
        diff[minC] += cnt;
        if(maxC + 1 < M) diff[maxC + 1] -= cnt;
    }
}