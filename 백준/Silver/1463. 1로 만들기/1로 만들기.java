import java.io.*;
import java.util.*;

public class Main {

	private static int[] dp;
	private static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);

		dp[0] = 0;
		dp[1] = 0;

		for (int i = 2; i <= N; i++) {
			
			dp[i] = dp[i-1] +1;
			
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i/2] + 1, dp[i]);
			}

			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i/3] + 1, dp[i]);
			}
			
			
		}

		System.out.println(dp[N]);

	}

}
