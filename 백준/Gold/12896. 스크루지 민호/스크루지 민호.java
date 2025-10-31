import java.io.*;
import java.util.*;

public class Main {
	private static int N, maxLength, targetNode;
	private static List<Integer>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adj[x].add(y);
			adj[y].add(x);
		}

		dfs(1, 0, new boolean[N + 1]);
		dfs(targetNode, 0, new boolean[N + 1]);
		System.out.println((maxLength + 1) / 2);
	}

	private static void dfs(int node, int length, boolean[] visited) {
		visited[node] = true;

		if (maxLength < length) {
			maxLength = length;
			targetNode = node;
		}

		for (int next : adj[node]) {
			if (visited[next])
				continue;

			dfs(next, length + 1, visited);
		}
	}
}