import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] board;
    private static boolean[][] visited;

    private static int[][] delta = {{1, 0}, {0, 1}};

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static void dfs(int r, int c) {
        visited[r][c] = true;

        for (int i = 0; i < 2; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];

            if (isRange(nr, nc) && board[nr][nc] == 1 && !visited[nr][nc]) {
                dfs(nr, nc);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < M; c++) {
                int num = Integer.parseInt(st.nextToken());

                board[r][c] = num;
            }
        }

        visited = new boolean[N][M];

        dfs(0, 0);

        System.out.println(visited[N - 1][M - 1] ? 1 : 0);
    }
}