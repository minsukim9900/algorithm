import java.util.*;

class Solution {
    private List<int[]>[] adj;
    private boolean[] isGates, isSummits;
    
    private int[] dijkstra(int n, int[] gates, int[] summits) {
        int[] dist = new int[n + 1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        
        for (int i = 0; i < gates.length; i++) {
            dist[gates[i]] = 0;
            pq.add(new int[] {gates[i], 0});
        }
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            
            int node = curr[0];
            int weight = curr[1];
            
            if (weight != dist[node]) {
                continue;
            }
            
            if (isSummits[node]) {
                continue;
            }
            
            for (int[] edge : adj[node]) {
                int next = edge[0];
                int nextWeight = edge[1];
                
                int intensity = Math.max(weight, nextWeight);
                
                if (dist[next] > intensity) {
                    dist[next] = intensity;
                    pq.add(new int[] {next, intensity});
                }
            }
        }
        
        return dist;
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        adj = new ArrayList[n + 1];
        
        for (int node = 1; node < n + 1; node++) {
            adj[node] = new ArrayList<>();
        }
        
        isGates = new boolean[n + 1];
        
        for (int i = 0; i < gates.length; i++) {
            isGates[gates[i]] = true;
        }
        
        isSummits = new boolean[n + 1];
        
        for (int i = 0; i < summits.length; i++) {
            isSummits[summits[i]] = true;
        }
        
        
        for (int i = 0; i < paths.length; i++) {
            int[] path = paths[i];
            
            int nodeX = path[0];
            int nodeY = path[1];
            int weight = path[2];
                
            adj[nodeX].add(new int[] {nodeY, weight});
            adj[nodeY].add(new int[] {nodeX, weight});
        }
        
        int[] dist = dijkstra(n, gates, summits);
        
        int[] answer = new int[] {0, Integer.MAX_VALUE};
        
        for (int i = 1; i < n + 1; i++) {
            if (isSummits[i] && answer[1] > dist[i]) {
                answer[1] = dist[i];
                answer[0] = i;
            }
        }
        
        
        return answer;
    }
}