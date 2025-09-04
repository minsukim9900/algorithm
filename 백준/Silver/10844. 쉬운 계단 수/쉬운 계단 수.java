import java.io.*;
import java.util.*;;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		long[][] dp = new long[N + 1][10];

		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}

		int MOD = 1_000_000_000;

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				if (j == 0) {
					dp[i][j] = dp[i - 1][1] % MOD;
				} else if (j == 9) {
					dp[i][j] = dp[i - 1][8] % MOD;
				} else {
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
				}
			}
		}

		long result = 0;
		for (int i = 0; i <= 9; i++) {
			result = (result + dp[N][i]) % MOD;
		}
		System.out.println(result);
	}
}
