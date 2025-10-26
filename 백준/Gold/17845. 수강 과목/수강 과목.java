import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] dp = new int[N + 1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int score = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			for (int t = N; t >= time; t--) {
				dp[t] = Math.max(dp[t], dp[t - time] + score);
			}
		}
		System.out.println(dp[N]);
	}
}
