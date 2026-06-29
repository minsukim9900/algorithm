import java.io.*;
import java.util.*;

public class Main {
    private static int N, sum;
    private static int[] nums;

    private static final int NOT_EXISTS = -1;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        sum = 0;

        nums = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            nums[i] = num;
            sum += num;
        }
    }

    private static String cal() {
        int[] dp = new int[sum + 1];
        Arrays.fill(dp, NOT_EXISTS);
        dp[0] = 0;

        for(int i = 0; i < N; i++) {
            int num = nums[i];

            for(int diff = sum; diff >= num; diff--) {
                if(dp[diff - num] == NOT_EXISTS) {
                    continue;
                }

                dp[diff] = Math.max(dp[diff], dp[diff - num] + 1);

                if(diff == sum - (diff)) {
                    return "Yes";
                }
            }
        }

        return "No";
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}