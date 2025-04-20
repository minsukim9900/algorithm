import java.io.*;
import java.util.*;

class Solution {
    private static List<Integer>[] arr;
    private static int[] in, out;
    private static int max;
    private static boolean[] visited;
    
    public int[] solution(int[][] edges) {
        
        max = 0;
        for(int[] e : edges) {         
            max = Math.max(max, Math.max(e[0], e[1]));
        }
        
        in = new int[max + 1];
        out = new int[max + 1];
        arr = new ArrayList[max + 1];
        
        for(int i = 1; i <= max; i++) {
            arr[i] = new ArrayList<>();
        }
        
        for(int[] e : edges) {
            out[e[0]]++;
            in[e[1]]++;
            
            arr[e[0]].add(e[1]);
        }
        
        int start = 0;
        for(int i = 1; i<=max; i++) {
            if(in[i] == 0 && out[i] >= 2) {
                start = i;
                break;
            }
        }
        
        
        int[] answer = search(start, arr);
        return answer;
    }
    
    private static int[] search(int start, List<Integer>[] arr) {
        int donuts = 0;
        int stick = 0;
        int eight = 0;
        
        for(int v : arr[start]) {
            visited = new boolean[max + 1];
            int[] info = dfs(v);
            
            if(info[0] == info[1]) {
                donuts++;
                continue;
            }
            if(info[0] > info[1] && info[0] - info[1] == 1) {
                stick++;
                continue;
            }
            if(info[1] > info[0] && info[1] - info[0] == 1) {
                eight++;
                continue;
            }
        }
        
        return new int[] {start, donuts, stick, eight};
    }
    
    // 정점 갯수와 간선의 갯수 구하는 메서드
    private static int[] dfs(int v) {
        visited[v] = true;
        int vCnt = 1; // 트리의 정점 갯수
        int eCnt = 0; // 트리의 간선 갯수
        
        for(int next : arr[v]) {
            eCnt++;
            
            if(visited[next]){
                continue;   
            }
            
            visited[next] = true;
            int[] info = dfs(next);
            vCnt += info[0];
            eCnt += info[1];
        }
        
        return new int[] {vCnt, eCnt};
        
    }
}