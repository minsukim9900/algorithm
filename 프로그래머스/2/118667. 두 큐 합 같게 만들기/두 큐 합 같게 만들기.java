import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum = 0L;
        long leftSum = 0;
        long rightSum = 0;
        
        int n = queue1.length;
        int m = queue2.length;
        
        int[] queue = new int[n + m];
        
        for (int i = 0; i < n + m; i++) {
            if (i < n) {
                queue[i] = queue1[i];
                leftSum += queue[i];
            } else {
                queue[i] = queue2[i - n];
                rightSum += queue[i];
            }
            
            sum += queue[i];
        }
        
        
        int leftIdx = 0;
        int rightIdx = n;
        
        
        int answer = -1;
        
        while (true) {
            answer++;
            
            if (leftSum == rightSum) {
                break;
            } else if(leftSum > rightSum) {
                leftSum -= queue[leftIdx];
                rightSum += queue[leftIdx];
                
                leftIdx = (leftIdx + 1) % (n + m);
            } else {
                rightSum -= queue[rightIdx];
                leftSum += queue[rightIdx];
                
                rightIdx = (rightIdx + 1) % (n + m);
            }
            
            if (answer == (n + m) * 2) {
                answer = -1;
                break;
            }
        }
        
        return answer;
    }
}