import java.io.*;
import java.util.*;

class Solution {
    
    private static boolean[] visited, light;
    private static boolean[] check;
    private static List<Integer>[] arr;
    private int[][] arr1;
    private static ArrayList<int[]> count = new ArrayList<>();
    private static int[] child;
    
    public int solution(int n, int[][] lighthouse) {
        
        visited = new boolean[n + 1];
        light = new boolean[n + 1];
        check = new boolean[n + 1];
        child = new int[n + 1];        

        arr = new ArrayList[n + 1];
        for(int i = 1; i<=n; i++) {
            arr[i] = new ArrayList<>();
        }
        
        
        for (int i = 0; i<lighthouse.length; i++) {
            
            int from = lighthouse[i][0];
            int to = lighthouse[i][1];
            
           arr[from].add(to);
           arr[to].add(from);
            
        }      
        
        dfs(1);
        
        visited = new boolean[n + 1];
        
      
        
        int answer = 0;
        for(boolean b : light) {
            if(b) answer++;
        }

        return answer;
    }
    
    private static int dfs(int st) {
        check[st] = true;
        visited[st] = true;
        int cnt = 1;
        
        for(int w : arr[st]) {
            if(visited[w]) continue;
            cnt += dfs(w);
        }
        
        
        if(cnt != 1) {
            
            boolean isLight = true;
            for(int w : arr[st]) {
                
                if(check[w]) continue;
                check[w] = true;
                if(!light[w]) {
                    isLight = false;
                    break;
                }
            
            }
            
            if(!isLight) light[st] = true;
        }
        
        check[st] = false;
        
        count.add(new int[] {st, cnt});
        child[st] = cnt;
        
        return cnt;
        
    }
    

}