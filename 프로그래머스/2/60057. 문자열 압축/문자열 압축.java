import java.io.*;
import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = s.length();
        int max = answer / 2;
        
        for(int l = 1; l < max + 1; l++) {
            StringBuilder sb = new StringBuilder();
            String pre = s.substring(0, l);
            int count = 1;
            
            for(int c = l; c < s.length(); c += l) {
                String curr = s.substring(c, Math.min(s.length(), c + l));
                
                if(pre.equals(curr)) {
                    count++;
                } else {
                    if(count > 1) {
                        sb.append(count);
                    }
                    
                    
                    sb.append(pre);
                    pre = curr;
                    count = 1;
                }
            }
            
            if(count > 1) {
                sb.append(count).append(pre);
            } else {
                sb.append(pre);
            }
            answer = Math.min(answer, sb.toString().length());
        }
        return answer;
    }
}