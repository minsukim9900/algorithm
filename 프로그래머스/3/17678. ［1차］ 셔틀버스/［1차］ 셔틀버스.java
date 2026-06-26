import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(String time : timetable) {
            pq.add(changeHourToMiniute(time));
        }
        
        int start = changeHourToMiniute("09:00");
        int result = 0;
        for(int i = 0; i < n; i++) {
            int size = Math.min(pq.size(), m);
            int count = 0;
            
            int maxTime = 0;
            for(int j = 0; j < size; j++) {
                if(pq.peek() > start) {
                    break;
                }
                
                maxTime = Math.max(maxTime, pq.poll());
                count++;
            }
            
            result = start;
            if (count == m) {
                result = maxTime - 1;
            }
            
            start += t;
        }
        
        
        return changeMiniuteToHour(result);
    }
    
    private String changeMiniuteToHour(int time) {
        int hour = time / 60;
        int miniute = time % 60;
        
        return String.format("%02d:%02d", hour, miniute);
    }
    
    private int changeHourToMiniute(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int miniute = Integer.parseInt(time.substring(3));
        
        return hour * 60 + miniute;
    }
}