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

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            nums[i] = num;
        }

        orders = new int[M];

        for (int i = 0; i < M; i++) {
            orders[i] = Integer.parseInt(br.readLine());
        }
    }

    private static int upperBound(int target) {
        int s = 0;
        int e = N - 1;
        int answer = N;

        while (s <= e) {
            int mid = (s + e) / 2;

            if (nums[mid] > target) {
                answer = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return answer;
    }

    private static int lowerBound(int target) {
        int s = 0;
        int e = N - 1;
        int answer = N;

        while (s <= e) {
            int mid = (s + e) / 2;

            if (nums[mid] >= target) {
                answer = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return answer;
    }

    private static String cal() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < M; i++) {
            int target = orders[i];

            int count = upperBound(target) - lowerBound(target);

            sb.append(count).append("\n");
        }

        return sb.toString();
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}