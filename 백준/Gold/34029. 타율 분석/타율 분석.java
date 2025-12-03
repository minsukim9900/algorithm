import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double A = Double.parseDouble(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        double p = A;
        double q = 1.0 - p;
        double alpha = 0.05;
        double EPS = 1e-12;

        if (q < EPS) {
            System.out.println(B);
            return;
        }

        double pk = Math.pow(q, B);
        double cum = pk;

        if (cum + EPS >= alpha) {
            System.out.println(0);
            return;
        }

        int answer = B;
        for (int k = 0; k < B; k++) {
            pk = pk * (B - k) / (k + 1) * (p / q);
            cum += pk;

            if (cum + EPS >= alpha) {
                answer = k + 1;
                break;
            }
        }

        System.out.println(answer);
    }
}