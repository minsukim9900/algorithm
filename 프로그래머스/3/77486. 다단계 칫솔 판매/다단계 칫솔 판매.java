import java.io.*;
import java.util.*;

class Solution {
    private static List<Integer>[] adj;
    private static Map<String, Integer> map;
    private static int N;
    private static int[] count;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        N = enroll.length + 1;
        adj = new ArrayList[N + 1];
        
        map = new HashMap<>();
        
        for(int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        
        int idx = 1;
        
        for(String name : enroll) {
            map.put(name, idx++);
        }
        
        for(int i = 1; i < N; i++) {
            if(referral[i - 1].equals("-")) {
                adj[i].add(0);
            }else {
                int p = map.get(referral[i - 1]);
                adj[i].add(p);
            }
        }
        
        count = new int[N];
        for(int i = 0; i < seller.length; i++) {
            int index = map.get(seller[i]);
            dfs(index, amount[i] * 100);
        }
        
        int[] answer = new int[N - 1];
        for(int i = 1; i < N; i++) {
            answer[i - 1] = count[i];
        }
        return answer;
    }
    
    private static void dfs(int node, int amount) {
        count[node] += amount;
        
        for(int next : adj[node]) {
            if(amount * 10 / 100 > 0) {
                dfs(next, amount * 10 / 100);
                count[node] -= (amount * 10 / 100);
            }
        }
    }
}