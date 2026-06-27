import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] numbers, reverseNumbers;

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        reverseNumbers = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            numbers[i] = num;
            reverseNumbers[N - 1 - i] = num;
        }
    }

    private static int increaseBinarySearch(int s, int e, int target, int[] dp) {
        int result = 0;

        while(s <= e) {
            int mid = (s + e) / 2;

            if(target <= dp[mid]) {
                result = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return result;
    }

    private static int[] getIncreaseOrder(int[] nums) {
        int[] order = new int[N];
        int[] dp = new int[N];
        dp[0] = nums[0];
        order[0] = 1;
        int idx = 1;

        for(int i = 1; i < N; i++) {
            int num = nums[i];

            if(num > dp[idx - 1]) {
                order[i] = idx + 1;
                dp[idx++] = num;
            } else {
                int pos = increaseBinarySearch(0, idx - 1, num, dp);
                dp[pos] = num;
                order[i] = pos + 1;
            }
        }

        return order;
    }

    public static void main(String[] args) throws Exception {
        init();
        int[] increaseOrder = getIncreaseOrder(numbers);
        int[] decreaseOrder = getIncreaseOrder(reverseNumbers);

        int answer = 0;
        for(int i = 0; i < N; i++) {
            int length = increaseOrder[i] + decreaseOrder[N - 1 - i];

            answer = Math.max(answer, length - 1);
        }

        System.out.println(answer);
    }
}