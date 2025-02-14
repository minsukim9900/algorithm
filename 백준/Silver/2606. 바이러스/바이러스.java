import java.io.*;
import java.util.*;

public class Main {

	private static List<Integer>[] adj;
	private static boolean[] visited;
	private static int cnt = -1;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adj[from].add(to);
			adj[to].add(from);

		}

		dfs(1);
		System.out.println(cnt);

	}

	private static void dfs(int num) {
		visited[num] = true;
		cnt++;

		for (int w : adj[num]) {
			if (!visited[w])
				dfs(w);
		}

	}

}
