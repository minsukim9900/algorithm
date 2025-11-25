import java.util.*;

class Solution {
    private static int N, M;
    private static Set<String> result;
    private static String[] users, ban;
    
    public int solution(String[] user_id, String[] banned_id) {
        N = user_id.length;
        M = banned_id.length;
        users = user_id;
        ban = banned_id;
        result = new HashSet<>();
        dfs(0, new boolean[N]);
        return result.size();
    }
    
    private static void dfs(int depth, boolean[] visited) {
        if(depth == M) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < N; i++) {
                if(visited[i]) sb.append(i);
            }
            result.add(sb.toString());
            return;
        }
        
        for(int i = 0; i < N; i++) {
            if(visited[i]) continue;
            
            if(check(users[i], ban[depth])) {
                visited[i] = true;
                dfs(depth + 1, visited);
                visited[i] = false;
            }
        }
    }
    
    private static boolean check(String str, String comp) {
        if(str.length() != comp.length()) return false;
        
        for(int i = 0; i < str.length(); i++) {
            if(comp.charAt(i) == '*') continue;
            if(str.charAt(i) != comp.charAt(i)) return false;
        }
        return true;
    }
}