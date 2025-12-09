import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		long[][] dp = new long[N][21];

		dp[0][nums[0]] = 1;

		for (int i = 1; i < N - 1; i++) {
			int num = nums[i];

			for (int v = 0; v <= 20; v++) {
				if (dp[i - 1][v] == 0)
					continue;

				int plus = v + num;
				int minus = v - num;

				if (check(plus)) {
					dp[i][plus] += dp[i - 1][v];
				}

				if (check(minus)) {
					dp[i][minus] += dp[i - 1][v];
				}
			}
		}

		System.out.println(dp[N - 2][nums[N - 1]]);

	}

	private static boolean check(int number) {
		return number >= 0 && number <= 20;
	}
}