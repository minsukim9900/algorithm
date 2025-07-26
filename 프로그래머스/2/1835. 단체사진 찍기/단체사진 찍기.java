import java.io.*;
import java.util.*;

class Solution {
    private static char[] c = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private static Map<Character, Integer> map = Map.of('A', 0, 'C', 1, 'F', 2, 'J', 3, 'M', 4
                                                           , 'N', 5, 'R', 6, 'T', 7);
    private static int count;
    
    private static class Info {
        int start;
        int end;
        int state;
        int value;
        
        public Info(char s, char e, int state, int value) {
            this.start = map.get(s);
            this.end = map.get(e);
            this.state = state;
            this.value = value;
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
        perm(0, new int[8], new boolean[8]);
        
        int answer = count;
        return answer;
    }
    
    private static void perm(int idx, int[] pos, boolean[] visited) {
        if(idx == 8 && isPoss(pos, visited)) {
            count++;
        } else {
            for(int i = 0; i < 8; i++) {
                if(visited[i]) continue;
                
                pos[i] = idx;
                visited[i] = true;
                perm(idx + 1, pos, visited);
                visited[i] = false;
            }
        }
    }
    
    private static boolean isPoss(int[] pos, boolean[] visited) {
        for(Info info : infos) {
            int s = pos[info.start];
            int e = pos[info.end];
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