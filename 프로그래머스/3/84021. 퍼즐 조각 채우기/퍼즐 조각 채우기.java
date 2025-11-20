import java.io.*;
import java.util.*;

class Solution {
    private static int N;
    private static boolean[][] visited;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static List<Hole>[][] holes;
    
    private static class Hole {
        public int[][] hole;
        public int idx;

        public Hole(int[][] hole, int idx) {
            this.hole = hole;
            this.idx = idx;
        }
    }
    
    private static class Shape {
        int[][] shape;
        int cnt;

        public Shape(int[][] shape, int cnt) {
            this.shape = shape;
            this.cnt = cnt;
        }
    }
    
    public int solution(int[][] game_board, int[][] table) {
        N = table.length;
        visited = new boolean[N][N];
        holes = new ArrayList[N + 1][N + 1];
        
        for(int r = 0; r <= N; r++) {
            for(int c = 0; c <= N; c++) {
                holes[r][c] = new ArrayList<>();
            }
        }
        
        int idx = 1;
        for(int r = 0; r < N; r++) {
            for(int c= 0; c < N; c++) {
                if(visited[r][c] || game_board[r][c] == 1) continue;
                int[][][] tmp = rotate(bfs(r, c, game_board, 0).shape);
                holes[tmp[0].length][tmp[0][0].length].add(new Hole(tmp[0], idx));
                holes[tmp[1].length][tmp[1][0].length].add(new Hole(tmp[1], idx));
                holes[tmp[2].length][tmp[2][0].length].add(new Hole(tmp[2], idx));
                holes[tmp[3].length][tmp[3][0].length].add(new Hole(tmp[3], idx++));
            }
        }
        
        boolean[] used = new boolean[idx];
        
        visited = new boolean[N][N];
        int answer = 0;
        
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(visited[r][c] || table[r][c] == 0) continue;
                
                Shape sh = bfs(r, c, table, 1);
                int[][] tmp = sh.shape;
                int cnt = sh.cnt;
                
                for(int i = 0; i < holes[tmp.length][tmp[0].length].size(); i++) {
                    Hole comp = holes[tmp.length][tmp[0].length].get(i);
                    
                    if(used[comp.idx]) continue;
                    
                    boolean poss = true;
                    out: for(int nr = 0; nr < tmp.length; nr++) {
                        for(int nc = 0; nc < tmp[0].length; nc++) {
                            if(tmp[nr][nc] != comp.hole[nr][nc]) {
                                poss = false;
                                break out;
                            }
                        }
                    }
                    
                    if(poss) {
                        used[comp.idx] = true;
                        answer += cnt;
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
    
    private static int[][][] rotate(int[][] board) {
        int[][][] result = new int[4][][];
        result[0] = board;
        result[1] = new int[board[0].length][board.length];
        result[2] = new int[board.length][board[0].length];
        result[3] = new int[board[0].length][board.length];
        
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                result[1][result[1].length - 1 - c][r] = board[r][c];
                result[2][result[2].length - 1 - r][result[2][0].length - 1 - c] = board[r][c];
                result[3][c][result[3][0].length - 1 - r] = board[r][c];
            }
        }
        return result;
    }
    
    private static Shape bfs(int r, int c, int[][] board, int target) {
        int minR = r;
        int minC = c;
        int maxR = r;
        int maxC = c;
        
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {r, c});
        visited[r][c] = true;
        
        List<int[]> cor = new ArrayList<>();
        cor.add(new int[] {r, c});
        int cnt = 0;
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int cr = curr[0];
            int cc = curr[1];
            cnt++;
            minR = Math.min(minR, cr);
            minC = Math.min(minC, cc);
            maxR = Math.max(maxR, cr);
            maxC = Math.max(maxC, cc);
            
            for(int i = 0; i < 4; i++) {
                int nr = cr + delta[i][0];
                int nc = cc + delta[i][1];
                
                if(isRange(nr, nc) && board[nr][nc] == target && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                    cor.add(new int[] {nr, nc});
                }
            }
        }
        
        int[][] result = new int[maxR - minR + 1][maxC - minC + 1];
        
        for(int[] info : cor) {
            int nr = info[0] - minR;
            int nc = info[1] - minC;
            result[nr][nc] = 1;
        }
        return new Shape(result, cnt);
    }
        
    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}