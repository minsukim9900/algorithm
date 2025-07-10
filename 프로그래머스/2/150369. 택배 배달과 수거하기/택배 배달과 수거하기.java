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
            
           // System.out.println(index + " " + Arrays.toString(deliveries) + " " + Arrays.toString(pickups));
            isCompleted(cap, index, deliveries);
            isCompleted(cap, index, pickups);
            answer += (index + 1) * 2;
            //System.out.println("현재 이동 거리: " + answer);
        }
        return answer;
    }
    
    private static void isCompleted(int cap, int index, int[] arr) {
        //System.out.println();
        int startIndex = index;
        int box = cap;
        
        while(index >= 0) {
            //System.out.print("박스 갯수: " + box + " ");
            if(box >= arr[index]) {
                box -= arr[index];
                arr[index--] = 0;
            }else {
                arr[index] -= box;
                //System.out.println(Arrays.toString(arr));
                break;
            }
            //System.out.println(Arrays.toString(arr));
        }
    }
}