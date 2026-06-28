import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] nums;

    private static final int MOD = 10_007;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[3];
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = 5;
    }

    private static int cal() {
        int[] dp = new int[N + 1];
        dp[0] = 1;

        for(int i = 1; i < N + 1; i++) {
            for(int j = 0; j < 3; j++) {
                if(i - nums[j] < 0) {
                    break;
                }
                dp[i] += dp[i - nums[j]];
                dp[i] %= MOD;
            }
        }

        return dp[N];
    }

    public static void main(String[] args) throws Exception {
        init();
        
        System.out.println(cal());
    }
}