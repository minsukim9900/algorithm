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
                isPoss = bfs(sr, sc, person, place);
                
                if(!isPoss) {
                    answer[t] = 0;
                    break;
                }
            }
        }
        return answer;
    }
    
    private static boolean bfs(int sr, int sc, List<int[]> person, char[][] place) {
        int[][] dist = new int[N][N];
        
        for(int r = 0; r < N; r++) {
            Arrays.fill(dist[r], INF);
        }
        
        dist[sr][sc] = 0;
        
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {sr, sc});
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            
            for(int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                
                if (isRange(nr, nc) && place[nr][nc] != 'X' && dist[nr][nc] > dist[r][c] + 1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    q.add(new int[] {nr, nc});
                }
            }
        }
        
        for (int i = 0; i < person.size(); i++) {
            int[] curr = person.get(i);
            int r = curr[0];
            int c = curr[1];
            
            if(r == sr && c == sc) {
                continue;
            }
            
            if(dist[r][c] <= 2) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}