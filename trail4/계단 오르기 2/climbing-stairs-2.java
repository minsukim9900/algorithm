import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] coins = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[4][N + 1];

        dp[0][0] = 0;
        dp[1][1] = coins[1];

        for (int i = 2; i < N + 1; i++) {
            dp[i % 2][i] = dp[i % 2][i - 2] + coins[i];

            for (int j = 1; j <= Math.min(3, i); j++) {
                dp[j][i] = Math.max(dp[j][i], 
                        Math.max(dp[j][i - 2], dp[j - 1][i - 1]) + coins[i]);
            }
        }
        
        int answer = 0;

        for (int j = 0; j <= 3; j++) {

            answer = Math.max(answer, dp[j][N]);

        }

        System.out.println(answer);
    }
}