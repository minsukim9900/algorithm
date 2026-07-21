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

        st = new StringTokenizer(br.readLine());

        nums = new int[N];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;
        }
    }

    private static long cal() {
        Arrays.sort(nums);

        long answer = 0;

        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                long sum = (long) nums[i] + nums[left] + nums[right];

                if (sum < K) {
                    left++;
                } else if (sum > K) {
                    right--;
                } else {
                    if (nums[left] == nums[right]) {
                        long count = right - left + 1L;
                        answer += count * (count - 1) / 2;
                        break;
                    }

                    int leftValue = nums[left];
                    int rightValue = nums[right];
                    long leftCount = 0;
                    long rightCount = 0;

                    while (left < right && nums[left] == leftValue) {
                        left++;
                        leftCount++;
                    }

                    while (left <= right && nums[right] == rightValue) {
                        right--;
                        rightCount++;
                    }

                    answer += leftCount * rightCount;
                }
            }
        }

        return answer;
    }
    public static void main(String[] args) throws Exception {
        init();

        System.out.println(cal());
    }
}