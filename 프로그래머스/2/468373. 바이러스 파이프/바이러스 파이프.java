import java.util.*;

class Solution {
    private static int N, K, answer;
    private static List<Integer>[][] adj;
    public int solution(int n, int infection, int[][] edges, int k) {
        N = n;
        K = k;
        answer = 0;
        
        adj = new ArrayList[n + 1][4];
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < 4; j++) {
                adj[i][j] = new ArrayList<>();
            }
        }
        
        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int type = edge[2];
            
            adj[from][type].add(to);
            adj[to][type].add(from);
        }
        
        boolean[] virus = new boolean[N + 1];
        virus[infection] = true;
        
        dfs(0, virus, 1);
        
        return answer;
    }
    
    private static void dfs(int depth, boolean[] virus, int sum) {
        answer = Math.max(answer, sum);
        
        if(depth == K || sum == N) {
            return;
        }
        
        for(int type = 1; type <= 3; type++) {
            List<Integer> nextNode = infectNode(virus, type);
            
            if (nextNode.isEmpty()) continue;
            
            dfs(depth + 1, virus, sum + nextNode.size());
            
            for(int node : nextNode) {
                virus[node] = false;
            }
        }
    }
    
    private static List<Integer> infectNode(boolean[] virus, int type) {
        Queue<Integer> q = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        
        for(int i = 1; i < N + 1; i++) {
            if(!virus[i]) continue;
            
            q.add(i);
        }
        
        while(!q.isEmpty()) {
            int curr = q.poll();
            
            for(int nextNode : adj[curr][type]) {
                if(virus[nextNode]) continue;
                
                virus[nextNode] = true;
                q.add(nextNode);
                result.add(nextNode);
            }
        }
        
        return result;
    }
}