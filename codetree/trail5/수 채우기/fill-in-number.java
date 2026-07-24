import java.io.*;
import java.util.*;

public class Main {
    private static int N;

    private static final int INF = 1_000_000_000;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
    }

    private static int cal() {
        int[] dp = new int[N + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int coin = 2; coin <= 5; coin+= 3) {

            for (int money = coin; money < N + 1; money++) {
                if (dp[money - coin] == INF) {
                    continue;
                }

                dp[money] = Math.min(dp[money], dp[money - coin] + 1);
            }
        }

        return dp[N] == INF ? -1 : dp[N];
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}