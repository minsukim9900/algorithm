import java.util.*;

class Solution {
    // 직업의 번호, 작업의 요청 시각, 작업의 소요 시간을 저장
    // 디스크 컨트롤러는 하드디스크가 작업을 하지 않고, 대기 큐가 비어있지 않다면 가장 우선순위가 높은 작업을 꺼내서 작업을 시킨다.
    // 작업의 소요시간이 짧은 것, 작업의 요청 시각이 빠른 것, 작업의 번호가 작은 것 순으로 우선순위가 높다.
    // 하드디스크는 작업을 한 번 시작하면 그 작업을 마칠 때까지 그 작업만 수행
    
    private static List<int[]>[] job;
    private static final int MAX = 510_001;
    
    public int solution(int[][] jobs) {
        int N = jobs.length;
        
        job = new ArrayList[MAX];
        for(int i = 0; i < MAX; i++) {
            job[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < N; i++) {
            int s = jobs[i][0];
            int l = jobs[i][1];
            
            job[s].add(new int[] {i, s, l});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[2] == b[2] 
            ? a[1] == b[1] ? Integer.compare(a[0], b[0]) 
            : Integer.compare(a[1], b[1]) : Integer.compare(a[2], b[2]));
        
        int answer = 0;
        int[] hardDisk = new int[] {-1, 0, 0};
        boolean flag = false;
        for (int time = 0; time < MAX; time++) {
            
            if (hardDisk[2] == time) {
                answer += (hardDisk[2] - hardDisk[1]);
                flag = true;
            }
            
            if(!job[time].isEmpty()) {
                for(int i = 0; i < job[time].size(); i++) {
                    pq.add(job[time].get(i));
                }
            }
            
            if(flag) {
                if(!pq.isEmpty()) {
                    int[] next = pq.poll();
                    hardDisk = new int[] {next[0], next[1], time + next[2]};
                    flag = false;
                }
            }
        }
        
        
        return answer / N;
    }
}