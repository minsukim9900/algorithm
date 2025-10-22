import java.io.*;
import java.util.*;

class Solution {
    private static int N;
    private static List<int[]>[] adj;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        adj = new ArrayList[n + 1];
        
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int[] fare : fares) {
            adj[fare[0]].add(new int[] {fare[1], fare[2]});
            adj[fare[1]].add(new int[] {fare[0], fare[2]});
        }
        
        int[] start = dijkstra(s);
        int[] startA = dijkstra(a);
        int[] startB = dijkstra(b);
        
        int answer = Integer.MAX_VALUE;
        for(int i = 1; i<= n; i++) {
            answer = Math.min(answer, start[i] + startA[i] + startB[i]);
        }
        
        return answer;
    }
    
    private static int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] {start, dist[start]});
        
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            
            if(dist[curr[0]] != curr[1]) continue;
            
            for(int[] next : adj[curr[0]]) {
                if(dist[next[0]] > dist[curr[0]] + next[1]) {
                    dist[next[0]] = dist[curr[0]] + next[1];
                    pq.add(new int[] {next[0], dist[next[0]]});
                }
            }
        }
        return dist;
    }
    
}