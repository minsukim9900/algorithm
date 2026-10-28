import java.util.*;

class Solution {
    private final int N = 51;
    
    private String[][] board;
    private List<int[]>[][] adj;
    
    private void init() {
        board = new String[N][N];
        
        adj = new ArrayList[N][N];
        
        for (int r = 1; r < N; r++) {
            for (int c = 1; c < N; c++) {
                adj[r][c] = new ArrayList<>();
            }
        }
    }
    
    private void update(String value1, String value2) {
        for (int r = 1; r < N; r++) {
            for (int c = 1; c < N; c++) {
                if (value1.equals(board[r][c])) {
                    board[r][c] = value2;
                }
            }
        }
    }
    
    private void update(int sr, int sc, String value) {
        boolean[][] visited = new boolean[N][N];
        
        visited[sr][sc] = true;
        Queue<int[]> q = new ArrayDeque<>();
        
        q.add(new int[] {sr, sc});
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            
            int r = curr[0];
            int c = curr[1];
            
            board[r][c] = value;
            
            for (int[] next : adj[r][c]) {
                int nr = next[0];
                int nc = next[1];
                
                if (visited[nr][nc]) {
                    continue;
                }
                
                visited[nr][nc] = true;
                q.add(new int[] {nr, nc});
            }
        }
    }
    
    private void merge(int r1, int c1, int r2, int c2) {
        adj[r1][c1].add(new int[] {r2, c2});
        adj[r2][c2].add(new int[] {r1, c1});
        
        if (board[r1][c1] != null && !board[r1][c1].isEmpty()) {
            update(r1, c1, board[r1][c1]);
            
            return;
        }
        
        if (board[r2][c2] != null && !board[r2][c2].isEmpty()) {
            update(r2, c2, board[r2][c2]);
            
            return;
        }
    }
    
    private void unmerge(int sr, int sc) {
        String text = board[sr][sc];
        
        boolean[][] visited = new boolean[N][N];
        
        visited[sr][sc] = true;
        Queue<int[]> q = new ArrayDeque<>();
        
        q.add(new int[] {sr, sc});
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            
            int r = curr[0];
            int c = curr[1];
            
            board[r][c] = "";
            
            for (int[] next : adj[r][c]) {
                int nr = next[0];
                int nc = next[1];
                
                if (visited[nr][nc]) {
                    continue;
                }
                
                visited[nr][nc] = true;
                q.add(new int[] {nr, nc});
            }
            
            adj[r][c].clear();
        }
        
        board[sr][sc] = text;
    }
    
    private String print(int r, int c) {
        return (board[r][c] == null || board[r][c].isEmpty()) ? "EMPTY" : board[r][c];
    }
    
    public String[] solution(String[] commands) {
        init();
        
        List<String> answer = new ArrayList<>();
        
        for (String command : commands) {
            String[] com = command.split(" ");
            
            if ("UPDATE".equals(com[0])) {
                if (com.length == 3) {
                    String value1 = com[1];
                    String value2 = com[2];
                    
                    update(value1, value2);
                } else {
                    int r = Integer.parseInt(com[1]);
                    int c = Integer.parseInt(com[2]);
                    String value = com[3];
                    
                    update(r, c, value);
                }
            } else if ("MERGE".equals(com[0])) {
                int r1 = Integer.parseInt(com[1]);
                int c1 = Integer.parseInt(com[2]);
                int r2 = Integer.parseInt(com[3]);
                int c2 = Integer.parseInt(com[4]);
                
                if (r1 == r2 && c1 == c2) {
                    continue;
                }
                
                merge(r1, c1, r2, c2);
            } else if ("UNMERGE".equals(com[0])) {
                int r = Integer.parseInt(com[1]);
                int c = Integer.parseInt(com[2]);
                
                unmerge(r, c);
            } else {
                int r = Integer.parseInt(com[1]);
                int c = Integer.parseInt(com[2]);
                
                answer.add(print(r, c));
            }
        }
        
        return answer.toArray(new String[0]);
    }
}