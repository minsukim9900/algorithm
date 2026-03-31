import java.util.*;

class Solution {
    private static int N, M;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int[][] board;
    
    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        board = maze;
        
        int rsr = 0;
        int rsc = 0;
        int rer = 0;
        int rec = 0;
        
        int bsr = 0;
        int bsc = 0;
        int ber = 0;
        int bec = 0;
        
        
        for(int r = 0; r < N; r++) {
            for(int c =0; c < M; c++) {
                if(maze[r][c] == 1) {
                    rsr = r;
                    rsc = c;
                } else if(maze[r][c] == 2) {
                    bsr = r;
                    bsc = c;
                } else if(maze[r][c] == 3) {
                    rer = r;
                    rec = c;
                } else if(maze[r][c] == 4){
                    ber = r;
                    bec = c;
                }
            }
        }
        
        int answer = bfs(rsr, rsc, rer, rec, bsr, bsc, ber, bec);
        return answer;
    }
    
    private static int bfs(int rsr, int rsc, int rer, int rec, int bsr, int bsc, int ber, int bec) {
        int rStart = change(rsr, rsc);
        int cStart = change(bsr, bsc);
        
        
        
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {rsr, rsc, bsr, bsc, 
                         0, 0, 1 << rStart, 1 << cStart, 0});
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            
            int rr = curr[0];
            int rc = curr[1];
            int br = curr[2];
            int bc = curr[3];
            int redFlag = curr[4];
            int blueFlag = curr[5];
            int redVisited = curr[6];
            int blueVisited = curr[7];
            int count = curr[8];
            
            
            if(redFlag == 1 && blueFlag == 1) {
                return count;
            }
            
            int nrr = rr;
            int nrc = rc;
            int nbr = br;
            int nbc = bc;
            
            for(int i = 0; i < 4; i++) {
                
                if(redFlag == 0) {
                    nrr = rr + delta[i][0];
                    nrc = rc + delta[i][1];
                    
                    if(!isRange(nrr, nrc) || board[nrr][nrc] == 5) continue;
                
                    int nextR = change(nrr, nrc);
                    if(isVisited(redVisited, nextR)) continue;
                }
                
                for(int j = 0; j < 4; j++) {
                    if(blueFlag == 0) {
                        nbr = br + delta[j][0];
                        nbc = bc + delta[j][1];
                        
                        if(!isRange(nbr, nbc) || board[nbr][nbc] == 5 ) {
                            continue;
                        }
                        
                        int nextB = change(nbr, nbc);
                    
                        if(isVisited(blueVisited, nextB)) continue;
                    }
                    
                    if(nrr == nbr && nrc == nbc) continue;
                    if(nrr == br && nrc == bc && nbr == rr && nbc == rc) continue;
                    
                    int nextRedFlag = redFlag;
                    int nextBlueFlag = blueFlag;
                    
                    if(nrr == rer && nrc == rec) {
                        nextRedFlag = 1;
                    }
                    
                    if(nbr == ber && nbc == bec) {
                        nextBlueFlag = 1;
                    }
                    
                    
                    
                    q.add(new int[] {nrr, nrc, nbr, nbc, 
                                     nextRedFlag, nextBlueFlag,  
                                     redVisited | (1 << change(nrr, nrc)), 
                                     blueVisited | (1 << change(nbr, nbc)),
                                     count + 1});
                }
            }  
        }
        return 0;
    }
    
    private static boolean isVisited(int flag, int num) {
        return (flag & (1 << num)) != 0;
    } 
    
    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
    
    private static int change(int r, int c) {
        return r * N + c;
    }
}