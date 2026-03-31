import java.io.*;
import java.util.*;

public class Main {
	private static int K, N, R;
	private static List<int[]>[] adj;
	private static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		R = Integer.parseInt(br.readLine());

		adj = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adj[from].add(new int[] { to, time, cost });
		}

		System.out.println(dijkstra());
	}

	private static int dijkstra() {
		int[][] dist = new int[K + 1][N + 1];
		for (int i = 0; i < K + 1; i++) {
			Arrays.fill(dist[i], INF);
		}

		dist[0][1] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>(
				(a, b) -> a[1] == b[1] ? Integer.compare(a[2], b[2]) : Integer.compare(a[1], b[1]));
		pq.add(new int[] { 1, 0, 0 });

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			int node = curr[0];
			int time = curr[1];
			int cost = curr[2];

			if (dist[cost][node] != time) {
				continue;
			}

			for (int[] next : adj[node]) {
				int nextNode = next[0];
				int nextTime = next[1] + time;
				int nextCost = next[2] + cost;

				if (nextCost > K)
					continue;

				if (dist[nextCost][nextNode] > nextTime) {
					dist[nextCost][nextNode] = nextTime;
					pq.add(new int[] { nextNode, nextTime, nextCost });
				}
			}

		}

		int answer = INF;
		for (int cost = 0; cost < K + 1; cost++) {
			answer = Math.min(answer, dist[cost][N]);
		}

		return answer == INF ? -1 : answer;
	}
}