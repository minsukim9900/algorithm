import java.io.*;
import java.util.*;

class Solution {
    private static int N, answer;
    private static boolean[] used;
    private static int[] nums;
    private static List<Integer>[] adj;
    
    public int solution(int[] info, int[][] edges) {
        N = info.length;
        answer = 0;
        nums = info;
        used = new boolean[1 << N];
        adj = new ArrayList[N];
        
        for(int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        used[1] = true;
        dfs(1, 1, 0);
        return answer;
    }
    
    private static void dfs(int mask, int sheep, int wolf) {
        if(sheep <= wolf) return;
        answer = Math.max(answer, sheep);
        
        for(int i = 0; i < N; i++) {
            if((mask & (1 << i)) == 0) continue;
            
            for(int v : adj[i]) {
                if((mask & (1 << v)) != 0) continue;
                int nmask = mask | (1 << v);
                int ns = sheep + (nums[v] == 0 ? 1 : 0);
                int nw = wolf + (nums[v] == 1 ? 1 : 0);
                
                if(!used[nmask])
                    dfs(nmask, ns, nw);
            }
        }
    }
}