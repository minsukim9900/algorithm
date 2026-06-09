import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int targetX = scores[0][0];
        int targetY = scores[0][1];
        int targetTotal = targetX + targetY;
        
        Arrays.sort(scores, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(b[0], a[0]));
        
        int maxY = 0;
        int rank = 1;
        
        for(int[] score : scores) {
            int x = score[0];
            int y = score[1];
            
            if(y < maxY) {
                if(x == targetX && y == targetY) {
                    return -1;
                }
                continue;
            }
            
            maxY = Math.max(maxY, y);
            
            if(x + y > targetTotal) {
                rank++;
            }
        }
        
        return rank;
    }
}