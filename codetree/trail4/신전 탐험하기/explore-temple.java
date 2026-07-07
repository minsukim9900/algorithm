import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] floors;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        floors = new int[N + 1][3];
        for(int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            floors[i][0] = l;
            floors[i][1] = m;
            floors[i][2] = r;
        }
    }

    private static int cal() {
        int[][] dp = new int[N + 1][3];

        for(int i = 0; i < 3; i++) {
            dp[1][i] = floors[1][i];
        }

        for(int i = 2; i < N + 1; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][2]) + floors[i][0];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]) + floors[i][1];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][0]) + floors[i][2];
        }

        int result = 0;
        for(int i = 0; i < 3; i++) {
            result = Math.max(result, dp[N][i]);
        }

        return result;
    }
    
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}