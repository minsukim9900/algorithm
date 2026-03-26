import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, R;
	private static List<Integer>[] adj;
	private static int[] result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		result = new int[N + 1];
		Arrays.fill(result, -1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adj[from].add(to);
			adj[to].add(from);
		}

		for (int i = 1; i < N + 1; i++) {
			adj[i].sort((a, b) -> Integer.compare(a, b));
		}

		dfs(R, new boolean[N + 1], 0);

		for (int i = 1; i < N + 1; i++) {
			sb.append(result[i]).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void dfs(int node, boolean[] visited, int depth) {
		result[node] = depth;
		visited[node] = true;

		for (int next : adj[node]) {
			if (visited[next]) {
				continue;
			}

			dfs(next, visited, depth + 1);
		}
	}
}