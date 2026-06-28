import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] coins;

    private static final int INF = 100_000_000;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        coins = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int coin = Integer.parseInt(st.nextToken());

            coins[i] = coin;
        }

        Arrays.sort(coins);
    }

    private static int cal() {
        int[] dp = new int[M + 1];

        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i = 0; i < N; i++) {
            int coin = coins[i];

            for(int j = coin; j < M + 1; j++) {
                int count = dp[j - coin] + 1;

                dp[j] = Math.min(dp[j], count);
            }
        }

        int result = dp[M];
        
        return result == INF ? -1 : result;
    }

    public static void main(String[] args) throws Exception{
        init();

        System.out.println(cal());
    }
}