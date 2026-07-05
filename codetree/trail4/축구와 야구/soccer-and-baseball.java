import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] students;

    private static final int SOCCER = 11;
    private static final int BASEBALL = 9;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        students = new int[N + 1][2];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            students[i][0] = s;
            students[i][1] = b;
        }
    }

    private static int cal() {
        int[][] dp = new int[SOCCER + 1][BASEBALL + 1];
        for (int i = 0; i < SOCCER + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;

        for (int i = 1; i < N + 1; i++) {
            int s = students[i][0];
            int b = students[i][1];

            for (int j = SOCCER; j >= 0; j--) {
                for (int k = BASEBALL; k >= 0; k--) {
                    if (dp[j][k] == -1) {
                        continue;
                    }

                    if (j + 1 <= SOCCER) {
                        dp[j + 1][k] = Math.max(dp[j + 1][k], dp[j][k] + s);
                    }

                    if (k + 1 <= BASEBALL) {
                        dp[j][k + 1] = Math.max(dp[j][k + 1], dp[j][k] + b);
                    }
                }
            }
        }


        return dp[SOCCER][BASEBALL];
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}