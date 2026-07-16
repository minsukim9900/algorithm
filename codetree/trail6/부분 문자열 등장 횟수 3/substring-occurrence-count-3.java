import java.io.*;
import java.util.*;

public class Main {
    private static String T, P;
    private static int N, M;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = "#" + br.readLine();
        P = "#" + br.readLine();
        N = T.length();
        M = P.length();
    }

    private static int[] getFailure() {
        int[] f = new int[M];
        f[0] = -1;

        for (int i = 1; i < M; i++) {
            int j = f[i - 1];

            while (j >= 0 && P.charAt(j + 1) != P.charAt(i)) {
                j = f[j];
            }

            f[i] = j + 1;
        }

        return f;
    }

    private static int cal(int[] f) {
        int result = 0;
        int j = 0;

        for (int i = 1; i < N; i++) {

            while(j >= 0 && P.charAt(j + 1) != T.charAt(i)) {
                j = f[j];
            }
            
            j++;

            if (j == M - 1) {
                result++;
                j = 0;
            }
        }

        return result;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal(getFailure()));
    }
}
