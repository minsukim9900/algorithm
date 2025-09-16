import java.io.*;
import java.util.*;

class Solution {
    private static class Info {
        String subject;
        int startTime;
        int time;
        int runTime;
        
        public Info(String subject, int startTime, int time) {
            this.subject = subject;
            this.startTime = startTime;
            this.time = time;
            this.runTime = 0;
        }
    }    
    public String[] solution(String[][] plans) {
        int N = plans.length;
        String[] answer = new String[N];
        
        PriorityQueue<Info> pq = new PriorityQueue<>((a, b) -> a.startTime - b.startTime);
        for(String[] plan : plans) {
            String subject = plan[0];
            String[] temp = plan[1].split(":");
            int startTime = Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
            int time = Integer.parseInt(plan[2]);
            Info info = new Info(subject, startTime, time);
            pq.add(info);
        }
        
        int t = 0;
        int idx = 0;
        Stack<Info> stack = new Stack<>();
        while(idx != N) {
            if(!pq.isEmpty() && pq.peek().startTime == t) {
                Info info = pq.poll();
                stack.add(info);
            }
            
            if(!stack.isEmpty()) {
                Info info = stack.peek();
                info.runTime++;
                
                if(info.runTime == info.time) {
                    stack.pop();
                    answer[idx++] = info.subject;
                }
            }
            
            t++;
        }
        
        return answer;
    }
}