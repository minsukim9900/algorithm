import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static String T;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = "#" + br.readLine();
        N = T.length();
    }

    private static int[] getFailure() {
        int[] f = new int[N];

        f[0] = -1;

        for (int i = 1; i < N; i++) {
            int j = f[i - 1];

            while (j >= 0 && T.charAt(j + 1) != T.charAt(i)) {
                j = f[j];
            }

            f[i] = j + 1;
        }

        return f;
    }
    public static void main(String[] args) throws Exception {
        init();

        int[] f = getFailure();

        System.out.println((N - 1) - f[N - 1]);
    }
}