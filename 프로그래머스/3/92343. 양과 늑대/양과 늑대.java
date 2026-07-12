import java.util.*;

class Solution {
    private static int N, M;
    private static int[] animals;
    private static int[] adj;
    
    public int solution(int[] info, int[][] edges) {
        N = info.length;
        adj = new int[N];
        animals = info;
        
        for(int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            
            adj[x] |= (1 << y);
            adj[y] |= (1 << x);
        }
        
        int answer = dfs(1, new boolean[1 << N]);
        return answer;
    }
    
    private static int dfs(int bitmask, boolean[] visited) {
        int sheep = 0;
        int wolf = 0;
        
        for (int i = 0; i < N; i++) {
            if ((bitmask & (1 << i)) == 0) {
                continue;
            }
            
            if (animals[i] == 0) {
                sheep++;
            } else {
                wolf++;
            }
        }
        
        if (sheep <= wolf || visited[bitmask]) {
            return sheep;
        }
        
        visited[bitmask] = true;
        int best = sheep;
        
        for(int i = 0; i < N; i++) {
            if((bitmask & (1 << i)) != 0 || (adj[i] & (bitmask)) == 0) {
                continue;
            }
            
            best = Math.max(best, dfs(bitmask | (1 << i), visited));
        }
        
        return best;
    }
}