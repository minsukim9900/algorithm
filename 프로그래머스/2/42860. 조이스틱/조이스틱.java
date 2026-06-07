import java.util.*;

class Solution {
    private static int N;
    private static int[] diff;
    
    public int solution(String name) {
        N = name.length();
        diff = new int[N];
        char[] alphabet = name.toCharArray();
        
        
        int answer = 0;
        for(int i = 0; i < N; i++) {
            char c = alphabet[i];
            diff[i] = Math.min(c - 'A', 'Z' - c + 1);
            answer += diff[i];
        }
        
        int move = N - 1;
        for(int i = 0; i < N; i++) {
            int next = i + 1;
            
            while(next < N && alphabet[next] == 'A') {
                next++;
            }
            
            move = Math.min(move, Math.min(i * 2 + (N - next), i + 2 * (N - next)));
        }
        
        
        return answer + move;
    }
}