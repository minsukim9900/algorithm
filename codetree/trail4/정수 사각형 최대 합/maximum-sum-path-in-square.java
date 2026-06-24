import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

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

        long[][] dp = new long[N + 1][N + 1];

        for (int r = 1; r < N + 1; r++) {
            for (int c = 1; c < N + 1; c++) {
                dp[r][c] = Math.max(dp[r - 1][c], dp[r][c - 1]) + board[r][c];
            }
        }

        System.out.println(dp[N][N]);
    }
}
