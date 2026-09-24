import java.util.*;

class Solution {
    private static class Info {
        int st;
        String userId;
        
        public Info(int st, String userId) {
            this.st = st;
            this.userId = userId;
        }
        
        public String toString() {
            return st + " " + userId;
        }
    }
    private static Map<String, String> userDatabase;
    
    public String[] solution(String[] record) {
        userDatabase = new HashMap<>();
        
        int n = record.length;
        
        List<Info> infos = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            String[] temp = record[i].split(" ");
            
            String state = temp[0];
            String userId = temp[1];
            
            if ("Enter".equals(state)) {
                String nickname = temp[2];
                infos.add(new Info(0, userId));
                userDatabase.put(userId, nickname);
            } else if ("Leave".equals(state)) {
                infos.add(new Info(1, userId));
            } else {
                String nickname = temp[2];
                userDatabase.put(userId, nickname);
            }
        }
        
        int m = infos.size();
        String[] answer = new String[m];
        
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            
            Info curr = infos.get(i);
            
            int st = curr.st;
            String userId = curr.userId;
            
            sb.append(userDatabase.get(userId)).append("님이 ");
            
            if (st == 0) {
                sb.append("들어왔습니다.");
            } else {
                sb.append("나갔습니다.");
            }
            
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}