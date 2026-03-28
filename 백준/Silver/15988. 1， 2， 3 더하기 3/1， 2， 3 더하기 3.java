import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static final int MAX = 1_000_001;
	private static final int MOD = 1_000_000_009;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		long[] dp = new long[MAX];

		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int i = 4; i < MAX; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;
		}

		for (int i = 0; i < N; i++) {
			sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}