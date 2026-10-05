import java.util.*;

class Solution {
    private boolean check(int[] stones, int k, int people) {
        int count = 0;
        
        for (int stone : stones) {
            if (stone - people < 0) {
                count++;
            } else {
                count = 0;
            }
            
            if (count == k) {
                return false;
            }
        }
        
        return true;
    }
    
    public int solution(int[] stones, int k) {
        int s = 0;
        int e = 1 << 30;
        int answer = 0;
        
        while (s <= e) {
            int mid = s + (e - s) / 2;
            
            if (check(stones, k, mid)) {
                answer = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        
        return answer;
    }
}