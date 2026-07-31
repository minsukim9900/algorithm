import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, K;
    private static int[][][] board;
    private static int[][][] prefix;
    private static int[][] orders;

    private static int getChangeAlpha(char x) {
        if (x == 'a') {
            return 1;
        } else if (x == 'b') {
            return 2;
        }

        return 3;
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[4][N + 1][M + 1];
        prefix = new int[4][N + 1][M + 1];

        for (int r = 1; r < N + 1; r++) {
            String str = br.readLine();

            for (int c = 1; c < M + 1; c++) {
                char x = str.charAt(c - 1);
                int idx = getChangeAlpha(x);

                board[idx][r][c] = 1;
            }
        }

        for (int i = 1; i < 4; i++) {
            for (int r = 1; r < N + 1; r++) {
                int sum = 0;

                for (int c = 1; c < M + 1; c++) {
                    sum += board[i][r][c];

                    prefix[i][r][c] = prefix[i][r - 1][c] + sum;
                }
            }
        }

        orders = new int[K][4];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            int er = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());

            orders[i][0] = sr;
            orders[i][1] = sc;
            orders[i][2] = er;
            orders[i][3] = ec;
        }
    }

    private static String cal() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < K; i++) {
            for(int idx = 1; idx < 4; idx++) {
                int sr = orders[i][0];
                int sc = orders[i][1];
                int er = orders[i][2];
                int ec = orders[i][3];

                int result = prefix[idx][er][ec] - prefix[idx][sr - 1][ec] - prefix[idx][er][sc - 1] + prefix[idx][sr - 1][sc - 1];

                sb.append(result).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}