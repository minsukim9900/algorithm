import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] lines;

    private static final int MAX = 1_001;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        lines = new int[N][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            lines[i][0] = x1;
            lines[i][1] = x2;
        }

        Arrays.sort(lines, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
    }

    private static int cal() {
        int result = 0;
        int[][] dp = new int[MAX][MAX];

        for(int i = 0; i < N; i++) {
            int x1 = lines[i][0];
            int x2 = lines[i][1];
            dp[x1][x2] = Math.max(dp[x1][x2], 1);

            for(int j = 0; j < i; j++) {
                int cx1 = lines[j][0];
                int cx2 = lines[j][1];

                if(cx2 >= x1) {
                    continue;
                }

                dp[x1][x2] = Math.max(dp[x1][x2], dp[cx1][cx2] + 1);
            }

            result = Math.max(result, dp[x1][x2]);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}