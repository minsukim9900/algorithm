import java.io.*;
import java.util.*;

class Solution {
    private static final int MAX = 100_001;
    private static class Node {
        int idx, order;
        
        public Node(int idx, int order) {
            this.idx = idx;
            this.order = order;
        }
    }
    
    public int solution(int cacheSize, String[] cities) {
        int[] records = new int[MAX];
        boolean[] state = new boolean[MAX];
        
        Map<String, Integer> cityDB = new HashMap<>();
        TreeSet<Node> cache = new TreeSet<>((a , b) -> a.order - b.order);
        
        int cnt = 0;
        int order = 0;
        int answer = 0;
        int size = 0;
        
        for(String city : cities) {
            String temp = city.toUpperCase();
            int index = cnt;
            order++;
            
            if(cityDB.containsKey(temp)) {
                index = cityDB.get(temp);
            } else {
                cityDB.put(temp, cnt++);
            }
            records[index] = order;
            Node node = new Node(index, order);
            
            // 이미 캐시에 내가 존재
            if(state[index]) {
                answer++;
                cache.add(node);
            } else { // 캐시에 내가 없음
                answer += 5;
                state[index] = true;
                cache.add(node);
                
                if(size < cacheSize) {
                    size++;
                } else {
                    while(!cache.isEmpty()) {
                        Node curr = cache.pollFirst();
                        
                        if(records[curr.idx] == curr.order) {
                            state[curr.idx] = false;
                            break;
                        }
                    }
                }
            }
        }
        return answer;
    }
}