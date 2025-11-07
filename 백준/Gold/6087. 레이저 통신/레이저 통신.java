import java.io.*;
import java.util.*;

public class Main {
    private static int W, H;
    private static int[][][] board;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int MAX = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new int[4][H][W];
        List<int[]> locations = new ArrayList<>();

        for (int r = 0; r < H; r++) {
            String str = br.readLine();
            for (int c = 0; c < W; c++) {
                char ch = str.charAt(c);

                for (int i = 0; i < 4; i++) {

                    if (ch == '.') {
                        board[i][r][c] = MAX;
                    } else if (ch == 'C') {
                        board[i][r][c] = MAX;
                    } else {
                        board[i][r][c] = -1;
                    }
                }
                if (ch == 'C') {
                    locations.add(new int[]{r, c});
                }
            }
        }

        System.out.println(bfs(locations.get(0), locations.get(1)));
    }

    private static int bfs(int[] start, int[] end) {
        int sr = start[0];
        int sc = start[1];
        int er = end[0];
        int ec = end[1];

        for (int i = 0; i < 4; i++) {
            board[i][sr][sc] = 0;
        }

        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{sr, sc, 0, 0});
        dq.add(new int[]{sr, sc, 1, 0});
        dq.add(new int[]{sr, sc, 2, 0});
        dq.add(new int[]{sr, sc, 3, 0});

        int answer = MAX;

        while (!dq.isEmpty()) {
            int[] curr = dq.poll();
            int r = curr[0];
            int c = curr[1];
            int d = curr[2];
            int cost = curr[3];

            if (cost >= answer) {
                continue;
            }

            if (board[d][r][c] != cost) {
                continue;
            }

            if (r == er && c == ec) {
                answer = Math.min(answer, cost);
                continue;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nr = r + delta[dir][0];
                int nc = c + delta[dir][1];

                if (nr >= 0 && nr < H && nc >= 0 && nc < W && board[dir][nr][nc] != -1) {
                    int next = cost + (dir == d ? 0 : 1);
                    if(next >= answer) continue;

                    if (board[dir][nr][nc] > next) {
                        board[dir][nr][nc] = next;
                        if (dir == d) {
                            dq.addFirst(new int[]{nr, nc, dir, next});
                        } else {
                            dq.addLast(new int[]{nr, nc, dir, next});
                        }
                    }
                }
            }
        }
        return answer;
    }
}