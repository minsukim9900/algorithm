import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static String T, P;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        T = "#" + br.readLine();
        P = br.readLine();

        P = P + P.substring(0, N - 1);
        P = "#" + P;
    }

    private static int[] getFailure() {
        int[] f = new int[T.length()];

        f[0] = -1;

        for(int i = 1; i < T.length(); i++) {
            int j = f[i - 1];

            while (j >= 0 && T.charAt(j + 1) != T.charAt(i)) {
                j = f[j];
            }

            f[i] = j + 1;
        }

        return f;
    }

    private static int cal(int[] f) {
        int answer = 0;
        int j = 0;

        for (int i = 1; i < P.length(); i++) {
            
            while (j >= 0 && T.charAt(j + 1) != P.charAt(i)) {
                j = f[j];
            }

            j++;

            if (j == T.length() - 1) {
                answer++;
                j = f[j];
            }
        }
        return answer;
    }
    public static void main(String[] args) throws Exception {
        init();

        int[] f = getFailure();

        System.out.println(cal(f));
    }
}