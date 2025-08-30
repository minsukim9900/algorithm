import java.io.*;
import java.util.*;

public class Main {
	private static int N, K, answer;
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

		answer = Integer.MAX_VALUE;
		boolean[] visited = new boolean[N];
		visited[K] = true;
		dfs(K, 0, 0, visited, dist);
		System.out.println(answer);
	}

	private static void dfs(int k, int sum, int depth, boolean[] visited, int[][] dist) {
		if (depth == N - 1) {
			answer = Math.min(answer, sum);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i] || i == k)
				continue;

			visited[i] = true;
			dfs(i, sum + dist[k][i], depth + 1, visited, dist);
			visited[i] = false;
		}
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