import java.util.*;

class Solution {
    private int N;
    private int[] stones;
    private int[] maxTree;
    
    private int init(int node, int nodeLeft, int nodeRight) {
        if (nodeLeft == nodeRight) {
            return maxTree[node] = stones[nodeLeft];
        }
        
        int mid = (nodeLeft + nodeRight) >> 1;
        int nextNode = node << 1;
        
        int leftValue = init(nextNode, nodeLeft, mid);
        int rightValue = init(nextNode + 1, mid + 1, nodeRight);
        
        return maxTree[node] = Math.max(leftValue, rightValue);
    }
    
    private int queryMax(int node, int nodeLeft, int nodeRight, int queryLeft, int queryRight) {
        if (queryRight < nodeLeft || nodeRight < queryLeft) {
            return 0;
        }
        
        if (queryLeft <= nodeLeft && nodeRight <= queryRight) {
            return maxTree[node];
        }
        
        int min = (nodeLeft + nodeRight) >> 1;
        int nextNode = node << 1;
        
        int leftMax = queryMax(nextNode, nodeLeft, min, queryLeft, queryRight);
        int rightMax = queryMax(nextNode + 1, min + 1, nodeRight, queryLeft, queryRight);
        
        return Math.max(leftMax, rightMax);
    }
    
    public int solution(int[] stone, int k) {
        N = stone.length;
        maxTree = new int[N * 4];
        stones = stone;
        
        init(1, 0, N - 1);
        
        int answer = Integer.MAX_VALUE;
        
        for (int queryLeft = 0; queryLeft + k - 1 < N; queryLeft++) {
            int queryRight = queryLeft + k - 1;
            
            int result = queryMax(1, 0, N - 1, queryLeft, queryRight);
            
            answer = Math.min(answer, result);
        }
        
        
        return answer;
    }
    
}