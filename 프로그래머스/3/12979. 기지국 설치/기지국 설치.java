import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int len = w * 2 + 1;
        
        int start = 1;
        
        for (int i = 0; i < stations.length; i++) {
            int standard = stations[i];
            
            int left = Math.max(0, standard - w);
            int right = Math.min(n, standard + w);
            
            
            answer += (left - start) / len;
            
            if ((left - start) % len > 0) {
                answer++;
            }
            
            start = right + 1;
            
            if (start > n) {
                break;
            }
        }
        
        if (start <= n) {
            int right = n + 1;
            
            answer += (right - start) / len;
            
            if ((right - start) % len > 0) {
                answer++;
            }
        }

        return answer;
    }
}