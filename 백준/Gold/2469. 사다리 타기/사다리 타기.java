import java.io.*;
import java.util.*;

public class Main {
    static int K, N;
    static String target;
    static String[] ladder;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine().trim());
        N = Integer.parseInt(br.readLine().trim());
        target = br.readLine().trim();

        ladder = new String[N];
        int qRow = -1;

        for (int i = 0; i < N; i++) {
            ladder[i] = br.readLine().trim();
            if (ladder[i].charAt(0) == '?') qRow = i;
        }

        char[] up = new char[K];
        for (int i = 0; i < K; i++) up[i] = (char) ('A' + i);

        for (int r = 0; r < qRow; r++) {
            applyRow(up, ladder[r]);
        }

        char[] down = target.toCharArray();
        for (int r = N - 1; r > qRow; r--) {
            applyRow(down, ladder[r]);
        }

        char[] ans = new char[K - 1];
        Arrays.fill(ans, '*');

        boolean fail = false;

        for (int i = 0; i < K - 1; i++) {
            if (up[i] == down[i]) {
                ans[i] = '*';
            } else {
                if (i + 1 < K && up[i] == down[i + 1] && up[i + 1] == down[i]) {
                    if (i > 0 && ans[i - 1] == '-') {
                        fail = true;
                        break;
                    }
                    ans[i] = '-';
                    char t = up[i];
                    up[i] = up[i + 1];
                    up[i + 1] = t;
                } else {
                    fail = true;
                    break;
                }
            }
        }

        if (fail) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < K - 1; i++) sb.append('x');
            System.out.print(sb.toString());
        } else {
            System.out.print(new String(ans));
        }
    }

    static void applyRow(char[] arr, String row) {
        for (int j = 0; j < K - 1; j++) {
            if (row.charAt(j) == '-') {
                char tmp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = tmp;
            }
        }
    }
}