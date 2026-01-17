import java.io.*;
import java.util.*;

public class Main {
	private static int V, E;
	private static List<Integer>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int K = Integer.parseInt(br.readLine());

		for (int k = 0; k < K; k++) {
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
				adj[x].add(y);
				adj[y].add(x);
			}

			sb.append(bfs() ? "YES" : "NO").append("\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean bfs() {
		int[] color = new int[V + 1];

		Queue<Integer> q = new ArrayDeque<>();

		for (int start = 1; start <= V; start++) {
			if (color[start] != 0)
				continue;

			color[start] = 1;
			q.add(start);

			while (!q.isEmpty()) {
				int node = q.poll();
				for (int next : adj[node]) {
					if (color[next] == 0) {
						color[next] = -color[node];
						q.add(next);
					} else if (color[next] == color[node]) {
						return false;
					}
				}
			}
		}
		return true;
	}
}