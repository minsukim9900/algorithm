import java.io.*;
import java.util.*;

class Solution {
    private static int N;
    private static List<int[]>[] adj;
    private static final int INF = 10_000_001;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        N = n;
        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < paths.length; i++) {
            int x = paths[i][0];
            int y = paths[i][1];
            int w = paths[i][2];
            
            adj[x].add(new int[] {y, w});
            adj[y].add(new int[] {x, w});
        }
        
        int[] answer = simulate(gates, summits);
        return answer;
    }
    
    private static int[] simulate(int[] gates, int[] summits) {
        boolean[] isGate = new boolean[N + 1];
        boolean[] isSummit = new boolean[N + 1];
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        
        for(int gate : gates) {
            isGate[gate] = true;
            dist[gate] = 0;
            pq.add(new int[] {gate, dist[gate]});
        }
        
        for(int summit : summits) {
            isSummit[summit] = true;
        }
        
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int intensity = curr[1];
            
            if(intensity != dist[node]) continue;
            if(isSummit[node]) continue;
            
            for(int[] next : adj[node]) {
                if(isGate[next[0]]) continue;
                
                int newIntensity = Math.max(intensity, next[1]);
                if(newIntensity < dist[next[0]]) {
                    dist[next[0]] = newIntensity;
                    pq.add(new int[] {next[0], newIntensity});
                }
            }
        }
        
        Arrays.sort(summits);
        int index = 0;
        int ans = INF;
        for(int summit : summits) {
            if(dist[summit] < ans) {
                ans = dist[summit];
                index = summit;
            }
        }
        return new int[] {index, ans};
    }
}