import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static String T, P;
    private static final String PREFIX = "#";

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = br.readLine();
        P = br.readLine();
        N = T.length();
        M = P.length();
    }

    private static int[] getFailure() {
        T = PREFIX + T;
        P = PREFIX + P;

        int[] failure = new int[M + 1];

        failure[0] = -1;
        for (int i = 1; i < M + 1; i++) {
            int j = failure[i - 1];

            while (j >= 0 && P.charAt(j + 1) != P.charAt(i)) {
                j = failure[j];
            }

            failure[i] = j + 1;
        }
        return failure;
    }

    private static int cal(int[] failure) {
        int j = 0;
        int result = 0;

        for (int i = 1; i < N + 1; i++) {
            while (j >= 0 && P.charAt(j + 1) != T.charAt(i)) {
                j = failure[j];
            }

            j++;

            if (j == M) {
                result++;
                j = failure[j];
            }
        }


        return result;
    }
    public static void main(String[] args) throws Exception {
        init();

        int[] failure = getFailure();

        System.out.println(cal(failure));
    }
}