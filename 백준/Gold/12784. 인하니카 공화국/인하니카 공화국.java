import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static List<int[]>[] adj;
	private static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
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

			int answer = dfs(1, INF, new boolean[N + 1]);
			sb.append(answer == INF ? 0 : answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int dfs(int node, int weight, boolean[] visited) {
		visited[node] = true;

		int cost = 0;
		for (int[] next : adj[node]) {
			if (visited[next[0]])
				continue;

			cost += dfs(next[0], next[1], visited);
		}

		return cost == 0 ? weight : Math.min(weight, cost);
	}
}