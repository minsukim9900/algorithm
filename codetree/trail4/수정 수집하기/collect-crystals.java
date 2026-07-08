import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[] locations;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        locations = new int[N + 1];
        String str = br.readLine();

        for (int i = 1; i <= N; i++) {
            char ch = str.charAt(i - 1);

            if (ch == 'R') {
                locations[i] = 1;
            } else {
                locations[i] = 0;
            }
        }
    }

    private static int cal() {
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int k = 0; k <= K; k++) {
                int currLocation = k % 2;

                int weight = locations[i] == currLocation ? 1 : 0;

                dp[i][k] = dp[i - 1][k] + weight;

                if (k > 0) {
                    dp[i][k] = Math.max(dp[i][k], dp[i - 1][k - 1] + weight);
                }
            }
        }

        int answer = 0;

        for (int k = 0; k <= K; k++) {
            answer = Math.max(answer, dp[N][k]);
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}