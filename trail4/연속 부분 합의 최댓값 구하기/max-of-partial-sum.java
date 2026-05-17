import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] nums, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N];

        dp[0] = nums[0];

        int answer = nums[0];
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}