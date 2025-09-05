import java.io.*;
import java.util.*;;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		long[] dp = new long[91];
		dp[1] = 1L;
		dp[2] = 1L;
		for (int i = 3; i <= 90; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		System.out.println(dp[N]);
	}
}
