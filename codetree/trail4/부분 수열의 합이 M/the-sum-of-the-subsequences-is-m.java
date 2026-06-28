import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] nums;

    private static final int INF = 100_000_000;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            nums[i] = num;
        }
    }

    private static int cal() {
        int[] dp = new int[M + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i = 0; i < N; i++) {
            int num = nums[i];

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

        int result = dp[M];

        return result == INF ? -1 : result;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}