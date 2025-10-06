import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static List<Integer>[] adj;
	private static int[] cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cnt = new int[N + 1];

		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adj[y].add(x);
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			cnt[i] = bfs(i);
			max = Math.max(max, cnt[i]);
		}

		for (int i = 1; i <= N; i++) {
			if (max == cnt[i]) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb.toString());
	}

	private static int bfs(int node) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(node);
		int result = 0;
		boolean[] visited = new boolean[N + 1];
		visited[node] = true;

		while (!q.isEmpty()) {
			int curr = q.poll();
			result++;

			for (int w : adj[curr]) {
				if (visited[w])
					continue;
				visited[w] = true;
				q.add(w);
			}
		}
		return result;
	}
}
