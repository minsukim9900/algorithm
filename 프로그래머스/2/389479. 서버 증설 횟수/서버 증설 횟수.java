import java.util.*;

class Solution {
    private static final int MAX = 50;
    
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        int[] serverPrefix = new int[MAX];
        int currServer = 1;
        
        for(int i = 0; i < players.length; i++) {
            currServer += serverPrefix[i];
            int player = players[i];
            
            int temp = player / m;
            int addServer = Math.max(0, (player / m - currServer + 1));
            
            currServer += addServer;
            answer += addServer;
            serverPrefix[i] += addServer;
            serverPrefix[i + k] -= addServer;
        }
        
        return answer;
    }
}