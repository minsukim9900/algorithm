import java.io.*;
import java.util.*;

class Solution {
    
    private static int[] answer;
    
    public int[] solution(int[][] arr) {
        
        
        
        answer = new int[2];
        check(arr, arr.length, 0, 0);
        
        return answer;
    }
    
    
    private static void check(int[][] board, int size, int x, int y){
        
        if(isCompress(board, size, x, y)){
            
            answer[board[x][y]]++;
            return;
            
        }else{
            
            check(board, size/2, x,y);
            check(board, size/2, x+(size/2), y);
            check(board, size/2, x, y+(size/2));
            check(board, size/2, x+(size/2), y+(size/2));
            
            
        }
        
    }
    
    private static boolean isCompress(int[][] board, int size, int x, int y){
        
        
        for(int r = x; r<x+size; r++){
            for(int c = y; c<y+size; c++){
                if(board[x][y] != board[r][c] ) return false;
            }
        }
        
        return true;
        
    }
    
}