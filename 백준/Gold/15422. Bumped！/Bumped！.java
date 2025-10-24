import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, F, S, T;
	private static List<int[]>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[x].add(new int[] { y, w });
			adj[y].add(new int[] { x, w });
		}

		int[] flight = new int[F + 1];
		flight[0] = S;

		for (int i = 1; i <= F; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = 0;
			adj[x].add(new int[] {y, w});
			flight[i] = x;
		}
		System.out.println(dijkstra(flight));
	}

	private static long dijkstra(int[] flight) {
		PriorityQueue<long[]> pq = new PriorityQueue<>(
				(a, b) -> Long.compare(a[1], b[1]));
		long[][] dist = new long[F + 1][N];
		for (int i = 0; i <= F; i++) {
			Arrays.fill(dist[i], Long.MAX_VALUE);
			dist[i][S] = 0;
			pq.add(new long[] { S, dist[i][S], i });
		}

		while (!pq.isEmpty()) {
			long[] curr = pq.poll();
			int node = (int) curr[0];
			long d = curr[1];
			int idx = (int) curr[2];

			if (dist[idx][node] != d)
				continue;

			if (node == T)
				return d;

			for (int[] next : adj[node]) {
				if (next[1] == 0) {
					if (node == flight[idx] && dist[idx][next[0]] > d + next[1]) {
						dist[idx][next[0]] = d + next[1];
						pq.add(new long[] { next[0], dist[idx][next[0]], idx });
					}
				} else {
					if (dist[idx][next[0]] > d + next[1]) {
						dist[idx][next[0]] = d + next[1];
						pq.add(new long[] { next[0], dist[idx][next[0]], idx });
					}
				}
			}
		}

		return -1;
	}
}
