import java.io.*;
import java.util.*;

class Solution {
    private static final int MAX = 10_000;
    private static int[] timeRecord;
    private static boolean[] state;
    private static int[] charge;

    public int[] solution(int[] fees, String[] records) {
        timeRecord = new int[MAX];
        state = new boolean[MAX];
        charge = new int[MAX];
        Set<Integer> tower = new HashSet<>();
        
        int preTime = 0;
        for(String str : records) {
            String[] temp = str.split(" ");
            int time = changeToMinute(temp[0]);
            int carNum = Integer.parseInt(temp[1]);
            
            int gap = time - preTime;
            
            for(Integer index : tower) {
                if(!state[index]) continue;
                
                timeRecord[index] += gap;
            }
            
            tower.add(carNum);
            
            if(temp[2].equals("IN")) {
                state[carNum] = true;
            } else {
                state[carNum] = false;
            }
            preTime = time;
        }
        int maxTime = changeToMinute("23:59");
        int gap = maxTime - preTime;
        
        for(Integer index : tower) {
            if(state[index]) {
                timeRecord[index] += gap;
            }
            
            charge[index] = calFee(timeRecord[index], fees);
        }
        
        int[] answer = new int[tower.size()];
        int idx = 0;
        for(int i = 0; i < MAX; i++) {
            if(tower.contains(i)) {
                answer[idx++] = charge[i];
            }
        }
        
        return answer;
        
    }
    
    private static int calFee(int time, int[] fees) {
        int basicTime = fees[0];
        int basicMoney = fees[1];
        int standardTime = fees[2];
        int extraFee = fees[3];
        
        int extraPlayTime = time - basicTime;
        if(extraPlayTime <= 0) {
            extraPlayTime = 0;
        }
        
        int result = basicMoney + (((extraPlayTime) / standardTime) * extraFee); 
        
        if(extraPlayTime % standardTime > 0) {
            result += extraFee;
        }
        
        return result;
    }
    
    private static int changeToMinute(String time) {
        int h = (time.charAt(0) - '0') * 10 + time.charAt(1) - '0';
        int m = (time.charAt(3) - '0') * 10 + time.charAt(4) - '0';
        return h * 60 + m;
    }
}