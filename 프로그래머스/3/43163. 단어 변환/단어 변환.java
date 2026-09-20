import java.util.*;

class Solution {
    private static int N, M;
    private static List<Integer>[] adj;
    private static String[] wordsArr;
    
    public int solution(String begin, String target, String[] words) {
        N = words.length + 2;
        M = begin.length();
        
        wordsArr = new String[N];
        
        for (int i = 0; i < words.length; i++) {
            wordsArr[i] = words[i];
        }
        
        wordsArr[N - 2] = begin;
        wordsArr[N - 1] = target;
        
        adj = new ArrayList[N];
        
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        
        boolean flag = false;
        
        for (int i = 0; i < words.length; i++) {
            
            if (words[i].equals(target)) {
                flag = true;
            }
            
            if (isChange(begin, words[i])) {
                adj[i].add(N - 2);
                adj[N - 2].add(i);
            }
            
            if (isChange(target, words[i])) {
                adj[i].add(N - 1);
                adj[N - 1].add(i);
            }
            
            for (int j = i + 1; j < words.length; j++) {
                if (isChange(words[i], words[j])) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        
        if (!flag) {
            return 0;
        }
        
        int answer = bfs(target);
        return answer;
    }
    
    private static int bfs(String target) {
        boolean[] visited = new boolean[N];
        visited[N - 2] = true;
        
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {N - 2, 0});
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int idx = curr[0];
            int dist = curr[1];
            
            if (wordsArr[idx].equals(target)) {
                return dist;
            }
            
            for (int next : adj[idx]) {
                if (visited[next]) {
                    continue;
                }
                
                visited[next] = true;
                q.add(new int[] {next, dist + 1});
            }
        }
        
        return 0;
    }
    
    private static boolean isChange(String wordA, String wordB) {
        int count = 0;
        
        for (int i = 0; i < M; i++) {
            if (wordA.charAt(i) != wordB.charAt(i)) {
                count++;
            }
            
            if (count > 1) {
                return false;
            }
        }
        
        return count == 1;
    }
}