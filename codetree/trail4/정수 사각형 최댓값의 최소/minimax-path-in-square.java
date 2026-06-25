import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] board;

    private static final int INF = 1_000_001;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];

        for(int r = 1; r < N + 1; r++) {
            st = new StringTokenizer(br.readLine());

            for(int c = 1; c < N + 1; c++) {
                int num = Integer.parseInt(st.nextToken());
                board[r][c] = num;
            }
        }

        int[][] dp = new int[N + 1][N + 1];

        for(int i = 0; i < N + 1; i++) {
            dp[i][0] = INF;
            dp[0][i] = INF;
        }

        dp[0][1] = 0;
        dp[1][0] = 0;

        for(int r = 1; r < N + 1; r++) {
            for(int c = 1; c < N + 1; c++) {

                dp[r][c] = Math.max(Math.min(dp[r - 1][c], dp[r][c - 1]), board[r][c]);
            }
        }

        System.out.println(dp[N][N]);
    }
}