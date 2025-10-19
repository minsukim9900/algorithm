import java.io.*;
import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        char[] nums = number.toCharArray();
        
        int s = 0;
        int e = k;
        while(e < number.length()) {
            int maxNumber = -1;
            int maxNumberIndex = 0;
            
            for(int i = s; i <= e; i++) {
                if(maxNumber < nums[i] - '0') {
                    maxNumber = nums[i] - '0';
                    maxNumberIndex = i;
                }
            }
            sb.append(maxNumber);
            s = maxNumberIndex + 1;
            e++;
        }
        return sb.toString().equals("") ? "0" : sb.toString();
    }
}