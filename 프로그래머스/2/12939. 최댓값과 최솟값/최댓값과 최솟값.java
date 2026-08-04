import java.util.*;

class Solution {
    public String solution(String s) {
        String[] temp = s.split(" ");
        
        int N = temp.length;
        int[] nums = new int[N];
        
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(temp[i]);
            
            nums[i] = num;
        }
        
        Arrays.sort(nums);
        
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0] + " " + nums[N - 1]);
        return sb.toString();
    }
}