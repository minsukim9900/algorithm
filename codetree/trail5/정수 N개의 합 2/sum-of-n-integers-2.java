import java.io.*;
import java.util.*;

public class Main {
    private static int N, K;
    private static int[] nums;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i < N + 1; i++) {
            int num = Integer.parseInt(st.nextToken());

            nums[i] = num;
        }
    }

    private static int cal() {
        int[] prefix = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            prefix[i] = nums[i] + prefix[i - 1];
        }

        int answer = 0;

        for (int i = K; i < N + 1; i++) {
            answer = Math.max(answer, prefix[i] - prefix[i - K]);
        }

        return answer;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}