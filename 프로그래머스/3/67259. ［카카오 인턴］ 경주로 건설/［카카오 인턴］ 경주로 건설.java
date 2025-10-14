import java.io.*;
import java.util.*;

class Solution {
    private static int[][] delta = {{-1, 0}, {0,-1}, {1, 0},{0, 1}};
    
    public int solution(int[][] board) {
        int answer = simulation(board);
        return answer;
    }
    
    private int simulation(int[][] board) {
        int N = board.length;
        int[][] dist = new int[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(dist[i], 987654321);
        }
        Queue<int[]> q = new ArrayDeque<>();
        
        for(int i = 0; i < 4; i++) {
            int nr = 0 + delta[i][0];
            int nc = 0 + delta[i][1];
            
            if(isRange(nr, nc, N) && board[nr][nc] != 1) {
                dist[nr][nc] = 100;
                q.add(new int[] {nr, nc, 100, i});
            }
        }
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int w = curr[2];
            int dir = curr[3];
            
            for(int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                
                if(isRange(nr, nc, N) && board[nr][nc] != 1) {
                    int nw = w + ((dir == i) ? 100 : 600);
                    if(dist[nr][nc] + 500 >= w) {
                        if(dist[nr][nc] > nw) {
                            dist[nr][nc] = nw;
                        }
                        q.add(new int[] {nr, nc, nw, i});
                    }
                }
            }
        }
        return dist[N - 1][N - 1];
    }
    
    private static boolean isRange(int r, int c, int N) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}