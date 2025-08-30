import java.io.*;
import java.util.*;

public class Main {
	private static int N, K;
	private static int[][] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				nums[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dist = new int[N][];
		for (int i = 0; i < N; i++) {
			dist[i] = dijkstra(i);
		}

		int[][] dp = new int[N][1 << N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}

		int full = (1 << N) - 1;
		int ans = solve(K, 1 << K, full, dp, dist);
		System.out.println(ans);
	}

	private static int solve(int u, int mask, int full, int[][] dp, int[][] dist) {
		if (mask == full)
			return 0;
		if (dp[u][mask] != -1)
			return dp[u][mask];

		int best = Integer.MAX_VALUE;
		for (int v = 0; v < N; v++) {
			if ((mask & (1 << v)) != 0)
				continue;
			int cand = dist[u][v] + solve(v, mask | (1 << v), full, dp, dist);
			best = Math.min(cand, best);
		}
		return dp[u][mask] = best;
	}

	private static int[] dijkstra(int start) {
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pq.add(new int[] { start, dist[start] });

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			if (dist[curr[0]] != curr[1]) {
				continue;
			}

			for (int i = 0; i < N; i++) {
				if (curr[0] == i)
					continue;

				if (dist[i] > dist[curr[0]] + nums[curr[0]][i]) {
					dist[i] = dist[curr[0]] + nums[curr[0]][i];
					pq.add(new int[] { i, dist[i] });
				}
			}
		}
		return dist;
	}

}