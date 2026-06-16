import java.util.*;
class Solution {
    private static List<Integer>[] adj;
    private static long answer;
    private static boolean[] visited;
    private static int[] a;
    
    public long solution(int[] value, int[][] edges) {
        a = value;
        int N = a.length;
        long total = 0L;
        for(int x : a) {
            total += x;
        }
        
        if(total != 0) {
            return -1;
        }
        
        
        adj = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            
            adj[x].add(y);
            adj[y].add(x);
        }
        
        
        answer = 0;
        visited = new boolean[N];
        dfs(0);
        return answer;
    }
    
    private long dfs(int node) {
        visited[node] = true;
        
        long sum = a[node];
        
        for(int i = 0; i < adj[node].size(); i++) {
            int next = adj[node].get(i);
            
            if (visited[next]) {
                continue;
            }
            
            long childSum = dfs(next);
            answer += Math.abs(childSum);
            sum += childSum;
        }
        
        return sum;
    }
}