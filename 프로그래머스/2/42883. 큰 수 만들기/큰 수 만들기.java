import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        
        char[] nums = number.toCharArray();
        
        Deque<Integer> dq = new ArrayDeque<>();
        
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i] - '0';
            
            while (count < k && !dq.isEmpty() && dq.peekLast() < num) {
                count++;
                dq.pollLast();
            }
            
            dq.add(num);
        }
        
        while (count < k && !dq.isEmpty()) {
                count++;
                dq.pollLast();
        }
        
        while (!dq.isEmpty()) {
            sb.append(dq.poll());
        }
        
        String answer = sb.toString();
        return answer;
    }
}