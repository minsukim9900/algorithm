import java.io.*;
import java.util.*;

public class Main {
    private static String T;
    private static String[] P; 
    private static int q;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        T = "#" + st.nextToken();
        q = Integer.parseInt(st.nextToken());

        P = new String[q];
        for (int i = 0; i < q; i++) {
            P[i] = "#" + br.readLine();
        }
    }

    private static int[] getFailure(String s) {
        int[] f = new int[s.length()];
        f[0] = -1;

        for (int i = 1; i < s.length(); i++) {
            int j = f[i - 1];

            while (j >= 0 && s.charAt(j + 1) != s.charAt(i)) {
                j = f[j];
            }

            f[i] = j + 1;
        }
        
        return f;
    }

    private static int cal(String p, int[] f) {
        int j = 0;
        int result = 0;

        for (int i = 1; i < T.length(); i++) {
            while (j >= 0 && p.charAt(j + 1) != T.charAt(i)) {
                j = f[j];
            }

            j++;

            if (j == p.length() - 1) {
                result++;
                j = f[j];
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();

        init();

        for (String p : P) {
            int[] f = getFailure(p);

            sb.append(cal(p, f)).append("\n");
        }

        System.out.println(sb.toString());
    }
}