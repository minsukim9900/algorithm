import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        dp[0] = 1;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

        if (nums[0] > 0) {
            pq.add(new int[] {dp[0], nums[0]});
        }

        int answer = 1;

        for (int i = 1; i < N; i++) {
            while (!pq.isEmpty() && pq.peek()[1] < i) {
                pq.poll();
            }

            if (!pq.isEmpty()) {
                dp[i] = pq.peek()[0] + 1;
                answer = Math.max(answer, dp[i]);

                if (nums[i] > 0) {
                    pq.add(new int[] {dp[i], i + nums[i]});
                }
            }
        }

        System.out.println(answer - 1);
    }
}