import java.util.*;

class Solution {
    
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        
        int start = getSecond(h1, m1, s1);
        int end = getSecond(h2, m2, s2);
        
        double hour = hourAngle(start);
        double minute = minuteAngle(start);
        double second = secondAngle(start);
        
        if(hour == second || minute == second) {
            answer++;
        }
        
        
        for(int time = start; time < end; time++) {
            double preHour = hourAngle(time);
            double preMin = minuteAngle(time);
            double preSec = secondAngle(time);
            
            double currHour = hourAngle(time + 1);
            double currMin = minuteAngle(time + 1);
            double currSec = secondAngle(time + 1);
            
            if(isCross(preHour, preSec, currHour, currSec)) {
                answer++;
            }
            
            if(isCross(preMin, preSec, currMin, currSec)) {
                answer++;
            }
            
            if(currHour == currSec && currMin == currSec) {
                answer--;
            }
        }
        
        return answer;
    }
    
    
    private static int getSecond(int h, int m, int s) {
        return 3600 * h + 60 * m + s;
    }
    
    private static double secondAngle(int time) {
        return (time % 60) * 6.0;
    }
    
    private static double minuteAngle(int time) {
        return ((time / 60) % 60) * 6.0 + (time % 60) * 0.1;
    }
    
    private static double hourAngle(int time) {
        return (time % (12 * 3600)) / 120.0;
    }
    
    private static boolean isCross(double preTarget, double preSec,
                               double currTarget, double currSec) {
        if (currSec == 0) {
            currSec = 360;
        }

        if (currTarget == 0) {
            currTarget = 360;
        }

        return preTarget > preSec && currTarget <= currSec;
    }   
}