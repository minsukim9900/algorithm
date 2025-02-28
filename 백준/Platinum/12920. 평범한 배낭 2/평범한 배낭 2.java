import java.io.*;
import java.util.*;

public class Main {

    private static int N, M;
    private static int[][] items;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        items = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            items[i][0] = Integer.parseInt(st.nextToken()); 
            items[i][1] = Integer.parseInt(st.nextToken()); 
            items[i][2] = Integer.parseInt(st.nextToken()); 
        }

        ArrayList<int[]> arr = new ArrayList<>();

        for (int[] w : items) {
            int weight = w[0];
            int value = w[1];
            int count = w[2];

            for (int k = 1; count > 0; k <<= 1) {
                int portion = Math.min(k, count);
                arr.add(new int[] { weight * portion, value * portion });
                count -= portion;
            }
        }

        int[] dp = new int[M + 1];
        for (int[] curr : arr) {
            int w = curr[0];
            int c = curr[1];

            for (int j = M; j >= w; j--) {
                dp[j] = Math.max(dp[j], dp[j - w] + c);
            }
        }

        System.out.println(dp[M]);
    }
}