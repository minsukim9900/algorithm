import java.util.*;

class Solution {
    private static int N;
    private static int[][] board;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    private static final int INF = 1_000_000_000;
    
    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
    
    private static int bfs() {
        int[][][] dist = new int[4][N][N];
        
        for (int i = 0; i < 4; i++) {
            for (int r = 0; r < N; r++) {
                Arrays.fill(dist[i][r], INF);
            }
        }
        
        Queue<int[]> q = new ArrayDeque<>();
        
        for (int i = 0; i < 4; i++) {
            q.add(new int[] {0, 0, 0, i});
            dist[i][0][0] = 0;
        }
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            
            int r = curr[0];
            int c = curr[1];
            int cost = curr[2];
            int dir = curr[3];
            
            if (dist[dir][r][c] != cost) {
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                
                if (isRange(nr, nc) && board[nr][nc] != 1) {
                    if (i == dir) {
                       if (dist[i][nr][nc] > dist[dir][r][c] + 1) {
                           dist[i][nr][nc] = dist[dir][r][c] + 1;
                           q.add(new int[] {nr, nc, dist[i][nr][nc], i});
                       }
                    } else {
                        if (dist[i][nr][nc] > dist[dir][r][c] + 6) {
                            dist[i][nr][nc] = dist[dir][r][c] + 6;
                            q.add(new int[] {nr, nc, dist[i][nr][nc], i});
                        }
                    }
                }
            }
        }
        
        int answer = INF;
        
        for (int i = 0; i < 4; i++) {
            answer = Math.min(answer, dist[i][N - 1][N - 1]);
        }
        
        return answer * 100;
    }
    
    public int solution(int[][] b) {
        N = b.length;
        board = b;
        
        int answer = bfs();
        return answer;
    }
}