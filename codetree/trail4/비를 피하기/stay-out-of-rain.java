import java.io.*;
import java.util.*;

public class Main {
    private static int N, H, M;
    private static int[][] board, persons, spaces;

    private static final int[][] DELTA = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];

        persons = new int[H][2];
        int pIdx = 0;

        spaces = new int[M][2];
        int sIdx = 0;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < N; c++) {
                int state = Integer.parseInt(st.nextToken());

                board[r][c] = state;

                if (state == 2) {
                    persons[pIdx][0] = r;
                    persons[pIdx][1] = c;
                    pIdx++;
                }

                if (state == 3) {
                    spaces[sIdx][0] = r;
                    spaces[sIdx][1] = c;
                    sIdx++;
                }
            }
        }
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static int[][] bfs() {
        int[][] dist = new int[N][N];

        for (int r = 0; r < N; r++) {
            Arrays.fill(dist[r], -1);
        }

        Queue<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < M; i++) {
            int r = spaces[i][0];
            int c = spaces[i][1];

            dist[r][c] = 0;
            q.add(new int[] {r, c});
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + DELTA[i][0];
                int nc = c + DELTA[i][1];

                if (isRange(nr, nc) && board[nr][nc] != 1 && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    q.add(new int[] {nr, nc});
                }
            }
        }

        int[][] result = new int[N][N];

        for (int i = 0; i < H; i++) {
            int r = persons[i][0];
            int c = persons[i][1];

            result[r][c] = dist[r][c];
        }

        return result;
    }
    
    private static String answer() {
        StringBuilder sb = new StringBuilder();

        int[][] result = bfs();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                sb.append(result[r][c]).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        init();

        System.out.println(answer());
    }
}