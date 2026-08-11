import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] nums;

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
    }

    private static int cal() {
        int left = 0;
        int right = 0;
        int result = 0;
        int sum = nums[left];

        while (right < N) {

            if (sum > M) {
                sum -= nums[left];
                left++;
            } else if (sum == M) {
                result++;
                sum -= nums[left];

                left++;
                right++;

                if (right == N) {
                    break;
                }

                sum += nums[right];
            } else {
                right++;

                if (right == N) {
                    break;
                }

                sum += nums[right];
            }
        }

        return result;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}