import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, A, B, C;
	private static List<int[]>[] adj;
	private static Set<String> visited;
	private static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		visited = new HashSet<>();
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
		System.out.println(dijkstra(A, B));
	}

	private static int dijkstra(int start, int end) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.add(new int[] { start, 0, 0 });

		int answer = INF;
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			
			int node = curr[0];
			int d = curr[1];
			int w = curr[2];
			
			if (node == end) {
				answer = Math.min(answer, d);
				continue;
			}

			if (w >= C) {
				continue;
			}


			for (int[] next : adj[node]) {
				String x = String.valueOf(node);
				String y = String.valueOf(next[0]);

				if (visited.contains(x + y))
					continue;

				if (w + next[1] > C)
					continue;

				check(node, next[0]);
				int nd = Math.max(d, next[1]);
				pq.add(new int[] { next[0], nd, w + next[1] });
			}
		}

		return answer == INF ? -1 : answer;
	}

	private static void check(int node1, int node2) {
		visited.add(String.valueOf(node1) + String.valueOf(node2));
		visited.add(String.valueOf(node2) + String.valueOf(node1));
	}
}