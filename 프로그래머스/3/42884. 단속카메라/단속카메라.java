import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        int answer = 0;
        int endPoint = -30_001;
        
        for(int[] curr : routes) {
            if(curr[0] > endPoint) {
                answer++;
                endPoint = curr[1];
            }
        }
        return answer;
    }
}