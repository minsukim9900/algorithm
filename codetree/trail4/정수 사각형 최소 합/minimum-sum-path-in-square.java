import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] board = new int[N + 1][N + 1];

        for (int r = 1; r < N + 1; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c < N + 1; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[N + 2][N + 2];

        for (int i = 0; i < N + 1; i++) {
            dp[0][i] = INF;
            dp[i][N + 1] = INF;
        }

        dp[0][N] = 0;

        for (int r = 1; r < N + 1; r++) {
            for (int c = N; c > 0; c--) {
                dp[r][c] = Math.min(dp[r - 1][c], dp[r][c + 1]) + board[r][c];
            }
        }

        System.out.println(dp[N][1]);
    }
}
