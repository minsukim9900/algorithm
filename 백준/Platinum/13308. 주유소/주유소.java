import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, maxOil;
	private static int[] oil;
	private static final long INF = Long.MAX_VALUE;
	private static List<int[]>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		oil = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		maxOil = 0;

		for (int i = 1; i < N + 1; i++) {
			oil[i] = Integer.parseInt(st.nextToken());
			maxOil = Math.max(oil[i], maxOil);
		}

		adj = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adj[x].add(new int[] { y, weight });
			adj[y].add(new int[] { x, weight });
		}

		System.out.println(dijkstra());
	}

	private static long dijkstra() {
		long[][] dist = new long[N + 1][maxOil + 1];
		for (int i = 1; i < N + 1; i++) {
			Arrays.fill(dist[i], INF);
		}

		dist[1][oil[1]] = 0;

		PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[2], b[2]));

		pq.add(new long[] { 1, oil[1], 0 });

		while (!pq.isEmpty()) {
			long[] curr = pq.poll();
			int node = (int) curr[0];
			int currOil = (int) curr[1];
			long currCost = curr[2];

			if (node == N) {
				return currCost;
			}

			for (int[] next : adj[node]) {
				int nextNode = next[0];
				int nextWeight = next[1];

				long nextCost = (currOil * nextWeight) + currCost;

				if (dist[nextNode][currOil] > nextCost) {
					dist[nextNode][currOil] = nextCost;
					int nextOil = Math.min(oil[nextNode], currOil);
					pq.add(new long[] { nextNode, nextOil, dist[nextNode][currOil] });
				}
			}
		}
		return -1;
	}
}