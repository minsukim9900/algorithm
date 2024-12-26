import java.io.*;
import java.util.*;

public class Main {

	private static class Node {
		int x, y, w;

		private Node(int x, int y, int w) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
		}

	}

	private static class PqFormat implements Comparable<PqFormat> {

		int idx, dist;

		private PqFormat(int idx, int dist) {
			super();
			this.idx = idx;
			this.dist = dist;
		}

		public int compareTo(PqFormat o) {
			return Integer.compare(this.dist, o.dist);
		}

	}

	private static int[] dist;
	private static boolean[] visited;
	private static List<Node>[] adj;
	private static int V, E;
	private static final int INF = 200000000;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adj = new ArrayList[V + 1];

		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj[x].add(new Node(x, y, w));
			adj[y].add(new Node(y, x, w));
		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		dist = new int[V + 1];
		visited = new boolean[V + 1];

		int sum1 = 0;
		sum1 += dijkstra(1, v1);
		sum1 += dijkstra(v1, v2);
		sum1 += dijkstra(v2, V);

		int sum2 = 0;
		sum2 += dijkstra(1, v2);
		sum2 += dijkstra(v2, v1);
		sum2 += dijkstra(v1, V);

		int result = 0;
		if (dist[v1] >= INF || dist[v2] >= INF || dist[V] >= INF) {
			result = -1;
		} else {
			result = Math.min(sum1, sum2);
		}

		System.out.println(result);
	}

	private static int dijkstra(int start, int end) {
		Arrays.fill(dist, INF);
		Arrays.fill(visited, false);

		dist[start] = 0;

		PriorityQueue<PqFormat> pq = new PriorityQueue<>();
		pq.offer(new PqFormat(start, 0));

		while (!pq.isEmpty()) {
			PqFormat curr = pq.poll();

			if (visited[curr.idx])
				continue;

			visited[curr.idx] = true;

			for (Node now : adj[curr.idx]) {

				if (dist[now.y] > dist[curr.idx] + now.w) {
					dist[now.y] = dist[curr.idx] + now.w;
					pq.offer(new PqFormat(now.y, dist[now.y]));
				}

			}

		}

		return dist[end];

	}

}