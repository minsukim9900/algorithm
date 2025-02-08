import java.io.*;
import java.util.*;

class Solution {
    
    private static int[] visited = new int[4];
    private static ArrayList<Integer>[] adj;
    
    public int solution(int n, int[][] wires) {
        
        adj = new ArrayList[n+1];
        
        int max = 987654321;
        
        for(int w = 0; w < n - 1; w++){
            
            
            for(int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
            }
            
            for(int i = 0; i < n-1; i++) {
                if(w == i) continue;
                adj[wires[i][0]].add(wires[i][1]);
                adj[wires[i][1]].add(wires[i][0]);
            }
            
            int m = bfs( wires[(w+1) % (n - 1)][0] );
            m = Math.abs(m- (n - m));
            max = Math.min(max, m);
            
            visited[0] = 0;
            visited[1] = 0;
            visited[2] = 0;
            visited[3] = 0;
        }
            
        
        return max;
    }
    
    private static int bfs(int st) {
        
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(st);
        visit(st);
        int cnt = 1;
        
        while(!q.isEmpty()) {
            
            int curr = q.poll();
            
            for(int c : adj[curr]) {
                
                
                if(!isVisit(c)) {
                    //System.out.println(c);
                    visit(c);
                    q.offer(c);
                    cnt++;
                }
                
            }
        }
        
        return cnt;
        
    }
    
    private static boolean isVisit(int i) {
        
        
        if((visited[i / 32] & (1 << (i % 32))) == 0) return false;
        
        return true;
        
    }
    
    private static void visit(int i) {
        
        visited[i / 32] |= ( 1 << (i % 32));
        
    }
    
}