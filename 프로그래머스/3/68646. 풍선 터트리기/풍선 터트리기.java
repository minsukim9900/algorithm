import java.io.*;
import java.util.*;

class Solution {
    // 번호가 작은 풍선을 터트리는 행위는 1번만.
    // 나머지는 큰 풍선이
    private static int N;
    private static int[] numsLeft, numsRight;
    public int solution(int[] a) {
        N = a.length;
        numsLeft = new int[N];
        numsRight = new int[N];
        
        
        numsLeft[0] = a[0];
        for(int i = 1; i < N; i++) {
            numsLeft[i] = Math.min(numsLeft[i - 1], a[i]);
        }
        
        numsRight[N - 1] = a[N - 1];
        for(int i = N - 2; i >= 0; i--) {
            numsRight[i] = Math.min(numsRight[i + 1], a[i]);
        }
        
        
        int answer = 0;
        
        for(int i = 0; i < N; i++) {
            if(i == 0 || i == N - 1) {
                answer++;
                continue;
            }
            
            if(numsLeft[i - 1] > a[i] || numsRight[i + 1] > a[i]) {
                answer++;
            }
        }
        
        return answer;
    }
}