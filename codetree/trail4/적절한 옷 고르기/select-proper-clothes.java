import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] clothes;
    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        clothes = new int[N + 1][3];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            clothes[i][0] = s;
            clothes[i][1] = e;
            clothes[i][2] = v;
        }
    }

    private static int cal() {
        int[][] dp = new int[M + 1][N + 1];
        for (int i = 0; i < M + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 1; i < N + 1; i++) {
            int[] clothe = clothes[i];
            int s = clothe[0];
            int e = clothe[1];
            int v = clothe[2];

            if (s == 1) {
                dp[1][i] = 0;
            }
        }

        for(int day = 2; day < M + 1; day++) {
            for(int i = 1; i < N + 1; i++) {
                if(dp[day - 1][i] == -1) {
                    continue;
                }

                int s = clothes[i][0];
                int e = clothes[i][1];
                int v = clothes[i][2];

                for(int j = 1; j < N + 1; j++) {

                    int cs = clothes[j][0];
                    int ce = clothes[j][1];
                    int cv = clothes[j][2];

                    if(day < cs || day > ce) {
                        continue;
                    }

                    dp[day][j] = Math.max(dp[day][j], dp[day - 1][i] + Math.abs(v - cv));
                }
            }
        }

        int answer = 0;
        for(int i = 1; i < N + 1; i++) {
            answer = Math.max(answer, dp[M][i]);
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}