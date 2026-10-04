import java.util.*;

class Solution {
    
    private String getBits(int num) {
        StringBuilder sb = new StringBuilder();
        
        while (num != 0) {
            int remain = num % 2;
            sb.append(remain);
            
            num /= 2;
        }
        
        return sb.reverse().toString();
    }
    
    public int[] solution(String s) {
        int changeCount = 0;
        int zeroCount = 0;
        
        while (!s.equals("1")) {
            char[] nums = s.toCharArray();
            
            int len = nums.length;
            int oneCount = 0;
            
            for (int i = 0; i < len; i++) {
                if (nums[i] == '0') {
                    continue;
                }
                
                oneCount++;
            }
            
            zeroCount += (len - oneCount);
            changeCount++;
            s = getBits(oneCount);
        }
        
        
        int[] answer = {changeCount, zeroCount};
        return answer;
    }
}