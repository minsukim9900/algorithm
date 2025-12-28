import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static List<Integer>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

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
		System.out.println(bfs());
	}

	private static int bfs() {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, 100_000_000);
		Queue<Integer> q = new ArrayDeque<>();
		dist[1] = 0;
		q.add(1);

		while (!q.isEmpty()) {
			int curr = q.poll();

			if (dist[curr] == 2)
				continue;

			for (int next : adj[curr]) {
				if (dist[next] > dist[curr] + 1) {
					dist[next] = dist[curr] + 1;
					q.add(next);
				}
			}
		}

		int result = 0;

		for (int x : dist) {
			if (x == 100_000_000 || x == 0)
				continue;
			result++;
		}
		return result;
	}
}