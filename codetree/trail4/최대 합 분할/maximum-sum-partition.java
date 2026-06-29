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
        int[] dp = new int[total + 1];
        Arrays.fill(dp, NOT_EXIST);
        dp[0] = 0;

        for(int i = 0; i < N; i++) {
            int num = nums[i];

            int[] next = Arrays.copyOf(dp, total + 1);

            for(int diff = 0; diff < total + 1; diff++) {
                if(dp[diff] == NOT_EXIST) {
                    continue;
                }

                int bigSum = dp[diff];
                int smallSum = bigSum - diff;

                int addBigSum = bigSum + num;
                int newDiff = diff + num;

                next[newDiff] = Math.max(next[newDiff], addBigSum);

                int addSmallSum = smallSum + num;
                newDiff = Math.abs(bigSum - addSmallSum);

                next[newDiff] = Math.max(next[newDiff], Math.max(addSmallSum, bigSum));
            }

            dp = next;
        }

        return dp[0];
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}