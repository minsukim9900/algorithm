import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] nums, orders;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        orders = new int[M];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            nums[i] = num;
        }

        Arrays.sort(nums);

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int order = Integer.parseInt(st.nextToken());

            orders[i] = order;
        }
    }

    private static String cal() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int target = orders[i];

            int result = lowerBound(target);

            sb.append(result).append("\n");
        }

        return sb.toString();
    }

    private static int lowerBound(int target) {
        int s = 0;
        int e = N - 1;
        int answer = N - 1;

        while (s <= e) {
            int mid = (s + e) >> 1;

            if (nums[mid] >= target) {
                answer = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return nums[answer] == target ? (answer + 1) : -1;
    }

    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}