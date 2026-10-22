import java.util.*;

class Solution {
    private int[] extended;
    private int[] dist;
    private int answer;
    
    public int solution(int n, int[] weak, int[] dist) {
        int w = weak.length;
        
        this.dist = dist;
        this.extended = new int[w << 1];
        this.answer = dist.length + 1;
        
        Arrays.sort(dist);
        
        for (int i = 0; i < w; i++) {
            extended[i] = weak[i];
            extended[i + w] = weak[i] + n;
        }
        
        for (int start = 0; start < w; start++) {
            dfs(start, start + w, 0, 0);
        }

        return answer > dist.length ? -1 : answer;
    }
    
    private void dfs(int index, int end, int visited, int count) {
        if (index >= end) {
            answer = Math.min(answer, count);
            return;
        }
        
        if (count >= answer || count == dist.length) {
            return;
        }
        
        for (int i = dist.length - 1; i >= 0; i--) {
            if ((visited & (1 << i)) != 0) {
                continue;
            }
            
            int coverage = extended[index] + dist[i];
            int next = index;
            
            while (next < end && extended[next] <= coverage) {
                next++;
            }
            
            dfs(next, end, visited | (1 << i), count + 1);
        }
    }
}