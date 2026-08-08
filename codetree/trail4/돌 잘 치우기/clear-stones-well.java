import java.io.*;
import java.util.*;

public class Main {
    private static int N, K, M, S, answer;
    private static int[][] board, starts;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static List<int[]> stones;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = 0;

        board = new int[N][N];
        stones = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int num = Integer.parseInt(st.nextToken());

                board[r][c] = num;

                if (num == 1) {
                    S++;
                    stones.add(new int[] {r, c});
                }
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
        int count = 0;

        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < K; i++) {
            int sr = starts[i][0];
            int sc = starts[i][1];

            q.add(new int[] {sr, sc});
            visited[sr][sc] = true;
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            count++;

            int r = curr[0];
            int c = curr[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + delta[d][0];
                int nc = c + delta[d][1];

                if (isRange(nr, nc)
                        && !visited[nr][nc]
                        && board[nr][nc] == 0) {

                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                }
            }
        }

        return count;
    }

    private static void dfs(int idx, int depth, int[] select) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                int[] stone = stones.get(select[i]);

                int r = stone[0];
                int c = stone[1];

                board[r][c] = 0;
            }

            answer = Math.max(answer, bfs());

            for (int i = 0; i < M; i++) {
                int[] stone = stones.get(select[i]);

                int r = stone[0];
                int c = stone[1];

                board[r][c] = 1;
            }
            return;
        }

        for (int i = idx; i < S; i++) {
            select[depth] = i;
            dfs(i + 1, depth + 1, select);
        }
    }

    public static void main(String[] args) throws Exception {
        init();

        dfs(0, 0, new int[M]);

        System.out.println(answer);
    }
}