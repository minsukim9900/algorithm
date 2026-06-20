import java.util.*;

class Solution {
    private static int[] diff, prefix;
    private static long[] sum;
    private static final int MAX_TIME = changeTimeToSecond(new String[] {"99", "59", "59"});
    
    public String solution(String play_time, String adv_time, String[] logs) {
        prefix = new int[MAX_TIME + 2];
        diff = new int[MAX_TIME + 2];
        sum = new long[MAX_TIME + 2];
        
        for (String log : logs) {
            String[] timeInfos = log.split("-");
            String startTimeInfo = timeInfos[0];
            String endTimeInfo = timeInfos[1];
            
            int startTime = changeTimeToSecond(startTimeInfo.split(":"));
            int endTime = changeTimeToSecond(endTimeInfo.split(":"));
            
            diff[startTime] += 1;
            diff[endTime] -= 1;
        }
        
        prefix[0] = diff[0];
        for(int i = 1; i < MAX_TIME + 2; i++) {
            prefix[i] += prefix[i - 1] + diff[i];
        }
        
        sum[0] = prefix[0];
        
        for(int i = 1; i < MAX_TIME + 2; i++) {
            sum[i] += sum[i - 1] + prefix[i];
        }
        
        int playTime = changeTimeToSecond(play_time.split(":"));
        int advTime = changeTimeToSecond(adv_time.split(":"));
        long maxTotalTime = -1;
        int answerTime = 0;
        
        for(int start = 0; start < playTime - advTime + 1; start++) {
            int end = start + advTime - 1;
            
            long total = sum[end] - (start == 0 ? 0 : sum[start - 1]);
            
            if(total > maxTotalTime) {
                maxTotalTime = total;
                answerTime = start;
            }
        }
        
        
        
        return chageTimeToFormat(answerTime);
    }
    
    private static String chageTimeToFormat(int time) {
        int hour = time / 3600;
        
        time %= 3600;
        
        int miniute = time / 60;
        
        int second = time % 60;
        
        return String.format("%02d:%02d:%02d", hour, miniute, second);
    }
    
    private static int changeTimeToSecond(String[] timeInfo) {
        int hour = Integer.parseInt(timeInfo[0]);
        int miniute = Integer.parseInt(timeInfo[1]);
        int second = Integer.parseInt(timeInfo[2]);
        
        return hour * 3600 + miniute * 60 + second;
    }
}