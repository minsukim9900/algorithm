import java.io.*;
import java.util.*;

public class Main {
	private static long[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());

		dp = new long[56];
		long d = 1L;
		for (int i = 1; i <= 55; i++) {
			dp[i] = (dp[i - 1] << 1) + (1L << (i - 1));
		}
		System.out.println(countOne(B) - countOne(A - 1));
	}

	private static long countOne(long x) {
		if (x <= 0)
			return 0;

		long temp = Long.highestOneBit(x);
		int idx = 63 - Long.numberOfLeadingZeros(x);
		long preCount = dp[idx];
		long postCount = x - temp + 1;
		return preCount + postCount + countOne(postCount - 1);
	}
}