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
        int right = N - 1;

        for (int left = 0; left < N - 1; left++) {
            int sum = nums[left];

            while (right > 0 && right > left && nums[left] + nums[right] > K) {
                right--;
            }

            if (nums[left] + nums[right] <= K) {
                    result += right - left;
            } else {
                break;
            }
        }

        return result;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}