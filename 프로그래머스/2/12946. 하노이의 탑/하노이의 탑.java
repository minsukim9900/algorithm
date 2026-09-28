import java.util.*;

class Solution {
    
    List<int[]> ans;
    
    public int[][] solution(int n) {
        ans = new ArrayList<>();
        hanoi(n, 1, 2, 3);
        return ans.toArray(int[][]::new);
    }
    
    void hanoi(int n, int from, int by, int to) {
        if (n == 1) {
            //System.out.println(from + "->" + to);
            ans.add(new int[] {from, to});
            return;
        }
        
        hanoi(n - 1, from, to, by);
        //System.out.println(from + "->" + to);
        ans.add(new int[] {from, to});
        hanoi(n - 1, by, from, to);
    }
}