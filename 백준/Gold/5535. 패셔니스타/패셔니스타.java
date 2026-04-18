import java.io.*;

import java.util.*;

public class Main {

    static class Cloth {

        int low, high, flashy;

        Cloth(int low, int high, int flashy) {

            this.low = low;

            this.high = high;

            this.flashy = flashy;

        }

        boolean canWear(int temp) {

            return low <= temp && temp <= high;

        }

    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int D = Integer.parseInt(st.nextToken());

        int N = Integer.parseInt(st.nextToken());

        int[] temp = new int[D];

        for (int i = 0; i < D; i++) {

            temp[i] = Integer.parseInt(br.readLine());

        }

        Cloth[] clothes = new Cloth[N];

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());

            int b = Integer.parseInt(st.nextToken());

            int c = Integer.parseInt(st.nextToken());

            clothes[i] = new Cloth(a, b, c);

        }

        int[][] dp = new int[D][N];

        for (int i = 0; i < D; i++) {

            Arrays.fill(dp[i], -1);

        }

        // 첫째 날

        for (int j = 0; j < N; j++) {

            if (clothes[j].canWear(temp[0])) {

                dp[0][j] = 0;

            }

        }

        // 둘째 날부터

        for (int day = 1; day < D; day++) {

            for (int cur = 0; cur < N; cur++) {

                if (!clothes[cur].canWear(temp[day])) continue;

                for (int prev = 0; prev < N; prev++) {

                    if (dp[day - 1][prev] == -1) continue;

                    if (!clothes[prev].canWear(temp[day - 1])) continue;

                    dp[day][cur] = Math.max(

                        dp[day][cur],

                        dp[day - 1][prev] + Math.abs(clothes[cur].flashy - clothes[prev].flashy)

                    );

                }

            }

        }

        int answer = 0;

        for (int j = 0; j < N; j++) {

            answer = Math.max(answer, dp[D - 1][j]);

        }

        System.out.println(answer);

    }

}