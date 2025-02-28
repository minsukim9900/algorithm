import java.io.*;
import java.util.*;

public class Main {

	private static int N, K;
	private static int[][] items;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		items = new int[N][2];
		for (int i = 0; i < N; i++) {

			st = new StringTokenizer(br.readLine());

			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());

		}


		int[] dp = new int[K + 1];

		for (int i = 0; i < N; i++) {

			int weight = items[i][0];
			int cost = items[i][1];

			for (int j = K; j >= weight; j--) {
				dp[j] = Math.max(dp[j - weight] + cost, dp[j]);
			}
			
		}
		
		
		System.out.println(dp[K]);

	}
}
