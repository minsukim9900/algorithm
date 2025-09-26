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
			dp[i] = i * d;
			d *= 2;
		}
		System.out.println(countOne(B) - countOne(A - 1));
	}

	private static long countOne(long x) {
		if (x <= 0)
			return 0;

		int idx = 63 - Long.numberOfLeadingZeros(x);
		long first_One = (1L << (idx));
		long postCount = x - first_One + 1;
		return dp[idx] + postCount + countOne(postCount - 1);
	}
}