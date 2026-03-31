import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static List<int[]>[] adj;
	private static final int INF = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
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

		int[] info = findParent();

		List<int[]> banInfo = new ArrayList<>();

		int curr = N;
		while (curr != 1) {
			banInfo.add(new int[] {curr, info[curr]});
			curr = info[curr];
		}

		int answer = info[0];

		for (int[] ban : banInfo) {
			answer = Math.max(answer, dijkstra(ban[0], ban[1]));
		}
		System.out.println(answer);
	}

	private static int dijkstra(int ban1, int ban2) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[1] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.add(new int[] { 1, 0 });

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			int node = curr[0];
			int cost = curr[1];

			if (dist[node] != cost) {
				continue;
			}

			if (node == N) {
				return cost;
			}

			for (int[] next : adj[node]) {
				if ((next[0] == ban1 && node == ban2) || (next[0] == ban2 && node == ban1))
					continue;

				if (dist[next[0]] > cost + next[1]) {
					dist[next[0]] = cost + next[1];
					pq.add(new int[] { next[0], dist[next[0]] });
				}
			}
		}

		return -1;
	}

	private static int[] findParent() {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[1] = 0;

		int[] parent = new int[N + 1];

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		pq.add(new int[] { 1, 1, 0 });

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			int node = curr[0];
			int preNode = curr[1];
			int cost = curr[2];

			if (dist[node] != cost) {
				continue;
			}

			parent[node] = preNode;

			for (int[] next : adj[node]) {
				if (dist[next[0]] > cost + next[1]) {
					dist[next[0]] = cost + next[1];
					pq.add(new int[] { next[0], node, dist[next[0]] });
				}
			}
		}

		parent[0] = dist[N];
		return parent;
	}
}