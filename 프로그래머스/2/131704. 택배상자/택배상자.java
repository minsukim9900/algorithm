import java.util.*;

class Solution {
    public int solution(int[] order) {
        int n = order.length;
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        int boxNum = 1;
        
        for (int i = 0; i < n; i++) {
            int num = order[i];
            
            while (boxNum <= num) {
                stack.push(boxNum++);
            }
            
            if (!stack.isEmpty() && stack.peek() == num) {
                stack.pop();
                answer++;
                continue;
            }
            
            break;
        }
        
        return answer;
    }
}