import java.util.*;

class Solution {
    
    public int solution(int[] cards) {
        int N = cards.length;
        List<Integer> group = new ArrayList<>();
        
        boolean[] visited = new boolean[N + 1];
        
        for(int i = 0; i < N; i++) {
            int count = 0;
            int card = cards[i];
            if(visited[card]) continue;
            
            while(!visited[card]) {
                visited[card] = true;
                card = cards[card - 1];
                count++;
            }
            
            group.add(count);
        }
        
        
        group.sort((a, b) -> Integer.compare(a, b));
        int size = group.size();
        
        int answer = size == 1 ? 0 : group.get(size - 1) * group.get(size - 2);
        return answer;
    }
}