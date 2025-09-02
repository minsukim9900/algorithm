import java.io.*;
import java.util.*;

class Solution {
    public List<Integer> solution(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        boolean[][][] visited = new boolean[4][n][m];
        int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        char[][] board = new char[n][];
        for(int r = 0; r < n; r++) {
            board[r] = grid[r].toCharArray();
        }
        
        List<Integer> arr = new ArrayList<>();
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                for(int i = 0; i < 4; i++) {
                    if(visited[i][r][c]) continue;
                    int cr = r;
                    int cc = c;
                    int cd = i;
                    int count = 0;
                    
                    
                    while(!visited[cd][cr][cc]) {
                        visited[cd][cr][cc] = true;
                        count++;
                        
                        if(board[cr][cc] == 'L') {
                            cd = (cd - 1 + 4) % 4;
                        } else if(board[cr][cc] == 'R') {
                            cd = (cd + 1) % 4;
                        } 
                        
                        cr = (cr + delta[cd][0] + n) % n;
                        cc = (cc + delta[cd][1] + m) % m;
                    }
                    
                    if(count > 0) {
                        arr.add(count);
                    }
                }
            }
        }
        Collections.sort(arr);
        return arr;
    }
}