import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[] nums;

    private static final int NEG_INF = -1_000_000_000;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N + 1; i++) {
            int num = Integer.parseInt(st.nextToken());

            nums[i] = num;
        }
    }

    private static int cal() {
        int[][] dp = new int[N + 1][K + 1];

        for(int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], NEG_INF);
        }

        int answer = NEG_INF;

        for(int i = 1; i < N + 1; i++) {
            int num = nums[i];

            if(num >= 0) {
                dp[i][0] = Math.max(dp[i][0], num);

                for(int count = 0; count < K + 1; count++) {
                    if(dp[i - 1][count] == NEG_INF) {
                        continue;
                    }

                    dp[i][count] = Math.max(dp[i][count], dp[i - 1][count] + num);
                }
            } else {
                if(K >= 1) {
                    dp[i][1] = Math.max(dp[i][1], num);
                }

                for(int count = 1; count < K + 1; count++) {
                    if(dp[i - 1][count - 1] == NEG_INF) {
                        continue;
                    }

                    dp[i][count] = Math.max(dp[i][count], dp[i - 1][count - 1] + num);
                }
            }

            for(int count = 0; count < K + 1; count++) {
                answer = Math.max(answer, dp[i][count]);
            }
        }

        return answer;
    }


    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}