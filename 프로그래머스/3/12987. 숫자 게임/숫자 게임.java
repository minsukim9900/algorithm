import java.io.*;
import java.util.*;

class Solution {
    private static boolean[] used;
    private static int N;
    
    public int solution(int[] A, int[] B) {
        N = B.length;
        used = new boolean[N];
        Arrays.sort(A);
        Arrays.sort(B);
        int answer = 0;
        for(int target : A) {
            int idx = binarySearch(target, B);
            if(idx != -1) {
                answer++;
                used[idx] = true;
            }
        }
        return answer;
    }
    
    private static int binarySearch(int target, int[] B) {
        int s = 0;
        int e = N - 1;
        int result = -1;
        
        while(s <= e) {
            int mid = (s + e) / 2;
            
            if(B[mid] > target) {
                if(used[mid]) {
                    s = mid + 1;
                    continue;
                }
                result = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return result;
    }
}