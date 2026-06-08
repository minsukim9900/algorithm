import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> a[0] == b[0] ? Integer.compare(b[1], a[1]) : Integer.compare(a[0], b[0]));
        int answer = 0;
        int endTime = 0;
        
        for(int[] target : targets) {
            if (endTime > target[0]) {
                endTime = Math.min(target[1], endTime);
                continue;
            }
            
            answer++;
            endTime = target[1];
        }
        
        return answer;
    }
}