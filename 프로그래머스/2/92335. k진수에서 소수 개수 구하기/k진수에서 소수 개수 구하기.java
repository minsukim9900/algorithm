import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String x = Integer.toString(n, k);
        
        String[] nums = x.split("0");
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i].isEmpty()) {
                continue;
            }
            
            long num = Long.parseLong(nums[i]);
            
            if (isPrime(num)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }

        for (long i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}