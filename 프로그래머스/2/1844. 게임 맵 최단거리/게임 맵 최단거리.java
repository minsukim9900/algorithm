import java.util.*;

class Solution {
    private static int N, M;
    private static int[][] delta = {{-1 ,0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        
        int answer = bfs(maps);
        return answer;
    }
    
    public boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
    
    public int bfs(int[][] maps) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        
        q.add(new int[] {0, 0, 1});
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            
            int r = curr[0];
            int c = curr[1];
            int d = curr[2];
            
            if (r == N - 1 && c == M - 1) {
                return d;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                
                if (isRange(nr, nc) && maps[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc, d + 1});
                }
            }
        }
        
        return -1;
    }
}