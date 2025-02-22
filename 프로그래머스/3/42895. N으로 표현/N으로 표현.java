import java.io.*;
import java.util.*;

class Solution {
    
    private static ArrayList<Set<Integer>> dp = new ArrayList<>();
    
    public int solution(int N, int number) {
        
        for(int i = 0; i<=8; i++) {
             dp.add(new HashSet<>());
        }
        
        int tmp = 0;
        for(int i = 0; i<8; i++) {
            tmp = tmp * 10 + N;
            dp.get(i+1).add(tmp);
        }
        
        for(int i = 2; i<=8; i++) {
            for(int j = 1; j<i; j++) {
                
                int idx = i - j;
                
                for(int x : dp.get(j)) {
                    for(int y : dp.get(idx)) {
                        
                        dp.get(i).add(x + y);
                        dp.get(i).add(x - y);
                        dp.get(i).add(x * y);
                        if(y != 0){
                         dp.get(i).add(x / y);   
                        }
                        
                    }
                }
                
            }
            
        }
        
        for(int i = 1; i<=8; i++) {
            for(int x : dp.get(i)) {
                if(x == number) return i;
            }
        }
        
        
        return -1;
        
    }
}