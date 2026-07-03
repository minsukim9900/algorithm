import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static final int MOD = 1_000_000_007;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
    }

    private static int cal() {
        int[][][] dp = new int[N + 1][3][3];
        dp[0][0][0] = 1;

        for(int i = 1; i < N + 1; i++) {
            for(int b = 0; b < 3; b++) {
                for(int t = 0; t < 3; t++) {
                    int pre = dp[i - 1][b][t];

                    if(pre == 0) {
                        continue;
                    }

                    // G를 받을 때
                    dp[i][0][t] += pre;
                    dp[i][0][t] %= MOD;


                    // B를 받을 때
                    if(b + 1 < 3) {
                        dp[i][b + 1][t] += pre;
                        dp[i][b + 1][t] %= MOD;
                    }

                    // T를 받을 때
                    if(t + 1 < 3) {
                        dp[i][0][t + 1] += pre;
                        dp[i][0][t + 1] %= MOD;
                    }
                }
            }
        }
        
        int answer = 0;

        for(int b = 0; b < 3; b++) {
            for(int t = 0; t < 3; t++) {
                if(dp[N][b][t] == -1) {
                    continue;
                }

                answer += dp[N][b][t];
                answer %= MOD;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}