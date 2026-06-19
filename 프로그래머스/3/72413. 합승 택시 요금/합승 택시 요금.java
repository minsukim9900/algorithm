import java.util.*;

class Solution {
    private static int N;
    private static final int INF = 100_000_000;
    private static List<int[]>[] adj;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        
        adj = new ArrayList[N + 1];
        for(int node = 1; node < N + 1; node++) {
            adj[node] = new ArrayList<>();
        }
        
        for(int[] fare :fares) {
            int nodeX = fare[0];
            int nodeY = fare[1];
            int weight = fare[2];
            
            adj[nodeX].add(new int[] {nodeY, weight});
            adj[nodeY].add(new int[] {nodeX, weight});
        }
        
        int[] distS = dijkstra(s);
        int[] distA = dijkstra(a);
        int[] distB = dijkstra(b);
        
        int answer = INF;
        for(int node = 1; node < N + 1; node++) {
            answer = Math.min(answer, distA[node] + distB[node] + distS[node]);
        }
        
        return answer;
    }
    
    private int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        dist[start] = 0;
        pq.add(new int[] {start, 0});
        
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int weight = curr[1];
            
            if(dist[node] != weight) {
                continue;
            }
            
            for(int[] next : adj[node]) {
                if(dist[next[0]] > next[1] + weight) {
                    dist[next[0]] = next[1] + weight;
                    pq.add(new int[] {next[0], dist[next[0]]});
                }
            }
        }
        
        return dist;
    }
}