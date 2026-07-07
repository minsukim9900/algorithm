import java.io.*;
import java.util.*;

public class Main {
    private static int N;

    private static final int MOD = 1_000_000_007;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
    }

    private static int cal() {
        int[][] dp = new int[N + 1][10];

        for(int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for(int i = 2; i < N + 1; i++) {
            for(int j = 0; j < 10; j++) {
                if(j < 9) {
                    dp[i][j] += dp[i - 1][j + 1];
                    dp[i][j] %= MOD;
                }

                if(j > 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                    dp[i][j] %= MOD;
                }
            }
        }

        int result = 0;
        for(int i = 0; i < 10; i++) {
            result += dp[N][i];
            result %= MOD;
        }

        return result;
    }


    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}