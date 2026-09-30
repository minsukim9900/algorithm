import java.util.*;

class Solution {
    private static int[] parent, size;
    
    private static int findParent(int nodeX) {
        if (nodeX == parent[nodeX]) {
            return nodeX;
        }
        
        return parent[nodeX] = findParent(parent[nodeX]);
    }
    
    private static void union(int rootX, int rootY) {
        if (size[rootX] >= size[rootY]) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        } else {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        }
    }
    public int solution(int n, int[][] costs) {
        parent = new int[n];
        size = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));
        
        int pick = 0;
        int answer = 0;
        
        for (int i = 0; i < costs.length; i++) {
            int[] curr = costs[i];
            
            int nodeX = curr[0];
            int nodeY = curr[1];
            int weight = curr[2];
            
            int rootX = findParent(nodeX);
            int rootY = findParent(nodeY);
            
            if (rootX != rootY) {
                pick++;
                union(rootX, rootY);
                answer += weight;
                
                if (pick == n - 1) {
                    break;
                }
            }
        }
        
        return answer;
    }
}