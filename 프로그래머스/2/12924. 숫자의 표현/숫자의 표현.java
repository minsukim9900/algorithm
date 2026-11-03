import java.util.*;

class Solution {
    
    public int solution(int n) {
        int answer = 0;
        
        while (n % 2 == 0) {
            n /= 2;
        }
        
        for (int i = 1; (long) i * i <= n; i += 2) {
            if (n % i == 0) {
                answer += ((long) i * i == n ? 1 : 2);
            }
        }
        
        return answer;
    }
}