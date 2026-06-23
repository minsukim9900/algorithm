import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (o1, o2) -> Integer.compare(o1[1], o2[1]));
        
        int N = jobs.length;
        int jobIndex = 0;
        int finishJob = 0;
        int endTime = 0;
        int answer = 0;
        
        while(finishJob < N) {
            while(jobIndex < N && jobs[jobIndex][0] <= endTime) {
                pq.add(jobs[jobIndex++]);
            }
            
            if (!pq.isEmpty()) {
                int[] job = pq.poll();
                answer += endTime - job[0] + job[1];
                endTime += job[1];
                finishJob++;
            } else {
                endTime = jobs[jobIndex][0];
            }
        }
        
        return answer / N;
    }
}