import java.io.*;
import java.util.*;

class Solution {
    private static boolean[] visited;
    
    public int solution(int[] cards) {
        visited = new boolean[cards.length + 1];
        List<Integer> arr = new ArrayList<>();
        
        for(int i = 0; i < cards.length; i++) {
            if(visited[i]) continue;
            int cnt = dfs(i, cards);
            arr.add(cnt);
        }
        
        Collections.sort(arr);
        
        int cnt = 0;
        int answer = 1;
        for(int i = arr.size() - 1; i >= 0; i--) {
            answer *= arr.get(i);
            cnt++;
            
            if(cnt == 2) {
                break;
            }
        }      
        return cnt != 2 ? 0 : answer;
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