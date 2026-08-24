import java.io.*;
import java.util.*;

public class Main {
    private static int N, sr, sc, er, ec;

    private static int[][] delta = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken()) - 1;
        sc = Integer.parseInt(st.nextToken()) - 1;
        er = Integer.parseInt(st.nextToken()) - 1;
        ec = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(bfs());
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static int bfs() {
        boolean[][] visited = new boolean[N][N];
        visited[sr][sc] = true;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {sr, sc, 0});

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];
            int d = curr[2];

            if (r == er && c == ec) {
                return d;
            }

            for (int i = 0; i < 8; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];

                if (isRange(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc, d + 1});
                }
            }
        }

        return -1;
    }
}