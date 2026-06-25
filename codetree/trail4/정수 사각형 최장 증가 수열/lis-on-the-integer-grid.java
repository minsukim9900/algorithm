import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] board;
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        board = new int[N][N];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < N; c++) {
                int num = Integer.parseInt(st.nextToken());
                board[r][c] = num;
                pq.add(new int[] {r, c, num});
            }
        }

        System.out.println(bfs(pq));
    }

    private static int bfs(PriorityQueue<int[]> pq) {
        int answer = -1;

        int[][] dist = new int[N][N];

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0];
            int c = curr[1];
            int v = curr[2];

            for(int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];

                if (isRange(nr, nc) && board[nr][nc] > v) {
                    dist[nr][nc] = Math.max(dist[nr][nc], dist[r][c] + 1);
                }
            }

            answer = Math.max(answer, dist[r][c]);
        }

        return answer + 1;
    }

    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}