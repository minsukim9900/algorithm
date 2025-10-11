import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];
		Arrays.fill(dp, 100_000);
		for (int i = 0; i <= N; i += 5) {
			dp[i] = i / 5;
		}

		for (int i = 2; i <= N; i++) {
			dp[i] = Math.min(dp[i - 2] + 1, dp[i]);
		}
		
		System.out.println(dp[N] == 100_000 ? -1 : dp[N]);
	}
}
