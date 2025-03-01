import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] items;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		items = new int[N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
			items[i][2] = Integer.parseInt(st.nextToken());
		}

		Map<Integer, int[]> map = new HashMap<>();
		ArrayList<int[]> arr = new ArrayList<>();

		for (int[] w : items) {

			int v = w[0];
			int c = w[1];
			int k = w[2];

			for (int i = 1; k > 0; i *= 2) {
				int cnt = Math.min(i, k);
				arr.add(new int[] { v * cnt, c * cnt });
				k -= cnt;
			}

		}

		int[] dp = new int[M + 1];

		for (int i = 0; i < arr.size(); i++) {

			int[] curr = arr.get(i);
			int w = curr[0];
			int c = curr[1];

			for (int j = M; j >= w; j--) {
				dp[j] = Math.max(dp[j], dp[j - w] + c);
			}

		}

		System.out.println(dp[M]);
	}
}
