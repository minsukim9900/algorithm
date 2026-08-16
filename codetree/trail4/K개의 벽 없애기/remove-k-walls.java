import java.io.*;
import java.util.*;

public class Main {
    private static int N, K, sr, sc, er, ec;
    private static int[][] board;

    private static int[][] delta = {{-1 ,0}, {1, 0}, {0, -1}, {0, 1}};

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

        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken()) - 1;
        sc = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        er = Integer.parseInt(st.nextToken()) - 1;
        ec = Integer.parseInt(st.nextToken()) - 1;
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
    
    private static int bfs() {
        boolean[][][] visited = new boolean[K + 1][N][N];

        visited[0][sr][sc] = true;

        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[] {sr, sc, 0, 0});

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];
            int k = curr[2];
            int d = curr[3];

            if (r == er && c == ec) {
                return d;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];

                if (!isRange(nr, nc)) {
                    continue;
                }

                if (board[nr][nc] == 1 && k == K) {
                    continue;
                }

                int nk = board[nr][nc] == 1 ? k + 1 : k;

                if (visited[nk][nr][nc]) {
                    continue;
                }

                visited[nk][nr][nc] = true;
                q.add(new int[] {nr, nc, nk, d + 1});
            }
        }


        return -1;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(bfs());
    }
}