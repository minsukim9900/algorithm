import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] board;

    private static final int[][] DELTA = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < M; c++) {
                int state = Integer.parseInt(st.nextToken());

                board[r][c] = state;
            }
        }
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static int bfs() {
        boolean[][] visited = new boolean[N][M];
        
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0, 0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];
            int d = curr[2];

            if (r == N - 1 && c == M - 1) {
                return d;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + DELTA[i][0];
                int nc = c + DELTA[i][1];

                if (isRange(nr, nc) && board[nr][nc] == 1 && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc , d + 1});
                }
            }
        }
        
        return -1;
    }


    public static void main(String[] args) throws Exception {
        init();

        System.out.println(bfs());
    }
}