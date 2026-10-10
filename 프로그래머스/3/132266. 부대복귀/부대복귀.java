import java.util.*;

class Solution {
    private List<Integer>[] adj;
    
    private final int INF = 1_000_000_000;
    
    private int[] bfs(int destination, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[destination] = 0;
        
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {destination, 0});
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            
            int node = curr[0];
            int d = curr[1];
            
            if (dist[node] != d) {
                continue;
            }
            
            for (int next : adj[node]) {
                if (dist[next] > d + 1) {
                    dist[next] = d + 1;
                    q.add(new int[] {next, d + 1});
                }
            }
        }
        
        return dist;
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        adj = new ArrayList[n + 1];
        
        for (int i = 1; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < roads.length; i++) {
            int[] road = roads[i];
            
            int from = road[0];
            int to = road[1];
            
            adj[from].add(to);
            adj[to].add(from);
        }
        
        int[] dist = bfs(destination, n);
        
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            int node = sources[i];
            int state = dist[node] == INF ? -1 : dist[node];
            
            answer[i] = state;
        }
        
        return answer;
    }
}