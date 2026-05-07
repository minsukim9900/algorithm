import java.util.*;

class Solution {
    private static int N, M, x, y;
    private static final int INF = 1_000_000_000;
    
    
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        N = n;
        M = m;
        x = startX;
        y = startY;
        
        int[] answer = new int[balls.length];
        
        for(int i = 0; i < balls.length; i++) {
            answer[i] = cal(balls[i]);
        }
        
        return answer;
    }
    
    private int cal(int[] ball) {
        int c = ball[0];
        int r = ball[1];
        
        int result = getUnderCushion(r, c);
        result = Math.min(result, getOverCushion(r, c));
        result = Math.min(result, getLeftCushion(r ,c));
        result = Math.min(result, getRightCushion(r, c));
        
        return result;
    }
    
    private int getUnderCushion(int r, int c) {
        if(c == x && r < y) return INF;
        
        int nr = 0 - r;
        int nc = c;
        
        int distX = Math.abs(x - nc);
        int distY = Math.abs(y - nr);

        return (distX * distX) + (distY * distY);
    }
    
    private int getOverCushion(int r, int c) {
        if(c == x && r > y) return INF;
        
        int nr = N + (N - r);
        int nc = c;
        
        int distX = Math.abs(x - nc);
        int distY = Math.abs(y - nr);

        return (distX * distX) + (distY * distY);
    }
    
    private int getLeftCushion(int r, int c) {
        if(r == y && c < x) return INF;
        
        int nr = r;
        int nc = 0 - c;
        
        int distX = Math.abs(x - nc);
        int distY = Math.abs(y - nr);

        return (distX * distX) + (distY * distY);
    }
    
    private int getRightCushion(int r, int c) {
        if(r == y && c > x) return INF;
        
        int nr = r;
        int nc = M + (M - c);
        
        int distX = Math.abs(x - nc);
        int distY = Math.abs(y - nr);

        return (distX * distX) + (distY * distY);
    }
}