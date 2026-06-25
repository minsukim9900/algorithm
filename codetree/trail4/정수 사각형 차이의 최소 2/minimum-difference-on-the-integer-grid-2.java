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
        visited[0][0] = true;

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(isRange(r, c, low, high)) {
                    if (r > 0 && visited[r - 1][c]) {
                        visited[r][c] = true;
                    }

                    if (c > 0 && visited[r][c - 1]) {
                        visited[r][c] = true;
                    }
                }
            }
        }

        return visited[N - 1][N - 1];
    }

    private static boolean isRange(int r, int c, int low, int high) {
        return board[r][c] >= low && board[r][c] <= high;
    }
}