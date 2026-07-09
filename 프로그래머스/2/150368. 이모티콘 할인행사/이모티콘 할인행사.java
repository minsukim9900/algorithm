import java.util.*;

class Solution {
    private static int N, M;
    private static int plusCount, salesAmount;
    private static int[][] users;
    private static int[] emoticons;
    
    private static final int[] PERCENTS = {10, 20, 30, 40};
    
    public int[] solution(int[][] userInfos, int[] emoticonInfos) {
        N = userInfos.length;
        M = emoticonInfos.length;
        plusCount = 0;
        salesAmount = 0;
        
        users = userInfos;
        emoticons = emoticonInfos;
        dfs(0, new int[N], new int[N]);
        
        int[] answer = new int[] {plusCount, salesAmount};
        return answer;
    }
    
    private void dfs(int depth, int[] userPlus, int[] userAmount) {
        if(depth == M) {
            int pCount = 0;
            int sAmount = 0;
            
            for(int i = 0; i < N; i++) {
                pCount += userPlus[i];
                sAmount += userAmount[i];
            }
            
            if(plusCount < pCount || (plusCount == pCount && salesAmount < sAmount)) {
                plusCount = pCount;
                salesAmount = sAmount;
            }
            
            return;
        }
        
        int price = emoticons[depth];
        
        for(int i = 0; i < 4; i++) {
            int percent = PERCENTS[i];
            int productPrice = getDiscountPrice(price, percent);
            
            int[] tempPlus = userPlus.clone();
            int[] tempAmount = userAmount.clone();
            
            for(int user = 0; user < N; user++) {
                int refPercent = users[user][0];
                int refAmount = users[user][1];
                
                
                if(refPercent > percent) {
                    continue;
                }
                
                if(userPlus[user] > 0) {
                    continue;
                }
                
                if (productPrice + userAmount[user] >= refAmount) {
                    userPlus[user] += 1;
                    userAmount[user] = 0;
                }else {
                    userAmount[user] += productPrice;
                }
            }
            dfs(depth + 1, userPlus, userAmount);
            
            userPlus = tempPlus;
            userAmount = tempAmount;
        }
    }
    
    private int getDiscountPrice(int price, int percent) {
        return price - (price * percent / 100);
    }
}