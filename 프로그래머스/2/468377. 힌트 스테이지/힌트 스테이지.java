import java.util.*;

class Solution {
    private static int N, M, answer;
    
    public int solution(int[][] cost, int[][] hint) {
        N = cost.length;
        M = hint.length;
        answer = Integer.MAX_VALUE;
        
        dfs(0, cost, hint, new int[N + 1], 0);
        return answer;
    }
    
    private void dfs(int depth, int[][] cost, int[][] hint, int[] countNum, int sum) {
        if(depth == M) {
            sum += cost[depth][Math.min(M, countNum[depth + 1])];
            answer = Math.min(answer, sum);
            return;
        }
        
        int useHintCount = Math.min(countNum[depth + 1], N - 1);
        
        // 힌트권을 사지 않았을 때
        dfs(depth + 1, cost, hint, countNum, sum + cost[depth][useHintCount]);
        
        // 힌트권을 샀을 때
        for(int i = 1; i < hint[depth].length; i++) {
            countNum[hint[depth][i]] += 1;
        }
        
        dfs(depth + 1, cost, hint, countNum, sum + cost[depth][useHintCount] + hint[depth][0]);
        
        for(int i = 1; i < hint[depth].length; i++) {
            countNum[hint[depth][i]] -= 1;
        }
    }
}