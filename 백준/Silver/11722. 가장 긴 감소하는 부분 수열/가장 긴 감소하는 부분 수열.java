import java.io.*;
import java.util.*;;

public class Main {
	private static int[] nums, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = -Integer.parseInt(st.nextToken());
		}

		dp = new int[N];
		int idx = 1;
		dp[0] = nums[0];

		for (int i = 1; i < N; i++) {
			if (nums[i] > dp[idx - 1]) {
				dp[idx++] = nums[i];
			} else {
				int sidx = binarySerach(0, idx, nums[i]);
				dp[sidx] = nums[i];
			}
		}
		System.out.println(idx);
	}

	private static int binarySerach(int s, int e, int target) {
		int answer = 0;
		while (s <= e) {
			int mid = (s + e) / 2;

			if (dp[mid] < target) {
				s = mid + 1;
			} else {
				answer = mid;
				e = mid - 1;
			}
		}
		return answer;
	}
}