import java.io.*;
import java.util.*;;

public class Main {
	private static int N;
	private static int[] nums;
	private static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		nums = new int[N];

		dp = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int idx = 0;
		dp[idx++] = nums[0];

		for (int i = 1; i < N; i++) {
			if (dp[idx - 1] < nums[i]) {
				dp[idx++] = nums[i];
			} else {
				int sidx = binarySearch(0, idx, nums[i]);
				dp[sidx] = nums[i];
			}
		}
		System.out.println(idx);
	}

	private static int binarySearch(int s, int e, int target) {
		while (s <= e) {
			int mid = (s + e) / 2;
			
			if(dp[mid] < target) {
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		return s;
	}
}