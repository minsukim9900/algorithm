import java.io.*;
import java.util.*;

class Solution {
    private static int N, M, K;
    private static boolean isPoss = false;
    private static String answer = "impossible";
    private static int[][] delta = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    private static char[] ch = {'d', 'l', 'r', 'u'};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int distance = Math.abs(r - x) + Math.abs(c - y);
        if(distance > k || (k - distance) % 2 == 1) {
            return answer;
        }
        
        N = n;
        M = m;
        K = k;
        dfs(x, y, r, c, 0, new StringBuilder());
        return answer;
    }
    
    private static void dfs(int r, int c, int er, int ec, int depth, StringBuilder sb) {
        if(depth < K && r == er && c == ec && (K - depth) % 2 == 1) {
            return;
        }
        
        if(isPoss) return;
        
        if(depth == K) {
            if(r == er && c == ec) {
                isPoss = true;
                answer = sb.toString();
            }
            return;
        } else {
            for(int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                
                if(isRange(nr, nc)) {
                    int dis = Math.abs(er - nr) + Math.abs(ec - nc);
                    int remain = K - (depth + 1);
                    if(dis <= remain && (remain - dis) % 2 == 0) {
                        dfs(nr, nc, er, ec, depth + 1, sb.append(ch[i]));
                        break;
                    }
                }
            }
        }
    }
    
    
    private static boolean isRange(int r, int c) {
        return r >= 1 && r <= N && c >= 1 && c <= M;
    }
}
