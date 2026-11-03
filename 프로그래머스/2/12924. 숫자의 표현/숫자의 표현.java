import java.util.*;

class Solution {
    
    public int solution(int n) {
        int answer = 0;
        
        int sum = 0;
        int left = 0;
        
        for (int i = 1; i < n + 1; i++) {
            sum += i;
            
            while (sum > n) {
                sum -= left++;
            }
            
            if (sum == n) {
                answer++;
            }
        }
        
        return answer;
    }
}