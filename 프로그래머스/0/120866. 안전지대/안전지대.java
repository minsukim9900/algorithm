import java.util.*;

class Solution {
    private static int N;
    
    private final int[][] DELTA = {{-1 ,0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    
    private boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
    public int solution(int[][] board) {
        int answer = 0;
        
        N = board.length;
        
        int[][] buffer = new int[N][N];
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] == 1) {
                    for (int i = 0; i < 8; i++) {
                        int nr = r + DELTA[i][0];
                        int nc = c + DELTA[i][1];
                        
                        if (isRange(nr, nc)) {
                            buffer[nr][nc]++;
                        }
                    }
                }
            }
        }
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] == 0 && buffer[r][c] == 0) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}