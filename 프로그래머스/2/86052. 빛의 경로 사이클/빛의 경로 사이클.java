import java.util.*;

class Solution {
    private static final int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};
    public ArrayList<Integer> solution(String[] grid) {
        int N = grid.length, M = grid[0].length();
        char[][] board = new char[N][];
        for (int r = 0; r < N; r++) board[r] = grid[r].toCharArray();

        boolean[][][] visited = new boolean[4][N][M];
        ArrayList<Integer> ans = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                for (int d = 0; d < 4; d++) {
                    if (visited[d][r][c]) continue;

                    int cr = r, cc = c, cd = d, len = 0;

                    // 이번 출발에서 새로 방문한 것들만 길이에 포함
                    while (!visited[cd][cr][cc]) {
                        visited[cd][cr][cc] = true;
                        len++;

                        // 현재 칸에서 회전 결정
                        char ch = board[cr][cc];
                        if (ch == 'L') cd = (cd + 3) % 4;        // 왼쪽 회전
                        else if (ch == 'R') cd = (cd + 1) % 4;   // 오른쪽 회전

                        // 한 칸 전진 (토러스)
                        cr = (cr + delta[cd][0] + N) % N;
                        cc = (cc + delta[cd][1] + M) % M;
                    }

                    if (len > 0) ans.add(len);
                }
            }
        }

        Collections.sort(ans);
        return ans;
    }
}