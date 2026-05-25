import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int currTime = getTime(pos);
        int endTime = getTime(video_len);
        
        int opStart = getTime(op_start);
        int opEnd = getTime(op_end);
        
        
        for(String command : commands) {
            if(opStart <= currTime && currTime <= opEnd) {
                currTime = opEnd;
            }
            
            currTime = getNext(command, currTime, endTime);
        }
        
        if(opStart <= currTime && currTime <= opEnd) {
                currTime = opEnd;
        }
        
        int minute = currTime / 60;
        int second = currTime % 60;
        
        StringBuilder sb = new StringBuilder();
        
        if(minute < 10) {
            sb.append(0);
        }
        sb.append(minute).append(":");
        
        if(second < 10) {
            sb.append(0);
        }
        sb.append(second);
        
        String answer = sb.toString();
        return answer;
    }
    
    private static int getTime(String pos) {
        return Integer.parseInt(pos.substring(0, 2)) * 60 + Integer.parseInt(pos.substring(3));
    }
    
    private static int getNext(String command, int currTime, int endTime) {
            switch(command) {
                case "prev":
                    return Math.max(currTime - 10, 0);
                case "next":
                    return Math.min(currTime + 10, endTime);
            }
            
            return -1;
    }
}