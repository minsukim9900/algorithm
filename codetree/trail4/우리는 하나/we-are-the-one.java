import java.io.*;
import java.util.*;

public class Main {
    private static int N, K, U, D, answer;
    private static int[][] board;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        answer = 0;

        board = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < N; c++) {
                int num = Integer.parseInt(st.nextToken());

                board[r][c] = num;
            }
        }
    }

    private static boolean isRange(int nr, int nc, int r, int c) {
        return nr >= 0 && nr < N && nc >= 0 && nc < N && Math.abs(board[nr][nc] - board[r][c]) >= U && Math.abs(board[nr][nc] - board[r][c]) <= D;
    }

    private static int bfs(int[] arr) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];

            int r = num / N;
            int c = num % N;

            visited[r][c] = true;
            q.add(new int[] {r, c});
        }

        int result = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];
            int value = board[r][c];

            result++;

            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];

                if (isRange(nr, nc, r, c) && !visited[nr][nc]) {
                    q.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }

        return result;
    }

    private static void dfs(int depth, int idx, int[] arr) {
        if (depth == K) {
            int result = bfs(arr);
            answer = Math.max(answer, result);

            return;
        }

        for (int i = idx; i < N * N; i++) {
            arr[depth] = i;
            dfs(depth + 1, i + 1,arr);
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        dfs(0, 0, new int[K]);
        System.out.println(answer);
    }
}