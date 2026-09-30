import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[0] == b[0] ? Integer.compare(b[1], a[1]) : Integer.compare(a[0], b[0]));
        
        int answer = 1;
        
        for (int i = 0; i < routes.length; i++) {
            pq.add(routes[i]);
        }
        
        int end = pq.poll()[1];
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            
            int currStart = curr[0];
            int currEnd = curr[1];
            
            if (end >= currStart) {
                end = Math.min(end, currEnd);
                continue;
            }
            
            end = currEnd;
            answer++;
        }
        
        return answer;
    }
}