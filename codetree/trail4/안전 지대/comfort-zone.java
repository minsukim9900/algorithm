import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, max;
    private static int[][] board;

    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static void bfs(int sr, int sc, int k, boolean[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {sr, sc});

        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];

                if (isRange(nr, nc) && !visited[nr][nc] && board[nr][nc] > k) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                }
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
        max = 0;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < M; c++) {
                int num = Integer.parseInt(st.nextToken());

                board[r][c] = num;

                max = Math.max(max, num);
            }
        }

        int answerA = 0;
        int answerB = -1;

        for (int k = 1; k < max + 1; k++) {
            boolean[][] visited = new boolean[N][M];
            int count = 0;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (board[r][c] <= k || visited[r][c]) {
                        continue;
                    }

                    count++;
                    bfs(r, c, k, visited);
                }

                if (count > answerB) {
                    answerA = k;
                    answerB = count;
                }
            }
        }

        System.out.println(answerA + " " + answerB);
    }
}