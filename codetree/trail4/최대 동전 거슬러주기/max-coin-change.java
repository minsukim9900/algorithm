import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] coins;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        coins = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static int cal() {
        int[] dp = new int[M + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for(int coin : coins) {
            for(int money = coin; money < M + 1; money++) {
                if(dp[money - coin] == -1) {
                    continue;
                }
                
                dp[money] = Math.max(dp[money], dp[money - coin] + 1);
            }
        }

        return dp[M] == 0 ? -1 : dp[M];
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}