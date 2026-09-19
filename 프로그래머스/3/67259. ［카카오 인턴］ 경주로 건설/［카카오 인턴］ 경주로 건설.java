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
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        
        for (int i = 0; i < 4; i++) {
            pq.add(new int[] {0, 0, 0, i});
            dist[i][0][0] = 0;
        }
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            
            int r = curr[0];
            int c = curr[1];
            int cost = curr[2];
            int dir = curr[3];
            
            if (dist[dir][r][c] != cost) {
                continue;
            }
            
            if (r == N - 1 && c == N - 1) {
                return cost * 100;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                
                if (isRange(nr, nc) && board[nr][nc] != 1) {
                    
                    int nextCost = 1;
                    
                    if (i != dir) {
                        nextCost = 6;
                    }
                    
                    if (dist[i][nr][nc] > dist[dir][r][c] + nextCost) {
                        dist[i][nr][nc] = dist[dir][r][c] + nextCost;
                        pq.add(new int[] {nr, nc, dist[i][nr][nc], i});
                    }
                }
            }
        }
        
        return -1;
    }
    
    public int solution(int[][] b) {
        N = b.length;
        board = b;
        
        int answer = bfs();
        return answer;
    }
}