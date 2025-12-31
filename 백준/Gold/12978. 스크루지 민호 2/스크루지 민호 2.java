import java.io.*;
import java.util.*;

public class Main {
	private static int N, answer;
	private static List<Integer>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
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

		dfs(1, new boolean[N + 1]);
		System.out.println(answer);
	}

	private static int dfs(int node, boolean[] visited) {
		visited[node] = true;

		int childCount = 0;
		int policeCount = 0;

		for (int next : adj[node]) {
			if (visited[next])
				continue;
			childCount++;
			policeCount += dfs(next, visited);
		}

		int state = childCount == policeCount ? 0 : 1;
		answer += state;
		return state;
	}
}