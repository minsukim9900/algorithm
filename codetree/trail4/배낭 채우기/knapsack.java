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

    /**
    * 아이디어 핵심
    * 부분 수열의 합 문제와 유사하게 생각할 수 있다.
    * 아이템은 하나씩만 사용할 수 있으므로 0/1 배낭 문제이다.
    * dp[weight]는 현재까지 고려한 아이템들로 weight 이하의 무게를 사용할 때 얻을 수 있는 최대 가치이다.
    */

    private static int cal() {
        int[] dp = new int[M + 1];

        for(int i = 0; i < N; i++) {
            int w = items[i][0];
            int v = items[i][1];

            for(int weight = M; weight >= w; weight--) {
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