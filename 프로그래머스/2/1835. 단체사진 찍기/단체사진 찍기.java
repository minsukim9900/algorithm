import java.util.*;

class Solution {
    private static char[] alphabet;
    private static int[] alphabetIndex;
    private static int answer;
    
    public int solution(int n, String[] data) {
        alphabetIndex = new int['z' - 'a' + 1];
        alphabet = new char[] {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        Arrays.fill(alphabetIndex, -1);
        answer = 0;
        
        dfs(0, data, new boolean['z' - 'a' + 1]);
        
        return answer;
    }
    
    private void dfs(int depth, String[] data, boolean[] visited) {
        if (depth == 8) {
            answer++;
            return;
        }
        
        for(int i = 0; i < 8; i++) {
            int idx = alphabet[i] - 'A';
            
            if (visited[idx]) continue;
            
            alphabetIndex[idx] = depth;
            
            if(check(data)) {
                visited[idx] = true;
                dfs(depth + 1, data, visited);
                visited[idx] = false;
            }
            
            alphabetIndex[idx] = -1;
        }
    }
    
    private boolean check(String[] data) {
        for(String d : data) {
            char[] c = d.toCharArray();
            int x = c[0] - 'A';
            int y = c[2] - 'A';
            char condition = c[3];
            int range = c[4] - '0';
            
            if(alphabetIndex[x] == -1 || alphabetIndex[y] == -1) continue;
            
            if(!isPossible(x, y, condition, range)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean isPossible(int x, int y, char condition, int range) {
        int distance = Math.abs(alphabetIndex[x] - alphabetIndex[y]) - 1;
        
        if (condition == '>') {
            return distance > range;
        } else if (condition == '<') {
            return distance < range;
        } else {
            return distance == range;
        }
    }
}