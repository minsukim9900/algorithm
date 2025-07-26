import java.io.*;
import java.util.*;

class Solution {
    private static char[] person = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private static boolean isCheck = false;
    private static int count;
    
    private static class Info {
        char start;
        char end;
        int state;
        int value;
        
        public Info(char start, char end, int state, int value) {
            this.start = start;
            this.end = end;
            this.state = state;
            this.value = value;
        }
        
        public String toString() {
            return start + " " + end +" " + state + " " + value;
        }
    }
    
    private static Info[] infos;
    
    public int solution(int n, String[] data) {
        infos = new Info[data.length];
        
        for(int i = 0; i < data.length; i++) {
            char tmp = data[i].charAt(3);
            int num = 0;
            if(tmp == '>') {
                num = 1;
            }else if(tmp == '<') {
                num = -1;
            }
            infos[i] = new Info(data[i].charAt(0), data[i].charAt(2), num, data[i].charAt(4) - '0');
        }
        
        count = 0;
        perm(0, new boolean[person.length], new HashMap<Character, Integer>());
        int answer = count;
        return answer;
    }
    
    private static void perm(int idx, boolean[] visited, Map<Character, Integer> map) {
        if(idx == person.length && isPoss(map)) {
            count++;
        } else {
            for(int i = 0; i < person.length; i++) {
                if(visited[i]) continue;
                
                visited[i] = true;
                map.put(person[idx], i);
                perm(idx + 1, visited, map);
                visited[i] = false;
            }
        }
    }
    
    private static boolean isPoss(Map<Character, Integer> map) {
        for(Info info : infos) {
            int s = map.get(info.start);
            int e = map.get(info.end);
            
            int state = info.state;
            int range = info.value;
            
            if(!check(s, e, state, range)) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean check(int s, int e, int state, int range) {
        int tmp = Math.abs(s - e) - 1;
        
        if(state == 0) {
            return tmp == range;
        }else if(state == 1) {
            return tmp > range;
        }else {
            return tmp < range;
        }
    }
}