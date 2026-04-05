import java.io.*;
import java.util.*;

public class Main {
	private static int V, E, M, S, x, y;
	private static List<int[]>[] adj;
	private static final int INF = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adj = new ArrayList[V + 1];
		for (int i = 1; i < V + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj[from].add(new int[] { to, w });
			adj[to].add(new int[] { from, w });
		}

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		int[] buggers = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			buggers[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		int[] coffees = new int[S];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < S; i++) {
			coffees[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(dijkstra(buggers, coffees));
	}

	private static int dijkstra(int[] buggers, int[] coffees) {
		int[][] dist = new int[2][V + 1];

		for (int i = 0; i < 2; i++) {
			Arrays.fill(dist[i], INF);
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

		for (int i = 0; i < M; i++) {
			dist[0][buggers[i]] = 0;
			pq.add(new int[] { buggers[i], 0, 0 });
		}

		for (int i = 0; i < S; i++) {
			dist[1][coffees[i]] = 0;
			pq.add(new int[] { coffees[i], 0, 1 });
		}

		int answer = INF;

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			int node = curr[0];
			int weight = curr[1];
			int idx = curr[2];

			if (dist[idx][node] != weight) {
				continue;
			}

			if (isEnd(node, dist)) {
				answer = Math.min(answer, dist[idx][node] + dist[idx ^ 1][node]);
				continue;
			}

			for (int[] next : adj[node]) {
				if (dist[idx][next[0]] > weight + next[1]) {
					dist[idx][next[0]] = weight + next[1];
					pq.add(new int[] { next[0], dist[idx][next[0]], idx });
				}
			}
		}

		return answer == INF ? -1 : answer;
	}

	private static boolean isEnd(int node, int[][] dist) {
		return dist[0][node] != 0 && dist[1][node] != 0 && dist[0][node] != INF && dist[1][node] != INF
				&& dist[0][node] <= x && dist[1][node] <= y;
	}
}