import java.io.*;
import java.util.*;;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] nums = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			sb.append(cal(M, nums)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int cal(int M, int[] nums) {
		int[] dp = new int[M + 1];
		dp[0] = 1;
		for (int i = 0; i < nums.length; i++) {
			for (int j = nums[i]; j <= M; j++) {
				dp[j] = dp[j] + dp[j - nums[i]];
			}
		}
		return dp[M];
	}
}
