import java.util.*;

class Solution {
    private static int N, oCnt, xCnt;
    
    public int solution(String[] boardInfo) {
        N = boardInfo.length;
        oCnt = 0;
        xCnt = 0;
        
        char[][] board = new char[N][N];
        
        for (int r = 0; r < N; r++) {
            String str = boardInfo[r];
            for (int c = 0; c < N; c++) {
                char state = str.charAt(c);
                board[r][c] = state;
                
                if (state == 'O') {
                    oCnt++;
                } else if (state == 'X') {
                    xCnt++;
                }
            }
        }
        
        int answer = 0;
        if (xCnt > oCnt || oCnt - xCnt > 1) {
            return 0;
        }
        
        if (oCnt <= 2) {
            return 1;
        }
        
        boolean oFlag = false;
        boolean xFlag = false;
        
        for (int r = 0; r < N; r++) {
            char pre = board[r][0];
            boolean isPoss = true;
            
            if (pre == '.') continue;
            
            for (int c = 1; c < N; c++) {
                if (pre != board[r][c]) {
                    isPoss = false;
                    break;
                }
            }
            
            if (isPoss) {
                if (pre == 'O') {
                    oFlag = true;
                } else {
                    xFlag = true;
                }
            }
            
            if (oFlag && xFlag) {
                return 0;
            }
        }
        
        for (int c = 0; c < N; c++) {
            char pre = board[0][c];
            boolean isPoss = true;
            
             if (pre == '.') continue;
            
            for (int r = 1; r < N; r++) {
                if (pre != board[r][c]) {
                    isPoss = false;
                    break;
                }
            }
            
            if (isPoss) {
                if (pre == 'O') {
                    oFlag = true;
                } else {
                    xFlag = true;
                }
            }
            
            if (oFlag && xFlag) {
                return 0;
            }
        }
        
        char pre = board[0][0];
        if (pre != '.') {
            int pr = 0;
            int pc = 0;
            boolean flag = true;
            for(int i = 1; i < 3; i++) {
                if(pre != board[pr + i][pc + i]) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                if (pre == 'O') {
                    oFlag = true;
                } else {
                    xFlag = true;
                }
                
                if (oFlag && xFlag) {
                    return 0;
                }
            }
        }
        
        pre = board[2][0];
        if (pre != '.') {
            int pr = 2;
            int pc = 0;
            boolean flag = true;
            for(int i = 1; i < 3; i++) {
                if(pre != board[pr - i][pc + i]) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                if (pre == 'O') {
                    oFlag = true;
                } else {
                    xFlag = true;
                }
                
                if (oFlag && xFlag) {
                    return 0;
                }
            }
        }
        
        if (oFlag && oCnt != xCnt + 1) return 0;
        if (xFlag && oCnt != xCnt) return 0;
        
        return 1;
    }
}