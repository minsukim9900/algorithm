import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        
        Set<Integer> nums = new HashSet<>();
        
        int[] dp = new int[n];
        
        for (int length = 1; length < n + 1; length++) {
            for (int left = 0; left < n; left++) {
                int right = (left + length - 1) % n;
                
                dp[left] += elements[right];
                nums.add(dp[left]);
            }
        }
        return nums.size();
    }
}