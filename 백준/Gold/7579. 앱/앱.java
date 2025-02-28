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

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			items[i][0] = Integer.parseInt(st.nextToken());

		}

		st = new StringTokenizer(br.readLine());
		int total = 0;
		for (int i = 0; i < N; i++) {
			items[i][1] = Integer.parseInt(st.nextToken());
			total += items[i][1];
		}

		int[] dp = new int[total + 1];

		for (int i = 0; i < N; i++) {

			for (int j = total; j >= items[i][1]; j--) {
				dp[j] = Math.max(dp[j], dp[j - items[i][1]] + items[i][0]);
			}

		}
		
		int ans = 0;
		for (int i = 0; i <= total; i++) {
			if(dp[i] >= K) {
				ans = i;
				break;
			}
		}
		
		System.out.println(ans);

	}
}
