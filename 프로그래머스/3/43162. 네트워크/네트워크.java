import java.util.*;

class Solution {
    private static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visited = new boolean[n];
        
        for (int node = 0; node < n; node++) {
            if (visited[node]) {
                continue;
            }
            
            bfs(node, n, computers);
            answer++;
        }
        return answer;
    }
    
    private static void bfs(int start, int n, int[][] computers) {
        visited[start] = true;
        Queue<Integer> q = new ArrayDeque<>();
        
        q.add(start);
        
        while (!q.isEmpty()) {
            int curr = q.poll();
            
            for (int next = 0; next < n; next++) {
                if (visited[next] || computers[curr][next] == 0) {
                    continue;
                }
                
                visited[next] = true;
                q.add(next);
            }
        }
    }
}