import java.io.*;
import java.util.*;

public class Main {
	private static int N, S;
	private static final int MAX = 20_000_001;
	private static int[] amount;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		amount = new int[MAX];
		int max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			max = Math.max(max, l);
			amount[l] = Math.max(amount[l], p);
		}

		int[] dp = new int[max + 1];
		for (int i = 1; i < max + 1; i++) {
			dp[i] = Math.max(dp[Math.max(0, i - S)] + amount[i], dp[i - 1]);
		}
		
		System.out.println(dp[max]);
	}

}