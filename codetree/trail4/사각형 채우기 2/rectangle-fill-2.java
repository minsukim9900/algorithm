import java.io.*;
import java.util.*;

public class Main {
    private static final int MOD = 10_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i < N + 1; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] * 2) % MOD;
        }

        System.out.println(dp[N]);
    }
}