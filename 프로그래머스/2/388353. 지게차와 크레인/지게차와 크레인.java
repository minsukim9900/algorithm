import java.util.*;

class Solution {
    private static int N, M;
    private static boolean[] state;
    private static int[][] board;
    private static int[] alphabetCount;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        
        state = new boolean['Z' - 'A' + 2];
        alphabetCount = new int['Z' - 'A' + 2];
        
        state[0] = true;
        
        board = new int[N + 2][M + 2];
        
        int answer = 0;
        for(int r = 1; r <= N; r++) {
            String str = storage[r - 1];
            for(int c = 1; c <= M; c++) {
                board[r][c] = str.charAt(c - 1) - 'A' + 1;
                alphabetCount[board[r][c]]++;
                answer++;
            }
        }
        
        for(String request : requests) {
            int num = request.charAt(0) - 'A' + 1;
            
            if(request.length() == 1) {
                if(alphabetCount[num] == 0) continue;
                
                int result = bfs(num);
                alphabetCount[num] -= result;
                answer -= result;
            } else {
                answer -= alphabetCount[num];
                alphabetCount[num] = 0;
                state[num] = true;
            }
        }
        
        return answer;
    }
    
    
    private static int bfs(int num) {
        boolean[][] visited = new boolean[N + 2][M + 2];
        Queue<int[]> q = new ArrayDeque<>();
        
        q.add(new int[] {0, 0});
        visited[0][0] = true;
        
        List<int[]> result = new ArrayList<>();
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            
            for(int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                
                if(isRange(nr, nc) && !visited[nr][nc]) {
                    if(board[nr][nc] != num && !state[board[nr][nc]]) {
                        continue;
                    }
                    
                    visited[nr][nc] = true;
                    
                    if(board[nr][nc] != num) {
                        q.add(new int[] {nr, nc});
                    } else {
                        result.add(new int[] {nr, nc});
                    }
                }
            }
        }
        
        for(int[] x : result) {
            board[x[0]][x[1]] = 0;
        }
        
        return result.size();
    }
    
    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N + 2 && c >= 0 && c < M + 2;
    }
}