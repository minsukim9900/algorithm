import java.util.*;

class Solution {
    private int N, M;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        N = rows;
        M = columns;
        
        int[] answer = new int[queries.length];
        
        int[][] board = new int[N][M];
        
        int num = 1;
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                board[r][c] = num++;
            }
        }
        
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            
            int sr = query[0] - 1;
            int sc = query[1] - 1;
            int er = query[2] - 1;
            int ec = query[3] - 1;
            
            answer[i] = rotate(board, sr, sc, er, ec);
        }
        
        return answer;
    }
    
    private int rotate(int[][] board, int sr, int sc, int er, int ec) {
        int minNum = board[sr][sc];
        
        List<int[]> buffer = new ArrayList<>();
        
        for (int r = sr; r < er + 1; r++) {
            for (int c = sc; c < ec + 1; c++) {
                if (r == sr || r == er || c == sc || c == ec) {
                    if (minNum > board[r][c]) {
                        minNum = board[r][c];
                    }
                    
                    if (r == sr) {
                        if (c == ec) {
                            buffer.add(new int[] {r + 1, c, board[r][c]});
                        } else {
                            buffer.add(new int[] {r, c + 1, board[r][c]});
                        }
                    } else if (c == ec) {
                        if (r == er) {
                            buffer.add(new int[] {r, c - 1, board[r][c]});
                        } else {
                            buffer.add(new int[] {r + 1, c, board[r][c]});
                        }
                    } else if (r == er) {
                        if (c == sc) {
                            buffer.add(new int[] {r - 1, c, board[r][c]});
                        } else {
                            buffer.add(new int[] {r , c - 1, board[r][c]});
                        }
                    } else {
                        buffer.add(new int[] {r - 1, c, board[r][c]});
                    }
                }
            }
        }
        
        for (int[] b : buffer) {
            int r = b[0];
            int c = b[1];
            int v = b[2];
            
            board[r][c] = v;
        }
        
        return minNum;
    }
}