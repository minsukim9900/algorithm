import java.io.*;
import java.util.*;

class Solution {
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        int index = n - 1;
        long answer = 0;
        
        while(index >= 0) {
            if(deliveries[index] == 0 && pickups[index] == 0) {
                index--;
                continue;
            }

            process(cap, index, deliveries);
            process(cap, index, pickups);
            answer += (index + 1) * 2;
        }
        return answer;
    }
    
    private static void process(int cap, int index, int[] arr) {
        int startIndex = index;
        int box = cap;
        
        while(index >= 0) {
            if(box >= arr[index]) {
                box -= arr[index];
                arr[index--] = 0;
            }else {
                arr[index] -= box;
                break;
            }
        }
    }
}