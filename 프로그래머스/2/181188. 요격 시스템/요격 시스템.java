import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));
        int answer = 0;
        int endTime = 0;
        
        for(int[] target : targets) {
            if (endTime > target[0]) {
                continue;
            }
            
            answer++;
            endTime = target[1];
        }
        
        return answer;
    }
}