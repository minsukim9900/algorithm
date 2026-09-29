import java.util.*;

class Solution {
    private static int[] nums = {4, 1, 2};
    public String solution(int n) {
        List<Integer> bits = new ArrayList<>();
        
        int num = n;
        
        while (num != 0) {
            int bit = num % 3;
            bits.add(bit);
            num /= 3;
        }
        
        
        for (int i = 0; i < bits.size() - 1; i++) {
            int bit = bits.get(i);
            
            if (bit <= 0) {
                bits.set(i, nums[(bit + 3) % 3]);
                int nextBit = bits.get(i + 1);
                
                bits.set(i + 1, nextBit - 1);
            }
        }
        
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = bits.size() - 1; i >= 0; i--) {
            int temp = bits.get(i);
            
            if (temp <= 0) {
                continue;
            }
            
            sb.append(temp);
        }
        
        return sb.toString();
    }
}