import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] nums;

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

    private static long cal() {
        long[][] dp = new long[N + 1][41];
        dp[0][20] = 1;

        for(int i = 0; i < N; i++) {
            int num = nums[i];

            for(int j = 0; j < 41; j++) {
                if(dp[i][j] == 0) {
                    continue;
                }

                if(j + num <= 40) {
                    dp[i + 1][j + num] += dp[i][j];
                }

                if(j - num >= 0) {
                    dp[i + 1][j - num] += dp[i][j];
                }
            }
        }

        return dp[N][M + 20];
    }


    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}