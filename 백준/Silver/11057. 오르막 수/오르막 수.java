import java.io.*;
import java.util.*;

public class Main {
	private static final int MOD = 10_007;
	private static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N + 1][10];

		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k <= j; k++) {
					dp[i][j] += dp[i - 1][k];
				}
				dp[i][j] %= MOD;
			}
		}
		
		long answer = 0;
		for(int i = 0; i < 10; i++) {
			answer += dp[N][i];
		}
		System.out.println(answer % MOD);
	}
}