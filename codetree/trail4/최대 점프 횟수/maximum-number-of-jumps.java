import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            nums[i] = num;
        }

        int[] dp = new int[N];
        dp[0] = 1;

        int answer = 0;
        for (int i = 1; i < N; i++) {
            for(int j = 0; j < i; j++) {
                if (dp[j] <= 0 || nums[j] + j < i) {
                    continue;
                }

                dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer - 1);
    }
}