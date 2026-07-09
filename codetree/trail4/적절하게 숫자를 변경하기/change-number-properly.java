import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] nums;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static int cal() {
        int[][][] dp = new int[N][5][M + 1];

        for (int i = 0; i < N; i++) {
            for (int num = 1; num <= 4; num++) {
                Arrays.fill(dp[i][num], -1);
            }
        }

        for (int num = 1; num <= 4; num++) {
            dp[0][num][0] = nums[0] == num ? 1 : 0;
        }

        for (int i = 1; i < N; i++) {
            for (int prev = 1; prev <= 4; prev++) {
                for (int count = 0; count <= M; count++) {
                    if (dp[i - 1][prev][count] == -1) {
                        continue;
                    }

                    for (int curr = 1; curr <= 4; curr++) {
                        int nextCount = count;

                        if (prev != curr) {
                            nextCount++;
                        }

                        if (nextCount > M) {
                            continue;
                        }

                        int score = nums[i] == curr ? 1 : 0;

                        dp[i][curr][nextCount] = Math.max(
                                dp[i][curr][nextCount],
                                dp[i - 1][prev][count] + score
                        );
                    }
                }
            }
        }

        int answer = 0;

        for (int num = 1; num <= 4; num++) {
            for (int count = 0; count <= M; count++) {
                answer = Math.max(answer, dp[N - 1][num][count]);
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}