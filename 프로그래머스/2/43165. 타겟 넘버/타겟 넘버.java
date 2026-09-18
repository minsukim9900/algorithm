import java.util.*;

class Solution {
    private static int N, M, answer;
    private static int[] nums;
    
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        M = target;
        answer = 0;
        nums = numbers;
        dfs(0, 0);
        return answer;
    }
    
    private static void dfs(int depth, int sum) {
        if (depth == N) {
            if (sum == M) {
                answer++;
            }
            
            return;
        }
        
        dfs(depth + 1, sum + nums[depth]);
        dfs(depth + 1, sum - nums[depth]); 
    }
}