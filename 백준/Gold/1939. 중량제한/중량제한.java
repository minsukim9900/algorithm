import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static List<int[]>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
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

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		System.out.println(dijkstra(start, end));
	}

	private static int dijkstra(int start, int end) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, -1);

		dist[start] = 1_000_000_001;

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		pq.add(new int[] { start, dist[start] });
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int node = curr[0];
			int weight = curr[1];

			if (dist[node] != weight)
				continue;

			for (int[] next : adj[node]) {
				int nw = Math.min(next[1], dist[node]);
				if (dist[next[0]] < nw) {
					dist[next[0]] = nw;
					pq.add(new int[] { next[0], dist[next[0]] });
				}
			}
		}
		return dist[end];
	}
}
