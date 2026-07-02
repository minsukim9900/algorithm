import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] cards;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = 2 * N;
        cards = new int[M][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            cards[i][0] = x;
            cards[i][1] = y;
        }
    }

    private static int cal() {
        int[][] dp = new int[M + 1][N + 1];

        for (int i = 0; i <= M; i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[0][0] = 0;

        for (int i = 0; i < M; i++) {
            for (int red = 0; red <= N; red++) {
                if (dp[i][red] == -1) continue;

                // 빨간 카드 선택
                if (red + 1 <= N) {
                    dp[i + 1][red + 1] = Math.max(
                        dp[i + 1][red + 1],
                        dp[i][red] + cards[i][0]
                    );
                }

                // 파란 카드 선택
                int blue = i - red;
                if (blue + 1 <= N) {
                    dp[i + 1][red] = Math.max(
                        dp[i + 1][red],
                        dp[i][red] + cards[i][1]
                    );
                }
            }
        }

        return dp[M][N];
    }


    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}