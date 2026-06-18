import java.util.*;

class Solution {
    private static int N, M;
    private static int[][] delta = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        Node result = bfs(x, y, r, c, k);
        
        return result == null ? "impossible" : result.info;
    }
    
    private Node bfs(int sr, int sc, int er, int ec, int k) {
        boolean[][][] visited = new boolean[k + 1][N + 1][M + 1];
        
        visited[0][sr][sc] = true;
        
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(sr, sc, 0, ""));
        
        while(!q.isEmpty()) {
            Node curr = q.poll();
            
            int r = curr.r;
            int c = curr.c;
            int d = curr.d;
            String info = curr.info;
            
            if(r == er && c == ec && d == k) {
                return curr;
            }
            
            if (d == k) {
                continue;
            }
            
            for(int dir = 0; dir < 4; dir++) {
                int nr = r + delta[dir][0];
                int nc = c + delta[dir][1];
                
                if(isRange(nr, nc) && !visited[d + 1][nr][nc]) {
                    visited[d + 1][nr][nc] = true;
                    Node next = new Node(nr, nc, d + 1, info + getDir(dir));
                    q.add(next);
                }
            }
        }
              
        return null;
    }
              
    private char getDir(int dir) {
        if(dir == 0) {
            return 'd';
        }
        
        if(dir == 1) {
            return 'l';
        }
        
        if(dir == 2) {
            return 'r';
        }
        
        return 'u';
    }
    
    private class Node {
        int r, c, d;
        String info;
        
        public Node(int r, int c, int d, String info) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.info = info;
        }
        
        public String toString() {
            return "r:" + r + ", c: " + c + ", d: " + d + ", info: " + info;
        }
    }
    
    
    private boolean isRange(int r, int c) {
        return r > 0 && r <= N && c > 0 && c <= M;
    }
}