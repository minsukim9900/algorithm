import java.util.*;

class Solution {
    private static class Info {
        int diamond;
        int iron;
        int stone;
        
        public Info(int diamond, int iron, int stone) {
            this.diamond = diamond;
            this.iron = iron;
            this.stone = stone;
        }
    }
    
    public int solution(int[] picks, String[] minerals) {
        int pickCount = picks[0] + picks[1] + picks[2];
        
        int answer = 0;
        
        int len = Math.min(minerals.length, pickCount * 5);
        
        List<Info> arr = new ArrayList<>();
        
        for (int i = 0; i < len; i+= 5) {
            int dCount = 0;
            int iCount = 0;
            int sCount = 0;
            
            int start = i;
            int end = Math.min(len, i + 5);
            for(int j = start; j < end; j++) {
                String state = minerals[j];
                
                if (state.equals("diamond")) {
                    dCount += 1;
                    iCount += 5;
                    sCount += 25;
                } else if(state.equals("iron")) {
                    dCount += 1;
                    iCount += 1;
                    sCount += 5;
                } else {
                    dCount += 1;
                    iCount += 1;
                    sCount += 1;
                }
            }
            
            arr.add(new Info(dCount, iCount, sCount));
        }
        
        arr.sort((a, b) -> Integer.compare(b.stone, a.stone));
        
        for(Info info : arr) {
            if (picks[0] > 0) {
                answer += info.diamond;
                picks[0]--;
            } else if (picks[1] > 0) {
                answer += info.iron;
                picks[1]--;
            } else if (picks[2] > 0) {
                answer += info.stone;
                picks[2]--;
            }
        }
        return answer;
    }
}