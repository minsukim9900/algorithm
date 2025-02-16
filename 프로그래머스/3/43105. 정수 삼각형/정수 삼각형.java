import java.io.*;
import java.util.*;

class Solution {
    
    public int solution(int[][] triangle) {
        
        int size = triangle.length;
        
        int[][] dp = new int[size + 1][triangle[size-1].length];
        
        dp[1][0] = triangle[0][0];
        
        if(size >= 2) {
            dp[2][0] = triangle[1][0];
            dp[2][1] = triangle[1][1];
            
            for (int i = 3; i<= size; i++) {
                
                for(int j = 0; j < triangle[i - 1].length; j++) {
                    int tmp = j - 1;
                    if(tmp < 0) tmp = 0;
                    
                    dp[i][j] = Math.max(dp[i-1][tmp], dp[i-1][j]) + triangle[i-1][j];
                    
                }
                
            }
            
        }
        
        int max = 0;
        for(int m : dp[size]) {
            max = Math.max(max, m);
        }
        
        return max + triangle[0][0];
       
    }
    
    
}