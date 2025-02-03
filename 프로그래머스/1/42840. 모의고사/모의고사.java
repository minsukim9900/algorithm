import java.io.*;
import java.util.*;

class Solution {
    
    private static int[][] st = {{1,2,3,4,5}, {2,1,2,3,2,4,2,5}, {3,3,1,1,2,2,4,4,5,5} };
     
    public int[] solution(int[] answers) {
        
        int[] result = new int[3];
        int max = -1;
        for(int i = 0; i < 3; i++) {
            result[i] = check(i, answers);
            max = Math.max(max, result[i]);
        }
        
        ArrayList<Integer> tmp = new ArrayList<>();
        
        for(int i = 0; i<3; i++) {
            if(max == result[i]) tmp.add(i);
        }
        
        int[] answer = new int[tmp.size()];
        
        for(int i = 0; i<tmp.size(); i++) {
            answer[i] = tmp.get(i)+1;
        }
        
        
        return answer;
    }
    
    private static int check(int idx, int[] answers) {
        
        int N = st[idx].length;
        int cnt = 0;
        
        for(int i = 0; i < answers.length; i++) {
            
            int pCurr = answers[i];
            int stCurr = st[idx][(i+N) % N];
            
            if (pCurr == stCurr) cnt++;
            
        }
        
        return cnt;
        
    }
}