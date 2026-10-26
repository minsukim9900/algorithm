import java.util.*;

class Solution {
    private int N, M;
    
    private int[] bitMenu, menu;
    private List<Integer>[][] buffer;
    private Set<Integer> alphabets;
    
    private void init(String[] orders, int[] course) {
        N = orders.length;
        M = course.length;
        
        bitMenu = new int[N];
        
        buffer = new ArrayList[11][21];
        
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 21; j++) {
                buffer[i][j] = new ArrayList<>();
            }
        }
        
        alphabets = new HashSet<>();
        
        for (int i = 0; i < N; i++) {
            char[] order = orders[i].toCharArray();
            
            int num = 0;
            
            for (int j = 0; j < order.length; j++) {
                char alphabet = order[j];
                
                int bit = alphabet - 'A';
                
                alphabets.add(bit);
                num |= (1 << bit);
            }
            
            bitMenu[i] = num;
        }
        
        menu = new int[alphabets.size()];
        
        int idx = 0;
        for (int x : alphabets) {
            menu[idx++] = x;
        }
        
        Arrays.sort(menu);
    }
    
    private int check(int comb) {
        int count = 0;
        
        for (int i = 0; i < bitMenu.length; i++) {
            if ((bitMenu[i] & comb) == comb) {
                count++;
            }
        }
        
        return count;
    }
    
    private void dfs(int sIdx, int depth, int comb, int limit) {
        if (depth == limit) {
            int count = check(comb);
            buffer[limit][count].add(comb);
            return;
        }
        
        for (int i = sIdx; i < menu.length; i++) {
            dfs(i + 1, depth + 1, comb | (1 << menu[i]), limit);
        }
    }
    
    private String changeBitToAlpha(int comb) {
        StringBuilder sb = new StringBuilder();
        
        while (comb != 0) {
            int lowBit = comb & -comb;
            
            int idx = Integer.numberOfTrailingZeros(lowBit);
            
            sb.append((char) ('A' + idx));
            
            comb &= (comb - 1);
        }
        
        return sb.toString();
    }
    
    public String[] solution(String[] orders, int[] course) {
        init(orders, course);
        
        List<String> answer = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
            int limit = course[i];
            
            dfs(0, 0, 0, limit);
            
            for (int j = 20; j >= 2; j--) {
                if (buffer[limit][j].isEmpty()) {
                    continue;
                }
                
                for (int x : buffer[limit][j]) {
                    answer.add(changeBitToAlpha(x));
                }
                
                break;
            }
        }
        

        Collections.sort(answer);
        
        return answer.toArray(new String[0]);
    }
}