import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] nums, dp;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        nums = new int[2][N];
        dp = new int[N][N];

        for(int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                
                nums[i][j] = num;
            }
        }

        for(int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

    }

    private static int cal(int aIdx, int bIdx) {
        if(aIdx == N || bIdx == N) {
            return 0;
        }

        if(dp[aIdx][bIdx] != -1) {
            return dp[aIdx][bIdx];
        }

        if(nums[0][aIdx] < nums[1][bIdx]) {
            dp[aIdx][bIdx] = Math.max(dp[aIdx][bIdx], cal(aIdx + 1, bIdx));
        }

        if(nums[0][aIdx] > nums[1][bIdx]) {
            dp[aIdx][bIdx] = Math.max(dp[aIdx][bIdx], cal(aIdx, bIdx + 1) + nums[1][bIdx]);
        }

        dp[aIdx][bIdx] = Math.max(dp[aIdx][bIdx], cal(aIdx + 1, bIdx + 1));

        return dp[aIdx][bIdx];
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal(0, 0));
    }
}