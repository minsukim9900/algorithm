import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int start, end;
	private static List<Integer>[] adj;
	private static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			adj[x].add(y);
			adj[y].add(x);
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		while (!q.isEmpty()) {
			int node = q.poll();

			if (node == end) {
				return dist[node];
			}

			for (int next : adj[node]) {
				if (dist[next] > dist[node] + 1) {
					dist[next] = dist[node] + 1;
					q.add(next);
				}
			}
		}
		return -1;
	}
}