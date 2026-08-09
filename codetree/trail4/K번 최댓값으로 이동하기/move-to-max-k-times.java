import java.io.*;
import java.util.*;

public class Main {
    private static int N, K, startR, startC;
    private static int[][] board;
    private static boolean[][] visited;

    private static final int[][] DELTA = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        visited = new boolean[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < N; c++) {
                int num = Integer.parseInt(st.nextToken());

                board[r][c] = num;
            }
        }

        st = new StringTokenizer(br.readLine());

        startR = Integer.parseInt(st.nextToken()) - 1;
        startC = Integer.parseInt(st.nextToken()) - 1;
    }

    private static void initVisited() {
        for (int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                visited[r][c] = false;
            }
        }
    }
    
    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static int[] bfs(int sr, int sc, int num) {
        initVisited();

        visited[sr][sc] = true;

        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[] {sr, sc});

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[2] == b[2] 
                ? a[0] == b[0] 
                ? Integer.compare(a[1], b[1]) 
                : Integer.compare(a[0], b[0]) 
                : Integer.compare(b[2], a[2])
        );

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + DELTA[i][0];
                int nc = c + DELTA[i][1];

                if (isRange(nr, nc) && board[nr][nc] < num && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                    pq.add(new int[] {nr, nc, board[nr][nc]});
                }
            }
        }

        return pq.size() > 0 ? pq.poll() : null;
    }
    
    private static String playGame() {
        int sr = startR;
        int sc = startC;

        for (int k = 0; k < K; k++) {
            int[] result = bfs(sr, sc, board[sr][sc]);

            if (result == null) {
                break;
            }

            sr = result[0];
            sc = result[1];
        }

        StringBuilder sb = new StringBuilder();

        sb.append(sr + 1).append(" ").append(sc + 1);

        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        init();

        System.out.println(playGame());
    }
}