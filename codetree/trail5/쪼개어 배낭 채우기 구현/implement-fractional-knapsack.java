import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static double[][] items;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        items = new double[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            double w = Integer.parseInt(st.nextToken());
            double v = Integer.parseInt(st.nextToken());

            items[i][0] = w;
            items[i][1] = v;
        }

        Arrays.sort(items, (a, b) -> Double.compare((b[1] / b[0]), (a[1] / a[0])));
    }

    private static String cal() {
        double result = 0.000;

        int remain = M;

       out: for (int i = 0; i < N; i++) {
            double[] curr = items[i];
            double weight = curr[0];
            double value = curr[1];
            double temp = value / weight;

            int w = Math.min(remain, (int) weight);

            result += (w * temp);
            remain -= w;
        }


        return String.format("%.3f", result);
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}