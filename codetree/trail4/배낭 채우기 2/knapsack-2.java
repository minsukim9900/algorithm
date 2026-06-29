import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] items;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        items = new int[N][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            items[i][0] = w;
            items[i][1] = v;
        }
    }

    private static int cal() {
        int[] dp = new int[M + 1];
        
        for(int[] item : items) {
            int w = item[0];
            int v = item[1];

            for(int weight = w; weight < M + 1; weight++) {
                dp[weight] = Math.max(dp[weight], dp[weight - w] + v);
            }
        }

        return dp[M];
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}