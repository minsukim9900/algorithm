import java.util.*;

class Solution {
    private static int[][][][][] scores, prefix;
    
    private static final int MAX_SCORE = 100_001;
    
    public int[] solution(String[] infos, String[] querys) {
        scores = new int[4][3][3][3][MAX_SCORE];
        prefix = new int[4][3][3][3][MAX_SCORE];
        
        // 정보를 어떻게 저장해야 이득일까 0점 이상의 인원은 어떻게 가장 빠르게 알 수 있을까
        for(String info : infos) {
            String[] in = info.split(" ");
            int languageIdx = getLanguageIndex(in[0]);
            int jobIdx = getJobIndex(in[1]);
            int historyIdx = getHistoryIndex(in[2]);
            int foodIdx = getFoodIndex(in[3]);
            int score = Integer.parseInt(in[4]);
            
            for (int mask = 0; mask < 16; mask++) {
                int lang = (mask & 1) == 0 ? languageIdx : 0;
                int job = (mask & 2) == 0 ? jobIdx : 0;
                int history = (mask & 4) == 0 ? historyIdx : 0;
                int food = (mask & 8) == 0 ? foodIdx : 0;
                
                scores[lang][job][history][food][score]++;
            }
        }
        
        for(int lang = 0; lang < 4; lang++) {
            for(int job = 0; job < 3; job++) {
                for(int history = 0; history < 3; history++) {
                    for(int food = 0; food < 3; food++) {
                        for(int score = MAX_SCORE - 2; score >= 0; score--) {
                            scores[lang][job][history][food][score] += 
                                scores[lang][job][history][food][score + 1];
                        }
                    }
                }
            }
        }
        
        int[] answer = new int[querys.length];
        for(int i = 0; i < querys.length; i++) {
            String query = querys[i].replace(" and ", " ");
            String[] q = query.split(" ");
            
            int languageIdx = getLanguageIndex(q[0]);
            int jobIdx = getJobIndex(q[1]);
            int historyIdx = getHistoryIndex(q[2]);
            int foodIdx = getFoodIndex(q[3]);
            int score = Integer.parseInt(q[4]);
            
            answer[i] = scores[languageIdx][jobIdx][historyIdx][foodIdx][score];
        }
        
        return answer;
    }
    
    private static int getLanguageIndex(String language) {
        switch(language) {
            case "cpp": return 1;
            case "java": return 2;
            case "python": return 3;
            default: return 0;
        }
    }
    
    private static int getJobIndex(String job) {
        switch(job) {
            case "backend": return 1;
            case "frontend": return 2;
            default: return 0;
        }
    }
    
    private static int getHistoryIndex(String history) {
        switch(history) {
            case "junior": return 1;
            case "senior": return 2;
            default: return 0;
        }
    }
    
    private static int getFoodIndex(String food) {
        switch(food) {
            case "chicken": return 1;
            case "pizza": return 2;
            default: return 0;
        }
    }
}