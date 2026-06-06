import java.util.*;

class Solution {
    private static int N;
    private static List<Integer>[] adj;
    
    public int solution(int[] cards) {
        N = cards.length;
        
        adj = new ArrayList[N + 1];
        for(int i = 1; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int i = 1; i < N + 1; i++) {
            adj[i].add(cards[i - 1]);
        }
        
        List<Integer> counts = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];
        for(int i = 1; i < N + 1; i++) {
            if(visited[i]) continue;
            
            counts.add(bfs(i, visited));
        }
        
        counts.sort((a, b) -> Integer.compare(b, a));
        
        int answer = 0;
        
        if(counts.size() > 1) {
            answer = counts.get(0) * counts.get(1);
        }
        return answer;
    }
    
    private int bfs(int start, boolean[] visited) {
        visited[start] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        
        int result = 0;
        while(!q.isEmpty()) {
            int curr = q.poll();
            result++;
            
            for(int next : adj[curr]) {
                if(visited[next]) continue;
                visited[next] = true;
                q.add(next);
            }
        }
        
        return result;
    }
}