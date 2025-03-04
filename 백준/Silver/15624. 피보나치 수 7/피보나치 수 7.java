import java.io.*;
import java.util.*;

public class Main {

	private static int N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];

		if (N == 0)
			System.out.println(1);
		else {
			dp[1] = 1;

			for (int i = 2; i <= N; i++) {
				dp[i] = (dp[i - 1] + dp[i - 2]) % 1_000_000_007;
			}
		}
		
		System.out.println(dp[N]);

	}

}
