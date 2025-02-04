import java.io.*;
import java.util.*;

class Solution {
    
    private static int N, M;
    private static char[] charArr;
    private static Set<Integer> nums = new HashSet<>();
    private static int visited;
    private static char[] result;
    
    public int solution(String numbers) {
        
        charArr = dividing(numbers);
        N = numbers.length();
        for(int i = 1; i <= charArr.length; i++) {
            
            M = i;
            visited = 0;
            result = new char[M];
            
            dfs(0);
            
        }
        
        System.out.println(nums);
        
        int answer = nums.size();
        return answer;
    }
    
    private static void dfs(int depth) {
        
        if(depth == M) {
            
            String str = "";
            for(int i = 0; i< M; i++) {
                if(i == 0 && result[i] == '0') continue;
                str += result[i];
            }
            
            if(!str.equals("") && isPrime(str)) {
                nums.add(Integer.parseInt(str));
            }
            
        }else {
            for(int i = 0; i < N; i++) {
                
                if((visited & (1<<i)) == 0) {
                    visited |= (1<<i);
                    result[depth] = charArr[i];
                    dfs(depth+1);
                    visited ^= (1<<i);
                } 
                
            }
        }
        
    }
    
    private static boolean isPrime(String str) {
        int num = Integer.parseInt(str);
        
        if(num <= 1) return false;
        
        for(int i = 2; i<num; i++) {
            if(num % i == 0) return false;
        }
        
        return true;
        
    }
    
    
    private static char[] dividing(String numbers) {
        
         char[] charArr = new char[numbers.length()];
        for(int i = 0; i<numbers.length(); i++) {
            charArr[i] = numbers.charAt(i);
        }
        
        return charArr;
        
    }
}