import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[][] board;
    private static int[][] starts;

    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < N; c++) {
                int num = Integer.parseInt(st.nextToken());

                board[r][c] = num;
            }
        }

        starts = new int[K][2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            starts[i][0] = r;
            starts[i][1] = c;
        }
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static int bfs() {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();
        int count = 0;

        for (int i = 0; i < K; i++) {
            int r = starts[i][0];
            int c = starts[i][1];

            q.add(new int[] {r, c});
            visited[r][c] = true;
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];

            count++;

            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];

                if (isRange(nr, nc) && !visited[nr][nc] && board[nr][nc] == 0) {
                    q.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }

        return count;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(bfs());
    }
}