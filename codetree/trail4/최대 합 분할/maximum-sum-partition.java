import java.io.*;
import java.util.*;

public class Main {
    private static int N, total;
    private static int[] nums;

    private static final int NOT_EXIST = -1;

    private static void init() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            total += num;
            nums[i] = num;
        }
    }

    private static int cal() {

        int[][] dp = new int[N + 1][total + 1];

        for(int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], NOT_EXIST);
        }

        dp[0][0] = 0;

        for(int i = 0; i < N; i++) {
            int num = nums[i];

            for(int diff = 0; diff < total + 1; diff++) {
                if(dp[i][diff] == NOT_EXIST) {
                    continue;
                }

                int bigSum = dp[i][diff];
                int smallSum = bigSum - diff;

                // 1. C 그룹에 넣는 경우
                dp[i + 1][diff] = Math.max(dp[i + 1][diff], bigSum);

                // 2. 큰 쪽에 num을 넣는 경우
                int addToBigDiff = diff + num;
                int addToBigSum = bigSum + num;

                dp[i + 1][addToBigDiff] = Math.max(dp[i + 1][addToBigDiff], addToBigSum);

                // 3. 작은 쪽에 num을 넣는 경우
                int addToSmallSum = smallSum + num;
                int addToSmallDiff = Math.abs(bigSum - addToSmallSum);
                int addToSmallBigSum = Math.max(bigSum, addToSmallSum);

                dp[i + 1][addToSmallDiff] = Math.max(dp[i + 1][addToSmallDiff], addToSmallBigSum);
            }
        }

        return dp[N][0];
    }

    public static void main(String[] args) throws Exception {

        init();

        System.out.println(cal());
    }
}