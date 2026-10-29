import java.util.*;

class Solution {
    boolean solution(String s) {
        
        Deque<Character> stack = new ArrayDeque<>();
        char[] arr = s.toCharArray();
        
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            
            if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                
                stack.pollFirst();
            } else {
                stack.addFirst(c);
            }
        }
        
        if (!stack.isEmpty()) {
            return false;
        }
        
        return true;
    }
}