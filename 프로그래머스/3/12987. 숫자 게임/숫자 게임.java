import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        Arrays.sort(A);
        
        int n = B.length;
        int answer = 0;
        
        for (int i = 0; i < n; i++) {
            pq.add(B[i]);
        }
        
        int idx = 0;
        while (!pq.isEmpty()) {
            int a = A[idx];
            int b = pq.poll();
            
            if (b > a) {
                answer++;
                idx++;
            }
        }
        return answer;
    }
}