import java.io.*;
import java.util.*;

public class Main {
    private static int N, total;
    private static int[] nums;

    private static final int INF = 100_000_000;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        total = 0;
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            nums[i] = num;
            total += num;
        }
    }

    private static int cal() {
        int[] dp = new int[total + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            int num = nums[i];

            for (int j = total; j >= num; j--) {
                if (dp[j - num] == INF) {
                    continue;
                }

                dp[j] = Math.min(dp[j], dp[j - num] + 1);
            }
        }

        int result = INF;

        for(int i = 1; i < total + 1; i++) {
            if(dp[i] == INF) {
                continue;
            }

            result = Math.min(result, Math.abs(i - (total - i)));
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}