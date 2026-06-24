import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][N];
        dp[0][0] = board[0][0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], board[i][0]);
            dp[0][i] = Math.min(dp[0][i - 1], board[0][i]);

        }

        for (int r = 1; r < N; r++) {
            for (int c = 1; c < N; c++) {
                dp[r][c] = Math.min(Math.max(dp[r - 1][c], dp[r][c - 1]), board[r][c]);
            }
        }

        System.out.println(dp[N - 1][N - 1]);
    }
}
