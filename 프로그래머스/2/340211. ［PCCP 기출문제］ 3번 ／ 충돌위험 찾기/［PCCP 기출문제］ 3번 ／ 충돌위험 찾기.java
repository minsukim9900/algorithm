import java.util.*;

class Solution {
    private static int N;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int[][][] board;
    
    public int solution(int[][] points, int[][] routes) {
        N = 120;
        
        board = new int[N * N][N][N];
        
        for(int[] route : routes) {
            int sIdx = route[0] - 1;
            int sr = points[sIdx][0] - 1;
            int sc = points[sIdx][1] - 1;
            
            Node preNode = null;
            int dist = 0;
            
            for(int i = 1; i < route.length; i++) {
                int eIdx = route[i] - 1;
                int er = points[eIdx][0] - 1;
                int ec = points[eIdx][1] - 1;
                
                preNode = bfs(sr, sc, er, ec, dist, preNode);
                dist = preNode.d;
                sr = preNode.r;
                sc = preNode.c;
            }
            
            Node curr = preNode;
            while(curr != null) {
                int r = curr.r;
                int c = curr.c;
                int d = curr.d;
                
                board[d][r][c] += 1;
                curr = curr.preNode;
            }
        }
        
        int answer = 0;
        
        for(int i = 0; i < N * N; i++) {
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < N; c++) {
                    if(board[i][r][c] > 1) {
                        answer++;
                    }
                }
            }
        }
        
        return answer;
    }
    
    private static Node bfs(int sr, int sc, int er, int ec, int dist, Node preNode) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        
        Node start = preNode == null ? new Node(sr, sc, dist, preNode) : preNode;
        if (preNode == null) {
            q.add(start);
        } else {
            q.add(preNode);
        }
        
        visited[sr][sc] = true;
        
        Node endNode = start;
        while(!q.isEmpty()) {
            Node curr = q.poll();
            
            int r = curr.r;
            int c = curr.c;
            int d = curr.d;
            
            if(r == er && c ==ec) {
                endNode = curr;
                break;
            }
            
            for(int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                
                if(isRange(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new Node(nr, nc, d + 1, curr));
                }
            }
        }
        
        return endNode;
    }
    
    private static class Node {
        int r, c, d;
        Node preNode;
        
        public Node(int r, int c, int d, Node preNode) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.preNode = preNode;
        }
    }
    
    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}