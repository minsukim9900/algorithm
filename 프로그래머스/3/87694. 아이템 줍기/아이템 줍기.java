import java.util.*;

class Solution {
    private int[][] board;
    
    private final int MAX_LEN = 102;
    private final int[][] DELTA = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    
    private void init(int[][] rectangles) {
        board = new int[MAX_LEN][MAX_LEN];
        
        for (int i = 0; i < rectangles.length; i++) {
            int[] rectangle = rectangles[i];
            
            int sc = rectangle[0] * 2;
            int sr = rectangle[1] * 2;
            int ec = rectangle[2] * 2;
            int er = rectangle[3] * 2;
            
            for (int r = sr; r <= er; r++) {
                for (int c = sc; c <= ec; c++) {
                    if (board[r][c] == 2) {
                        continue;
                    }
                    
                    if (r == sr || c == sc || r == er || c == ec) {
                        board[r][c] = 1;
                    } else {
                        board[r][c] = 2;
                    }
                }
            }
        }
    }
    
    private boolean isRange(int r, int c) {
        return r >= 0 && r < MAX_LEN && c >= 0 && c < MAX_LEN;
    }
    
    private int bfs(int sr, int sc, int er, int ec) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[MAX_LEN][MAX_LEN];
        
        q.add(new int[] {sr, sc, 0});
        visited[sr][sc] = true;
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            
            int r = curr[0];
            int c = curr[1];
            int d = curr[2];
            
            if (r == er && c == ec) {
                return d / 2;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + DELTA[i][0];
                int nc = c + DELTA[i][1];
                
                if (isRange(nr, nc) && board[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc, d + 1});
                }
            }
        }
        
        return -1;
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        init(rectangle);
        
        
        return bfs(characterY * 2, characterX * 2, itemY * 2, itemX * 2);
    }
}