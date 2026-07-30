import java.io.*;
import java.util.*;

public class Main {
    private static int N, K, B;
    private static int[] nums, prefix;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        nums = new int[N + 1];

        for (int i = 0; i < B; i++) {
            int num = Integer.parseInt(br.readLine());

            nums[num] = 1;
        }

        prefix = new int[N + 1];

        for (int num = 1; num < N + 1; num++) {
            prefix[num] = prefix[num - 1] + nums[num];
        }
    }

    private static int cal() {
        int answer = B;

        for (int start = 1; start <= N - K + 1; start++) {
            int end = start + K - 1;

            int result = prefix[end] - prefix[start - 1];

            answer = Math.min(answer, result);
        }

        return answer;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}