import java.io.*;
import java.util.*;

class Solution {
    private static int N;
    private static List<Integer>[] adj;
    private static int answer;
    
    public int solution(int n, int[][] lighthouse) {
        N = n;
        
        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < N - 1; i++) {
            adj[lighthouse[i][0]].add(lighthouse[i][1]);
            adj[lighthouse[i][1]].add(lighthouse[i][0]);
        }
        
        answer = 0; 
        
        search(1, new boolean[N + 1]);
        
        return answer;
    }
    
    private static boolean search(int node, boolean[] visited) {
        visited[node] = true;
        
        int childCount = 0;
        int lightCount = 0;
        
        for(int next : adj[node]) {
            if(visited[next]) continue;
            
            childCount++;
            
            if(search(next, visited)) {
                lightCount++;
            }
        }
        
        if(childCount == lightCount) {
            return false;
        } else {
            answer++;
            return true;
        }
    }
}
