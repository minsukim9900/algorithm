import java.util.*;

class Solution {
    private static int N;
    private static int[] count;
    private static List<Integer>[] adj;
    
    public int solution(int n, int[][] wires) {
        N = n;
        count = new int[N + 1];
        adj = new ArrayList[N + 1];
        
        for (int node = 1; node < N + 1; node++) {
            adj[node] = new ArrayList<>();
        }
        
        for (int i = 0; i < N -1; i++) {
            int[] wire = wires[i];
            
            int from = wire[0];
            int to = wire[1];
            
            adj[from].add(to);
            adj[to].add(from);
        }
        
        dfs(1, new boolean[N + 1]);
        
        int answer = Integer.MAX_VALUE;
        
        for (int node = 2; node < N + 1; node++) {
            int diff = Math.abs(count[1] - count[node] * 2);
            
            answer = Math.min(answer, diff);
        }
        
        return answer;
    }
    
    private static int dfs(int node, boolean[] visited) {
        visited[node] = true;
        int cnt = 1;
        
        for (int next : adj[node]) {
            if (visited[next]) {
                continue;
            }
            
            cnt += dfs(next, visited);
        }
        
        return count[node] = cnt;
    }
}