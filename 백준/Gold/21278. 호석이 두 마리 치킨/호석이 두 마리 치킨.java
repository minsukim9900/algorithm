import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static List<Integer>[] adj;
	private static final int INF = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

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

			adj[x].add(y);
			adj[y].add(x);
		}
		int minX = INF;
		int minY = INF;
		int minDist = INF;
		for (int x = 1; x <= N; x++) {
			for (int y = x + 1; y <= N; y++) {
				int dist = bfs(x, y);
				if (minDist > dist) {
					minDist = dist;
					minX = x;
					minY = y;
				}
			}
		}
		
		System.out.println(minX + " " + minY + " " + minDist);
	}

	private static int bfs(int x, int y) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[0] = 0;
		Queue<Integer> q = new ArrayDeque<>();
		dist[x] = 0;
		dist[y] = 0;
		q.add(x);
		q.add(y);

		while (!q.isEmpty()) {
			int curr = q.poll();

			for (int next : adj[curr]) {
				if (dist[next] > dist[curr] + 2) {
					dist[next] = dist[curr] + 2;
					dist[0] += dist[next];
					q.add(next);
				}
			}
		}
		return dist[0];
	}
}