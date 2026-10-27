import java.util.*;

class Solution {
    private String convert(String p) {
        if (p.isEmpty()) {
            return "";
        }
        
        int count = 0;
        boolean flag = true;
        int idx = 0;
        
        for (int i = 0; i < p.length(); i++) {
            count += p.charAt(i) == '('? 1 : -1;
            idx = i;
            
            if (count < 0) {
                flag = false;
            }
            
            if (count == 0) {
                break;
            }
        }
        
        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);
        
        if (flag) {
            return u + convert(v);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(convert(v));
        sb.append(")");
        
        for (int i = 1; i < u.length() - 1; i++) {
            char c = u.charAt(i) == '(' ? ')' : '(';
            sb.append(c);
        }
        
        return sb.toString();
    }
    
    public String solution(String p) {
        return convert(p);
    }
}