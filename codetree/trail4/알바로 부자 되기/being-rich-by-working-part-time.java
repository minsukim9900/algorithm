import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] partTimeJobs;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        partTimeJobs = new int[N][3];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int money = Integer.parseInt(st.nextToken());

            partTimeJobs[i][0] = s;
            partTimeJobs[i][1] = e;
            partTimeJobs[i][2] = money;
        }
    }

    private static int cal() {
        int result = 0;
        int[] dp = new int[N];

        for(int i = 0; i < N; i++) {
            int currS = partTimeJobs[i][0];
            int currE = partTimeJobs[i][1];
            int currMoney = partTimeJobs[i][2];
            dp[i] = currMoney;

            for(int j = 0; j < i; j++) {
                int preS = partTimeJobs[j][0];
                int preE = partTimeJobs[j][1];
                int preMoney = partTimeJobs[j][2];

                if(preE >= currS) {
                    continue;
                }

                dp[i] = Math.max(dp[i], dp[j] + currMoney);
            }

            result = Math.max(result, dp[i]);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        init();

        int answer = cal();

        System.out.println(answer);
    }
}