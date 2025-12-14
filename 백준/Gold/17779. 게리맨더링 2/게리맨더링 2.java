import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] A;
    static int totalSum = 0;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        A = new int[N + 1][N + 1];

        for (int r = 1; r <= N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
                totalSum += A[r][c];
            }
        }

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 <= N; d1++) {
                    for (int d2 = 1; d2 <= N; d2++) {

                        if (x + d1 + d2 > N) continue;
                        if (y - d1 < 1) continue;
                        if (y + d2 > N) continue;

                        answer = Math.min(answer, calcDiff(x, y, d1, d2));
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static int calcDiff(int x, int y, int d1, int d2) {
        int[][] border = new int[N + 1][N + 1];
        int[] sum = new int[6];

        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = 5;
            border[x + d2 + i][y + d2 - i] = 5;
        }
        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = 5;
            border[x + d1 + i][y - d1 + i] = 5;
        }

        for (int r = 1; r < x + d1; r++) {
            for (int c = 1; c <= y; c++) {
                if (border[r][c] == 5) break;
                sum[1] += A[r][c];
            }
        }

        for (int r = 1; r <= x + d2; r++) {
            for (int c = N; c > y; c--) {
                if (border[r][c] == 5) break;
                sum[2] += A[r][c];
            }
        }

        for (int r = x + d1; r <= N; r++) {
            for (int c = 1; c < y - d1 + d2; c++) {
                if (border[r][c] == 5) break;
                sum[3] += A[r][c];
            }
        }

        for (int r = x + d2 + 1; r <= N; r++) {
            for (int c = N; c >= y - d1 + d2; c--) {
                if (border[r][c] == 5) break;
                sum[4] += A[r][c];
            }
        }

        sum[5] = totalSum - (sum[1] + sum[2] + sum[3] + sum[4]);

        int max = 0, min = Integer.MAX_VALUE;
        for (int i = 1; i <= 5; i++) {
            max = Math.max(max, sum[i]);
            min = Math.min(min, sum[i]);
        }
        return max - min;
    }
}