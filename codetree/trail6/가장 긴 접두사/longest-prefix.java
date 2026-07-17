import java.io.*;
import java.util.*;

public class Main {
    private static String T, P;
    private static int N;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        P = br.readLine();
        T = sb.append(P).reverse().toString();
        T = "#" + T;
        P = "#" + P;

        N = T.length();
    }

    private static int[] getFailure() {
        int[] f = new int[N];
        f[0] = -1;

        for (int i = 1; i < N; i++) {
            int j = f[i - 1];

            while (j >= 0 && P.charAt(j + 1) != P.charAt(i)) {
                j = f[j];
            }

            f[i] = j + 1;
        }

        return f;
    }

    private static int KMP(int[] f) {
        int result = 0;

        int j = 0;

        for (int i = 1; i < N; i++) {
            while (j >= 0 && P.charAt(j + 1) != T.charAt(i)) {
                j = f[j];
            }

            j++;

            result = Math.max(result, j);

            if (j == N - 1) {
                return j;
            }
        }

        return result;
    }
    public static void main(String[] args) throws Exception {
        init();
        int[] f = getFailure();

        System.out.println(KMP(f));
    }
}