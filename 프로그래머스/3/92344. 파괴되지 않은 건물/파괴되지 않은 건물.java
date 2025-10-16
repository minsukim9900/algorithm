import java.io.*;
import java.util.*;

class Solution {
    private static int N, M;
    private static int[][] prefix;
    
    public int solution(int[][] board, int[][] skill) {
        N = board.length;
        M = board[0].length;
        
        prefix = new int[N + 1][M + 1];
        
        for(int[] s : skill) {
            int state = s[0] == 1 ? -1 : 1;
            int sr = s[1];
            int sc = s[2];
            int er = s[3];
            int ec = s[4];
            int degree = s[5];
            
            prefix[sr][sc] += (state * degree);
            prefix[er + 1][sc] += ((state * -1) * degree);
            prefix[sr][ec + 1] += ((state * -1) * degree);
            prefix[er + 1][ec + 1] += (state * degree);
        }
        
        for(int c = 1; c < M; c++) {
            prefix[0][c] += prefix[0][c - 1];
        }
        
        for(int r = 1; r < N; r++) {
            int sum = 0;
            for(int c = 0; c < M; c++) {
                sum += prefix[r][c];
                prefix[r][c] = sum + prefix[r - 1][c];
            }
        }
        
        int answer = 0;
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                board[r][c] += prefix[r][c];
                if(board[r][c] > 0 ) answer++;
            }
        }
        
        return answer;
    }
}