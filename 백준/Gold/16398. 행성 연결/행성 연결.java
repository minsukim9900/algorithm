import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static boolean[] visited;

	private static class Edge implements Comparable<Edge> {
		int x, y, w;

		private Edge(int x, int y, int w) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}

	}

	private static List<Edge>[] adj;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N];

		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (i != j) {
					adj[i].add(new Edge(i, j, tmp));
					adj[j].add(new Edge(j, i, tmp));
				}
			}
		}

		System.out.println(prim(0));

	}

	private static long prim(int st) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		visited[st] = true;
		pq.addAll(adj[st]);
		long ans = 0;
		int pick = 1;

		while (pick != N) {
			Edge e = pq.poll();
			if (visited[e.y])
				continue;

			visited[e.y] = true;
			ans += e.w;
			pick++;
			pq.addAll(adj[e.y]);
		}

		return ans;
	}

}
