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

        nums = new int[N];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            nums[i] = num;
        }

        Arrays.sort(nums);
    }

    private static int cal() {
        int result = 0;

        for (int i = 0; i < N - 1; i++) {
            int sum = nums[i];

            if (sum >= K) {
                break;
            }


            for (int j = i + 1; j < N; j++) {
                sum += nums[j];

                if (sum <= K) {
                    result++;
                }

                if (sum > K) {
                    break;
                }

                sum -= nums[j];
            }
        }

        return result;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}