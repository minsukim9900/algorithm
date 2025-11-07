import java.io.*;
import java.util.*;

public class Main {
    private static int W, H;
    private static int[][][] board;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

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
                        board[i][r][c] = Integer.MAX_VALUE;
                    } else if (ch == 'C') {
                        board[i][r][c] = Integer.MAX_VALUE;
                    } else {
                        board[i][r][c] = -1;
                    }
                }
                if (ch == 'C') {
                    locations.add(new int[]{r, c});
                }
            }
        }

        int[] curr = locations.get(0);
        for (int i = 0; i < 4; i++) {
            board[i][curr[0]][curr[1]] = 0;
        }
        System.out.println(bfs(curr, locations.get(1)));
    }

    private static int bfs(int[] start, int[] end) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{start[0], start[1], 0, 0});
        q.add(new int[]{start[0], start[1], 1, 0});
        q.add(new int[]{start[0], start[1], 2, 0});
        q.add(new int[]{start[0], start[1], 3, 0});

        int answer = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            if (board[curr[2]][curr[0]][curr[1]] != curr[3]) {
                continue;
            }

            if (curr[0] == end[0] && curr[1] == end[1]) {
                answer = Math.min(answer, curr[3]);
                continue;
            }


            for (int dir = 0; dir < 4; dir++) {
                int nr = curr[0] + delta[dir][0];
                int nc = curr[1] + delta[dir][1];

                if (isRange(nr, nc) && board[dir][nr][nc] != -1) {
                    int next = curr[3];

                    if (curr[2] != dir) {
                        next++;
                    }

                    if (board[dir][nr][nc] > next) {
                        board[dir][nr][nc] = next;
                        q.add(new int[]{nr, nc, dir, next});
                    }
                }
            }
        }
        return answer;
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }
}