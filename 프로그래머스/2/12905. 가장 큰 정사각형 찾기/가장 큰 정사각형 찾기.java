import java.util.*;

class Solution {
    private static int N, M;
    
    public int solution(int[][] board) {
        int answer = 0;
        
        N = board.length;
        M = board[0].length;
        
        int[][] prefix = new int[N + 1][M + 1];
        
        for (int r = 0; r < N; r++) {
            int sum = 0;
            
            for (int c = 0; c < M; c++) {
                sum += board[r][c];
                
                prefix[r + 1][c + 1] = prefix[r][c + 1] + sum;
            }
        }
        
        int range = Math.min(N, M);
        
        for (int l = range; l >= 1; l--) {
            int comp = l * l;
            
            for (int sr = 1; sr <= N - l + 1; sr++) {
                for (int sc = 1; sc <= M - l + 1; sc++) {
                    int er = sr + l - 1;
                    int ec = sc + l - 1;
                    
                    int count = prefix[er][ec] - prefix[er][sc - 1] - prefix[sr - 1][ec] + prefix[sr - 1][sc - 1];
                    
                    if (count == comp) {
                        return count;
                    }
                }
            }
        }
        
        return answer;
    }
}