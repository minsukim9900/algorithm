import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        
        for (int i = 0; i < works.length; i++) {
            pq.add(works[i]);
        }
        
        for (int i = 0; i < n; i++) {
            int max = pq.poll();
            
            if (max == 0) {
                return 0;
            }
            
            pq.add(max - 1);
        }
        
        long answer = 0L;
        
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            
            answer += (curr * curr);
        }
        
        return answer;
    }
}