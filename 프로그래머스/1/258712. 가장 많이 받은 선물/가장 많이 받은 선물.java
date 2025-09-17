import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int N = friends.length;
        int M = gifts.length;
        
        Map<String, Integer> human = new HashMap<>();
        
        for(int i = 0; i < N; i++) {
            human.put(friends[i], i);
        }
        
        int[][] giftCount = new int[N][N];
        int[][] receiveCount = new int[N][N];
        int[] totalGiftCount = new int[N];
        int[] totalReceiveCount = new int[N];
        int[] percent = new int[N];
        int[] nextMonthRecevieCount = new int[N];
        
        for(int i = 0; i < M; i++) {
            String[] info = gifts[i].split(" ");
            
            int from = human.get(info[0]);
            int to = human.get(info[1]);
            
            giftCount[from][to]++;
            receiveCount[to][from]++;
            totalGiftCount[to]++;
            totalReceiveCount[from]++;
        }
        
        for(int i = 0; i < N; i++) {
            percent[i] = totalGiftCount[i] - totalReceiveCount[i];
        }
        
        for(int i = 0; i < N - 1; i++) {
            for(int j = i + 1; j < N; j++) {
                if(giftCount[i][j] == giftCount[j][i]) {
                    if(percent[i] == percent[j]) {
                        continue;
                    }else {
                        if(percent[i] > percent[j]) {
                            nextMonthRecevieCount[j]++;
                        } else {
                            nextMonthRecevieCount[i]++;
                        }
                    }
                } else {
                    if(giftCount[i][j] > giftCount[j][i]) {
                        nextMonthRecevieCount[i]++;
                    }else {
                        nextMonthRecevieCount[j]++;
                    }
                }
            }
        }
        
        
        int answer = 0;
        for(int i = 0; i < N; i++) {
            answer = Math.max(answer, nextMonthRecevieCount[i]);
        }
        
        return answer;
    }
}