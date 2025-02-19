import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, V;
	private static List<Integer>[] adj;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		visited = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adj[x].add(y);
			adj[y].add(x);
		}
		
		for(int i = 1; i<=N; i++) {
			Collections.sort(adj[i]);
		}

		dfs(V);
		visited = new boolean[N + 1];
		System.out.println();
		bfs(V);

	}

	private static void dfs(int st) {
		visited[st] = true;
		System.out.print(st + " ");

		for (int w : adj[st]) {
			if (!visited[w]) {
				dfs(w);
			}
		}
	}

	private static void bfs(int st) {
		Queue<Integer> q = new ArrayDeque<>();
		visited[st] = true;
		q.offer(st);

		while (!q.isEmpty()) {
			int curr = q.poll();
			System.out.print(curr+ " ");

			for (int w : adj[curr]) {
				if (!visited[w]) {
					visited[w] = true;
					q.offer(w);
				}
			}
		}
	}

}
