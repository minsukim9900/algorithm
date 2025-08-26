import java.io.*;
import java.util.*;

class Solution {
    private static boolean[] visited;
    
    public int solution(int[] cards) {
        visited = new boolean[cards.length + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        
        for(int i = 0; i < cards.length; i++) {
            if(visited[i]) continue;
            int cnt = dfs(i, cards);
            pq.add(cnt);
        }
        
        int count = 0;
        int answer = 1;
        
        while(!pq.isEmpty()) {
            int curr = pq.poll();
            count++;
            answer *= curr;
            
            if(count == 2) {
                break;
            }
        }
        
        return count != 2 ? 0 : answer;
    }
    
    private static int dfs(int node, int[] cards) {
        visited[node] = true;
        int cnt = 1;
        if(!visited[cards[node] - 1]) {
            cnt += dfs(cards[node] - 1, cards);
        }
        
        return cnt;
    }
}