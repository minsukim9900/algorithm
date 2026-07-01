import java.util.*;

class Solution {
    private static int[] dist;
    private static List<Integer>[] adj;
    
    public int solution(int n, int[][] edge) {
        dist = new int[n + 1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        adj = new ArrayList[n + 1];
        for(int i = 1; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int[] e : edge) {
            int from = e[0];
            int to = e[1];
            
            adj[from].add(to);
            adj[to].add(from);
        }
        
        return bfs(n);
    }
    
    private static int bfs(int n) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {1, 0});
        dist[1] = 0;
        
        int result = 0;
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0];
            int d = curr[1];
            
            if(d != dist[node]) {
                continue;
            }
            
            for(int next : adj[node]) {
                if(dist[next] > d + 1) {
                    dist[next] = d + 1;
                    result = Math.max(dist[next], result);
                    q.add(new int[] {next, dist[next]});
                }
            }
        }
        
        int answer = 0;
        for(int i = 1; i < n + 1; i++) {
            if(dist[i] == result) {
                answer++;
            }
        }
        
        return answer;
    }
}