import java.util.*;
class Solution {
    private int N, M;
    private final int[][] DELTA = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    private boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
    
    private int bfs(int sr, int sc, boolean[][] visited, int[][] board) {
        visited[sr][sc] = true;
        
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {sr, sc});
        
        int result = board[sr][sc];
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + DELTA[i][0];
                int nc = c + DELTA[i][1];
                
                if (isRange(nr, nc) && !visited[nr][nc] && board[nr][nc] != 0) {
                    visited[nr][nc] = true;
                    result += board[nr][nc];
                    
                    q.add(new int[] {nr, nc});
                }
            }
        }
        
        return result;
    }
    
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        int[][] board = new int[N][M];
        
        for (int r = 0; r < N; r++) {
            String map = maps[r];
            
            for (int c = 0; c < M; c++) {
                int num = map.charAt(c) - '0';
                
                if (num >= 0 && num < 10) {
                    board[r][c] = num;
                }
            }
        }
        
        int[] answer = {-1};
        
        boolean[][] visited = new boolean[N][M];
        
        List<Integer> buffer = new ArrayList<>();
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] > 0 && !visited[r][c]) {
                    int result = bfs(r, c, visited, board);
                    
                    if (result > 0) {
                        buffer.add(result);
                    }
                }
            }
        }
        
        buffer.sort((a, b) -> Integer.compare(a, b));
        
        if (buffer.size() > 0) {
            answer = new int[buffer.size()];
            
            for (int i = 0; i < buffer.size(); i++) {
                answer[i] = buffer.get(i);
            }
        }
        
        
        return answer;
    }
}