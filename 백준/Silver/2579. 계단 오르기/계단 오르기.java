import java.io.*;
import java.util.*;;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		int[] dp = new int[N + 1];
		dp[1] = nums[1];
		if (N > 1) {
			dp[2] = nums[1] + nums[2];

			for (int i = 3; i <= N; i++) {
				dp[i] = Math.max(dp[i - 2], nums[i - 1] + dp[i - 3]) + nums[i];
			}
		}
		System.out.println(dp[N]);
	}
}
