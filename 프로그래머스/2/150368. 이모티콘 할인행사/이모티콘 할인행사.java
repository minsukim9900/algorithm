import java.io.*;
import java.util.*;

class Solution {
    private static int[][] prices, usersInfo;
    private static int min;
    private static int[] answer = new int[2];
    
    public int[] solution(int[][] users, int[] emoticons) {
        usersInfo = users;
        prices = calEmoticonsPrices(emoticons);
        min = 40;
        
        for(int[] user : users) {
            min = Math.min(user[0], min);
        }
        
        if(min % 10 > 0) {
            min = (min / 10) * 10 + 10;
        }
        
        perm(0, new int[emoticons.length]);
        return answer;
    }
    private static int[] calPurchasePrice(int[] infos) {
        int price = 0;
        int subScribe = 0;
        
        for(int i = 0; i < usersInfo.length; i++) {
            int sum = 0;
            
            for(int j = 0; j < infos.length; j++) {
                if(infos[j] < usersInfo[i][0]) continue;
                
                sum += prices[j][infos[j] / 10 - 1];
            }
            
            if(sum >= usersInfo[i][1]) {
                subScribe++;
                sum = 0;
            }
            price += sum;
        }
        return new int[] {subScribe, price};
    }
    
    private static void perm(int depth, int[] infos) {
        if(depth == prices.length) {
            int[] curr = calPurchasePrice(infos);
            
            if(curr[0] > answer[0]) {
                answer = curr;
            } else if(curr[0] == answer[0] && curr[1] > answer[1]) {
                answer = curr;
            }            
        } else {
            for(int i = min; i <= 40; i += 10) {
                infos[depth] = i;
                perm(depth + 1, infos);
            }
        }
    }
    
    private static int[][] calEmoticonsPrices(int[] emoticons) {
        int[][] result = new int[emoticons.length][4];
        
        for(int i = 0; i < emoticons.length; i++) {
            int curr = emoticons[i];
            
            for(int j = 10; j <= 40; j += 10) {
                result[i][j / 10 - 1] = curr - ((curr / 100) * j);
            }
        }
        return result;
    }
    
    
}