import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static char[][] board;

    private static int[][] horizontal;
    private static int[][] vertical;

    private static int segmentCount;

    private static void buildSegments() {
        horizontal = new int[N][N];
        vertical = new int[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] == '*') continue;

                if (c == 0 || board[r][c - 1] == '*') {
                    segmentCount++;
                }

                horizontal[r][c] = segmentCount - 1;
            }
        }

        for (int c = 0; c < N; c++) {
            for (int r = 0; r < N; r++) {
                if (board[r][c] == '*') continue;

                if (r == 0 || board[r - 1][c] == '*') {
                    segmentCount++;
                }

                vertical[r][c] = segmentCount - 1;
            }
        }
    }

    private static int bfs(int sr, int sc, int er, int ec) {
        buildSegments();

        List<Integer>[] adj = new ArrayList[segmentCount];

        for (int i = 0; i < segmentCount; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] != '!') continue;

                int h = horizontal[r][c];
                int v = vertical[r][c];

                adj[h].add(v);
                adj[v].add(h);
            }
        }

        int[] dist = new int[segmentCount];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new ArrayDeque<>();

        int startH = horizontal[sr][sc];
        int startV = vertical[sr][sc];

        dist[startH] = 0;
        dist[startV] = 0;

        q.add(startH);
        q.add(startV);

        int endH = horizontal[er][ec];
        int endV = vertical[er][ec];

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (curr == endH || curr == endV) {
                return dist[curr];
            }

            for (int next : adj[curr]) {
                if (dist[next] != -1) continue;

                dist[next] = dist[curr] + 1;
                q.add(next);
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        N = Integer.parseInt(br.readLine());
        board = new char[N][N];

        int[][] location = new int[2][2];
        int count = 0;

        for (int r = 0; r < N; r++) {
            board[r] = br.readLine().toCharArray();

            for (int c = 0; c < N; c++) {
                if (board[r][c] == '#') {
                    location[count][0] = r;
                    location[count][1] = c;
                    count++;
                }
            }
        }

        System.out.println(bfs(
            location[0][0], location[0][1],
            location[1][0], location[1][1]
        ));
    }
}