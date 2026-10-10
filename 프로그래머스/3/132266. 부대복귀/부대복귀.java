import java.util.*;

class Solution {
    private List<Integer>[] adj;
    
    private int[] bfs(int destination, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[destination] = 0;
        
        Queue<Integer> q = new ArrayDeque<>();
        q.add(destination);
        
        while (!q.isEmpty()) {
            int curr = q.poll();
            int d = dist[curr];
            
            for (int next : adj[curr]) {
                if (dist[next] == -1) {
                    dist[next] = d + 1;
                    q.add(next);
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
            
            answer[i] = dist[node];
        }
        
        return answer;
    }
}