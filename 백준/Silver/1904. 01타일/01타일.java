import java.io.*;
import java.util.*;;

public class Main {
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		long[] dp = new long[1_000_001];

		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i = 3; i <= 1_000_000; i++)	 {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
		}
		System.out.println(dp[N]);
	}
}
