import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        
        Set<Long> nums = new HashSet<>();
        
        for (int length = 1; length < n + 1; length++) {
            long sum = 0L;
            
            for (int i = 0; i < length; i++) {
                sum += elements[i];
            }
            nums.add(sum);
            
            for (int left = 1; left < n; left++) {
                int right = (left + length - 1) % n;
                
                sum -= elements[left];
                sum += elements[right];
                nums.add(sum);
            }
        }
        return nums.size();
    }
}