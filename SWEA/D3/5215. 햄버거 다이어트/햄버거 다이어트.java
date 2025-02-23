import java.io.*;
import java.util.*;

public class Solution {

	private static int N, L;
	private static int[] dp;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			dp = new int[L + 1];

			int[][] items = new int[N + 1][2];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				items[i][0] = Integer.parseInt(st.nextToken());
				items[i][1] = Integer.parseInt(st.nextToken());

			}

			for (int i = 1; i <= N; i++) {
				for (int j = L; j >= items[i][1]; j--) {

					dp[j] = Math.max(dp[j], items[i][0] + dp[j - items[i][1]]);

				}
			}

			sb.append("#" + t + " ").append(dp[L]).append("\n");

		}
		System.out.println(sb.toString());

	}

}
