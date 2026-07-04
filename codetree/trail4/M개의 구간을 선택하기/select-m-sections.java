import java.io.*;
import java.util.*;

public class Main {
    static final long NEG = Long.MIN_VALUE / 4;

    static int N, M;
    static long[] arr;
    static long[] prefix;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        prefix = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            prefix[i] = prefix[i - 1] + arr[i];
        }

        dp = new long[N + 1][M + 1];

        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], NEG);
        }

        for (int i = 0; i <= N; i++) {
            dp[i][0] = 0;
        }

        for (int m = 1; m <= M; m++) {
            long best = NEG;

            for (int i = 1; i <= N; i++) {
                int prev = Math.max(0, i - 2);

                if (dp[prev][m - 1] != NEG) {
                    best = Math.max(best, dp[prev][m - 1] - prefix[i - 1]);
                }

                dp[i][m] = dp[i - 1][m];

                if (best != NEG) {
                    dp[i][m] = Math.max(dp[i][m], prefix[i] + best);
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}