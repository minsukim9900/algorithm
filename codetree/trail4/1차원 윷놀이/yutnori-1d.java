import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, K, answer;
    private static int[] nums, counts;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        answer = 0;

        nums = new int[N];
        counts = new int[K + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            nums[i] = num;
        }

        dfs(0);
        System.out.println(answer);
    }

    private static void dfs(int depth) {
        if (depth == N) {
            int count = 0;

            for (int i = 1; i < K + 1; i++) {
                if (counts[i] >= M - 1) {
                    count++;
                }
            }

            answer = Math.max(answer, count);
            return;
        }

        for (int i = 1; i < K + 1; i++) {
            counts[i] += nums[depth];
            dfs(depth + 1);
            counts[i] -= nums[depth];
        }
    }
}