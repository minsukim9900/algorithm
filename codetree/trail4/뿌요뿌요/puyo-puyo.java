import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] board;

    private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static int bfs(int sr, int sc, int target, boolean[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[] { sr, sc });

        visited[sr][sc] = true;

        int count = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            count++;

            int r = curr[0];
            int c = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];

                if (isRange(nr, nc) && !visited[nr][nc] && board[nr][nc] == target) {
                    visited[nr][nc] = true;
                    q.add(new int[] { nr, nc });
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < N; c++) {
                int num = Integer.parseInt(st.nextToken());

                board[r][c] = num;
            }
        }

        int answer1 = 0;
        int answer2 = 0;

        boolean[][] visited = new boolean[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (visited[r][c]) {
                    continue;
                }

                int result = bfs(r, c, board[r][c], visited);

                if (result > 3) {
                    answer1++;
                }

                answer2 = Math.max(answer2, result);
            }
        }

        System.out.println(answer1 + " " + answer2);

    }
}
