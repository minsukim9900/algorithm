import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] board;
    private static int[][] delta = {{1 ,0}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        int low = 200;
        int high = 0;

        for(int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 0; c < N; c++) {
                int num = Integer.parseInt(st.nextToken());

                board[r][c] = num;
                low = Math.min(low, num);
                high = Math.max(high, num);
            }
        }

        int answer = 200;
        for(int l = low; l <= high; l++) {
            for(int h = l; h <= high; h++) {
                if(h - l > answer) {
                    break;
                }

                if(isRange(0, 0, l, h) && isPoss(l, h)) {
                    answer = Math.min(answer, h - l);
                    break;
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean isPoss(int low, int high) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0, 0});
        visited[0][0] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            if (r == N - 1 && c == N - 1) {
                return true;
            }

            for(int i = 0; i < 2; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];

                if(isRange(nr, nc, low, high) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc});
                }
            }
        }

        return false;
    }

    private static boolean isRange(int r, int c, int low, int high) {
        return r >= 0 && r < N && c >= 0 && c < N && board[r][c] >= low && board[r][c] <= high;
    }
}