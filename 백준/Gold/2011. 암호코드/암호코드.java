import java.io.*;
import java.util.*;;

public class Main {
	private static final int MOD = 1_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		String str = br.readLine();

		int[] dp = new int[str.length() + 1];
		dp[0] = 1;
		char pre = '0';

		if (str.charAt(0) == '0') {
			dp[str.length()] = 0;
		} else {
			for (int i = 1; i <= str.length(); i++) {
				char c = str.charAt(i - 1);

				if (c == '0') {
					if (pre == '1' || pre == '2') {
						dp[i] = dp[i - 2];
					}
				} else {
					if (pre == '1' || (pre == '2' && c <= '6')) {
						dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
					} else {
						dp[i] = dp[i - 1];
					}
				}

				pre = c;
			}
		}
		System.out.println(dp[str.length()]);
	}
}