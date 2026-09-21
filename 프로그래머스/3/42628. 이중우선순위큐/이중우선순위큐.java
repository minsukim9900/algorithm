import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> asc = new PriorityQueue<>();
        PriorityQueue<Integer> desc = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < operations.length; i++) {
            String[] operation = operations[i].split(" ");
            
            String oper = operation[0];
            int num = Integer.parseInt(operation[1]);
            
            if ("I".equals(oper)) {
                asc.add(num);
                desc.add(num);
                
                int version = map.getOrDefault(num, 0);
                map.put(num, ++version);
            } else {
                if (num == 1) {
                    while(!desc.isEmpty()) {
                        int max = desc.poll();
                        
                        int version = map.get(max);
                        
                        
                        if (version <= 0) {
                            continue;
                        }
                        
                        map.put(max, version - 1);
                        break;
                    }
                } else {
                    while(!asc.isEmpty()) {
                        int min = asc.poll();
                        
                        int version = map.get(min);
                        
                        
                        if (version <= 0) {
                            continue;
                        }
                        
                        map.put(min, version - 1);
                        break;
                    }
                }
                
            }
            
        }
        
        
        int min = 0;
        int max = 0;
        
        while (!asc.isEmpty()) {
            int num = asc.poll();
            
            if (map.get(num) <= 0) {
                continue;
            }
            
            min = num;
            break;
        }
        
        while (!desc.isEmpty()) {
            int num = desc.poll();
            
            if (map.get(num) <= 0) {
                continue;
            }
            
            max = num;
            break;
        }
        
        return new int[] {max, min};
    }
}