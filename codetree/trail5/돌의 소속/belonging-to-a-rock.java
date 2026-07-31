import java.io.*;
import java.util.*;

public class Main {
    private static int N, Q;
    private static int[][] nums, prefix, orders;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        nums = new int[4][N + 1];
        prefix = new int[4][N + 1];

        for (int num = 1; num < N + 1; num++) {
            int group = Integer.parseInt(br.readLine());

            nums[group][num] = 1;
        }

        for (int group = 1; group < 4; group++) {
            for (int num = 1; num < N + 1; num++) {
                prefix[group][num] = prefix[group][num - 1] + nums[group][num];
            }
        }

        orders = new int[Q][2];

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            orders[i][0] = start;
            orders[i][1] = end;
        }
    }

    private static String cal() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            int start = orders[i][0];
            int end = orders[i][1];

            for (int group = 1; group < 4; group++) {
                int result = prefix[group][end] - prefix[group][start - 1];

                sb.append(result).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}