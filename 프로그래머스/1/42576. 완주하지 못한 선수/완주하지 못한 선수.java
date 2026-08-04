import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        StringBuilder sb = new StringBuilder();
        
        int N = participant.length;
        int M = completion.length;
        
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < M; i++) {
            int count = map.getOrDefault(completion[i], 0);
            map.put(completion[i], count + 1);
        }
        
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(participant[i]) || map.get(participant[i]) == 0) {
                sb.append(participant[i]);
                break;
            }
            
            map.put(participant[i], map.get(participant[i]) - 1);
        }
        
        return sb.toString();
    }
}