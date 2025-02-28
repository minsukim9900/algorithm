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
		for (int i = 0; i < N; i++) {
			items[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(items, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});

		int[] dp = new int[K + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 1; i <= Math.min(K, items[0][0]); i++) {
			dp[i] = items[0][1];
		}

		int pre = items[0][0];

		for (int i = 1; i < N; i++) {

			int w = items[i][0];
			if (w > K)
				w = K;
			int c = items[i][1];
			pre += w;
			if (pre > K)
				pre = K;

			for (int j = pre; j >= w; j--) {
				dp[j] = Math.min(dp[j], dp[j - w] + c);
			}

			for (int j = 1; j <= w; j++) {
				dp[j] = Math.min(dp[j], c);
			}
			

		}

		System.out.println(dp[K]);

	}
}
