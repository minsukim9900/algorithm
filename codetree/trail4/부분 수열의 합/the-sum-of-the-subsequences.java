import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] arr;

    private static final int INF = 100_000_000;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
           arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static String cal() {
        int[] dp = new int[M + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i = 0; i < N; i++) {
            int num = arr[i];

            if(num > M) {
                continue;
            }

            for(int j = M; j >= num; j--) {
                if(dp[j - num] == INF) {
                    continue;
                }

                dp[j] = Math.min(dp[j], dp[j - num] + 1);
            }
        }

        return dp[M] != INF ? "Yes" : "No";
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}