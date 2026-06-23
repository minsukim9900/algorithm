import java.util.*;

class Solution {
    public String solution(String s) {
        
        String[] strs = s.split(" ", -1);
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() == 0) {
                if (i != strs.length - 1) {
                    sb.append(" ");
                }
                continue;
            }
            
            char[] temp = strs[i].toCharArray();
            
            if(!(temp[0] >= '0' && temp[0] <= '9')) {
                temp[0] &= (~(1 << 5));
            }
            
            sb.append(temp[0]);
            
            for(int j = 1; j < temp.length; j++) {
                temp[j] |= (1 << 5);
                sb.append(temp[j]);
            }
            
            if(i == strs.length - 1) {
                continue;
            }
            sb.append(" ");
        }
        
        return sb.toString();
    }
}