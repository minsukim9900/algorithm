import java.util.*;

class Solution {
    private static int N;
    private static final int INF = 100_000_000;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int[] solution(String[][] places) {
        N = 5;
        int T = places.length;
        int[] answer = new int[T];
        
        for(int t = 0; t < T; t++) {
            String[] temp = places[t];
            
            char[][] place = new char[N][N];
            
            List<int[]> person = new ArrayList<>();
            
            for(int r = 0; r < N; r++) {
                String str = temp[r];
                
                for(int c = 0; c < N; c++) {
                   place[r][c] = str.charAt(c);
                    
                    if (place[r][c] == 'P') {
                       person.add(new int[] {r, c});
                    }
                }
            }
            
            boolean isPoss = true;
            answer[t] = 1;
            for(int i = 0; i < person.size(); i++) {
                int[] curr = person.get(i);
                int sr = curr[0];
                int sc = curr[1];
                isPoss = bfs(sr, sc, place);
                
                if(!isPoss) {
                    answer[t] = 0;
                    break;
                }
            }
        }
        return answer;
    }
    
    private static boolean bfs(int sr, int sc, char[][] place) {
        
        boolean[][] visited = new boolean[N][N];
        visited[sr][sc] = true;
        
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {sr, sc, 0});
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int dist = curr[2];
            
            if(!(r == sr && c == sc) && place[r][c] == 'P') {
                return false;
            }
            
            if(dist == 2) {
                continue;
            }
            
            
            for(int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                
                if (isRange(nr, nc) && place[nr][nc] != 'X' && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc, dist + 1});
                }
            }
        }
        
        return true;
    }
    
    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}