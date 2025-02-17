import java.io.*;
import java.util.*;

class Solution {
    
    private static Edge[] edge;
    private static int[] p;
    private static boolean[] visited;
    
    public int solution(int n, int[][] costs) {
        
        p = new int[n];
        visited = new boolean[n];
        
        for(int i = 0; i<n; i++) {
            p[i] = i;
        }
        
        int M = costs.length;
        
        edge = new Edge[M];
        
        for(int i = 0; i<M; i++) {
            int from = costs[i][0];
            int to = costs[i][1];
            int w = costs[i][2];
            
            edge[i] = new Edge(from, to, w);
        }
        
        Arrays.sort(edge);
        
        
        int answer = 0;
        
        for(int i = 0; i<M; i++) {
            Edge curr = edge[i];
            
            if(findP(curr.from) != findP(curr.to)) {
                union(findP(curr.from), findP(curr.to));
                answer += curr.w;
            }
            
        }
        
        
        return answer;
    }
    
    private static class Edge implements Comparable<Edge> {
        int from, to, w;
        
        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
        
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
        
    }
    
    private static int findP(int x) {
        if (x != p[x]) {
            p[x] = findP(p[x]);
        }
        
        return p[x];
    }
    
    private static void union(int x, int y) {
        p[y] = x;
    }
    
}