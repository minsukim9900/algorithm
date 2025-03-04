import java.io.*;
import java.util.*;

public class Main {

	private static long N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int pisano = 1_500_000;
		N = Long.parseLong(br.readLine()) % pisano;
		long[] dp = new long[pisano + 1];

		if (N == 0 || N ==1)
			System.out.println(N);
		else {
			dp[1] = 1;

			for (int i = 2; i <= pisano; i++) {
				dp[i] = (dp[i - 1] + dp[i - 2]) % 1_000_000;
			}
			System.out.println(dp[(int)N]);
		}
		

	}

}
