import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int row = relation.length;
        int col = relation[0].length;
        
        List<Integer> candidateKey = new ArrayList<>();
        
        for(int mask = 1; mask < (1 << col); mask++) {
            boolean isMinimality = true;
            
            for (int key : candidateKey) {
                if ((key & mask) == key) {
                    isMinimality = false;
                    break;
                }
            }
            
            if (!isMinimality) {
                continue;
            }
            
            Set<String> set = new HashSet<>();
            
            for (int r = 0; r < row; r++) {
                StringBuilder sb = new StringBuilder();
                
                for(int c = 0; c < col; c++) {
                    if ((mask & (1 << c)) != 0) {
                        sb.append(relation[r][c]).append("|");
                    }
                }
                
                set.add(sb.toString());
            }
            
            if (set.size() == row) {
                candidateKey.add(mask);
            }
        }
        
        return candidateKey.size();
    }
}