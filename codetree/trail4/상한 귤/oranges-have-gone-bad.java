import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[][] board, trashGuel;

    private static int[][] delta = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        trashGuel = new int[K][2];
        int gIdx = 0;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < N; c++) {
                int num = Integer.parseInt(st.nextToken());

                board[r][c] = num;

                if (num == 2) {
                    trashGuel[gIdx][0] = r;
                    trashGuel[gIdx][1] = c; 
                    gIdx++;
                }
            }
        }
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static int[][] bfs() {
        Queue<int[]> q = new ArrayDeque<>();

        boolean[][] visited = new boolean[N][N];
        int[][] result = new int[N][N];

        for (int[] guel : trashGuel) {
            int r = guel[0];
            int c = guel[1];

            q.add(new int[] {r, c, 0});
            visited[r][c] = true;
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];
            int dist = curr[2];

            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];

                if (isRange(nr, nc) && !visited[nr][nc] && board[nr][nc] != 0) {
                    result[nr][nc] = dist + 1;
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc, dist + 1});
                }
            }
        }

        return result;
    }

    private static String answer(int[][] result) {
        StringBuilder sb = new StringBuilder();

        for (int r =0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int num = result[r][c];

                int state = num;

                if (num == 0 && board[r][c] == 1) {
                    state = -2;
                } else if (num == 0 && board[r][c] == 0) {
                    state = -1;
                }

                sb.append(state).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(answer(bfs()));
    }
}