import java.io.*;
import java.util.*;

public class Main {
    private static int N, Q;
    private static int[] nums, prefix;
    private static int[][] orders;
    
    private static final int MAX = 1_000_002;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        nums = new int[MAX];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken()) + 1;

            nums[num] = 1;
        }

        orders = new int[Q][2];

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) + 1;
            int end = Integer.parseInt(st.nextToken()) + 1;

            orders[i][0] = start;
            orders[i][1] = end;
        }

        prefix = new int[MAX];

        for (int num = 1; num < MAX; num++) {
            prefix[num] = prefix[num - 1] + nums[num];
        }

    }

    private static String cal() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            int start = orders[i][0];
            int end = orders[i][1];

            int result = prefix[end] - prefix[start - 1];

            sb.append(result).append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}