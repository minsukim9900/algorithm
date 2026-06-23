import java.util.*;

class Solution {
    public String solution(String s) {
        
        char[] c = s.toCharArray();
        
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for(int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                sb.append(c[i]);
                flag = true;
                continue;
            }
            
            if (flag) {
                if (!(c[i] >= '0' && c[i] <= '9')) {
                    c[i] &= (~(1 << 5));
                }
                sb.append(c[i]);
                flag = false;
            } else {
                sb.append(c[i] |= (1 << 5));
            }
        }
        
        return sb.toString();
    }
}