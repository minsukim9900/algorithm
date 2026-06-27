import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;
        }

        int[] dp = new int[N];
        dp[0] = nums[N - 1];
        int idx = 1;

        for(int i = N - 2; i >= 0; i--) {
            if(dp[idx - 1] < nums[i]) {
                dp[idx++] = nums[i];
            } else {
                int sIdx = binarySearch(0, idx - 1, nums[i], dp);
                dp[sIdx] = nums[i];
            }
        }

        System.out.println(idx);
    }

    private static int binarySearch(int s, int e, int target, int[] dp) {
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
}