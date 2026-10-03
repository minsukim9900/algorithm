import java.util.*;

class Solution {
    int N, M;
    char[][] board;
    
    final int[][] DELTA = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    private boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
    
    private int bfs(int sr, int sc, int er, int ec) {
        boolean[][][] visited = new boolean[2][N][M];
        Queue<int[]> q = new ArrayDeque<>();
        
        visited[0][sr][sc] = true;
        q.add(new int[] {sr, sc, 0, 0});
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            
            int r = curr[0];
            int c = curr[1];
            int dist = curr[2];
            int state = curr[3];
            
            if (state == 1 && r == er && c == ec) {
                return dist;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + DELTA[i][0];
                int nc = c + DELTA[i][1];
                
                if (isRange(nr, nc) && board[nr][nc] != 'X' && !visited[state][nr][nc]) {
                    int nextState = state;
                    
                    if (board[nr][nc] == 'L') {
                        nextState = 1;
                    }
    
                    visited[nextState][nr][nc] = true;
                    q.add(new int[] {nr, nc, dist + 1, nextState});
                }
            }
        }
        
        return -1;
    }
    
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        board = new char[N][M];
        
        int sr = 0;
        int sc = 0;
        int er = 0;
        int ec = 0;
        
        for (int r = 0; r < N; r++) {
            String str = maps[r];
            
            for (int c = 0; c < M; c++) {
                char state = str.charAt(c);
                
                board[r][c] = state;
                
                if (state == 'S') {
                    sr = r;
                    sc = c;
                } else if (state == 'E') {
                    er = r;
                    ec = c;
                }
            }
        }
        
        return bfs(sr, sc, er, ec);
    }
}