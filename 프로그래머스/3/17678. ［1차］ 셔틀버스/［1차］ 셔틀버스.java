import java.io.*;
import java.util.*;

class Solution {
    private static int[] bus;
    private static List<int[]> crew;
    private static int M;
    
    public String solution(int n, int t, int m, String[] timetable) {
        M = m;
        crew = new ArrayList<>();
        for(int i = 0; i < timetable.length; i++) {
            crew.add(new int[] {i, getTime(timetable[i].split(":"))});
        }
        
        crew.sort((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        
        bus = new int[n];
        bus[0] = 540;
        
        for(int i = 1; i < n; i++) {
            bus[i] = bus[i - 1] + t;
        }
        
        return changeTime(binarySearch());
    }
    
    private static int binarySearch() {
        int s = -1;
        int e = 1440;
        int answer = 0;
        
        while(s <= e) {
            int mid = (s + e) / 2;
            
            if(check(mid)) {
                answer = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return answer;
    }
    
    private static boolean check(int t) {
        int idx = 0;
        for(int time : bus) {
            int pick = 0;
            
            while(idx < crew.size()) {
                int[] curr = crew.get(idx);

                if(curr[1] > time) break;
                if(curr[1] > t) break;

                idx++;
                pick++;

                if(pick == M) break;
            }
            
            if(pick < M && t <= time) {
                return true;
            }
        }
        return false;
    }
    
    private static String changeTime(int t) {
        StringBuilder sb = new StringBuilder();
        int tmp = t / 60;
        int temp = t % 60;
        if(tmp < 10 && temp >= 10) {
            sb.append("0").append(t / 60).append(":").append(temp);
        }
        else if(tmp >= 10 && temp < 10) {
            sb.append(t / 60).append(":").append("0").append(temp);
        } else if(tmp < 10 && temp < 10) {
            sb.append("0").append(t / 60).append(":").append("0").append(temp);
        }
        else {
            sb.append(t / 60).append(":").append(t % 60);
        }
        return sb.toString();
    }
    
    private static int getTime(String[] info) {
        int h = Integer.parseInt(info[0]);
        int m = Integer.parseInt(info[1]);
        
        return h * 60 + m;
    }
}