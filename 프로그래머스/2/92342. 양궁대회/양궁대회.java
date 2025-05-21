import java.io.*;
import java.util.*;

class Solution {
    private static int N, max;
    private static int[] infos;
    private static int[] rs = {-1};
    
    public int[] solution(int n, int[] info) {
        N = n;
        infos = info;
        
        dfs(0, 0, new int[11]);
        
        return rs;
    }
    
    private static void dfs(int depth, int n, int[] select) {
        if(n > N) {
            return;
        }
        
        if(depth == 10) {
            select[depth] = N - n;
            int tmp = diff(select);
            if(tmp > 0) {
                if(max < tmp) {
                    max = tmp;
                    rs = select.clone();
                }else if(max == tmp) {
                    for(int i = 10; i >= 0; i--) {
                        if(select[i] == 0 && rs[i] == 0) {
                            continue;
                        }
                        
                        if(select[i] > rs[i]) {
                            rs = select.clone();
                            break;
                        }else {
                            break;
                        }
                    }
                }
            }
            return;
        }
        
        // 해당 점수 과녁을 안쐈을 때
        dfs(depth + 1, n, select);
        
        // 해당 점수 과녁을 1 ~ N개 쐈을 때
        int need = infos[depth] + 1;
        
        if(need + n <= N) {
            select[depth] = need;
            dfs(depth + 1, n + need, select);
            select[depth] = 0;   
        }
        
    }
    
    private static int diff(int[] select) {
        int ryan = 0;
        int pich = 0;
        for(int i = 0; i <= 10; i++) {
            if(select[i] == 0 && infos[i] == 0) {
                continue;
            }
            
            if(select[i] > infos[i]) {
                ryan += (10 - i);
            }else {
                pich += (10 - i);
            }
        }
        
        return ryan - pich;
    }
}