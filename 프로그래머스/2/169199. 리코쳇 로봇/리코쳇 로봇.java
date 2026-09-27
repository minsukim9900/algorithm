import java.util.*;

class Solution {
    private static int N, M;
    private static char[][] board;
    
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
    
    private static int bfs(int sr, int sc, int er, int ec) {
        boolean[][] visited = new boolean[N][M];
        
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {sr, sc, 0});
        
        visited[sr][sc] = true;
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int dist = curr[2];
            
            if (r == er && c == ec) {
                return dist;
            }
            
            for (int dir = 0; dir < 4; dir++) {
                int nr = r;
                int nc = c;
                
                while(true) {
                    int tr = nr + delta[dir][0];
                    int tc = nc + delta[dir][1];
                    
                    if (!isRange(tr, tc) || board[tr][tc] == 'D') {
                        break;
                    }
                    
                    nr = tr;
                    nc = tc;
                }
                
                if (nr == r && nc == c) {
                    continue;
                }
                
                if (visited[nr][nc]) {
                    continue;
                }
                
                visited[nr][nc] = true;
                q.add(new int[] {nr, nc, dist + 1});
            }
        }
        
        return -1;
    }
    
    public int solution(String[] map) {
        N = map.length;
        M = map[0].length();
        
        board = new char[N][M];
        
        int sr = 0;
        int sc = 0;
        int er = 0;
        int ec = 0;
        
        for (int r = 0; r < N; r++) {
            String str = map[r];
            for (int c = 0; c < M; c++) {
                char x = str.charAt(c);
                
                if (x == 'R') {
                    sr = r;
                    sc = c;
                } else if (x == 'G') {
                    er = r;
                    ec = c;
                }
                
                board[r][c] = x;
            }
        }
        
        
        int answer = bfs(sr, sc, er, ec);
        return answer;
    }
}