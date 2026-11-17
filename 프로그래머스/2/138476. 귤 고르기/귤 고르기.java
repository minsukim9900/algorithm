import java.util.*;

class Solution {
    
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < tangerine.length; i++) {
            int size = tangerine[i];
            
            int count = map.getOrDefault(size, 0);
            
            map.put(size, count + 1);
        }
        
        int[] buffer = new int[map.size()];
        int idx = 0;
        
        for (int count : map.values()) {
            buffer[idx++] = count;
        }
        
        Arrays.sort(buffer);
        
        int answer = 0;
        
        for (int i = buffer.length - 1; i >= 0; i--) {
            answer++;
            
            k -= buffer[i];
            
            if (k <= 0) {
                return answer;
            }
        }
        
        return -1;
    }
}