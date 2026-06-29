import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, totalTime;
    private static int[][] info;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        totalTime = 0;

        info = new int[N][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            info[i][0] = e;
            info[i][1] = t;
            totalTime += t;
        }
    }

    private static int cal() {
        int[] dp = new int[totalTime + 1];

        for(int i = 0; i < N; i++) {
            int e = info[i][0];
            int t = info[i][1];

            for(int time = totalTime; time >= t; time--) {
                dp[time] = Math.max(dp[time], dp[time - t] + e);
            }
        }

        for(int i = 1; i < totalTime; i++) {
            if(dp[i] >= M) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}