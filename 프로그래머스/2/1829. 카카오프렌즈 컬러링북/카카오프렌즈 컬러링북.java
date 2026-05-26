import java.util.*;

class Solution {
    private static int N, M;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int[] solution(int m, int n, int[][] picture) {
        N = picture.length;
        M = picture[0].length;
        
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        boolean[][] visited = new boolean[N][M];
        
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(picture[r][c] > 0 && !visited[r][c]) {
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(r, c, visited, picture));
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    private static int bfs(int sr, int sc, boolean[][] visited, int[][] picture) {
        visited[sr][sc] = true;
        int result = 0;
        
        Queue<int[]> q = new ArrayDeque<>();
        
        q.add(new int[] {sr, sc});
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            result++;
            
            int r = curr[0];
            int c = curr[1];
            
            for(int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                
                if(isRange(nr, nc) 
                   && picture[nr][nc] == picture[sr][sc] && !visited[nr][nc]) {
                    q.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        
        return result;
    }
    
    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}