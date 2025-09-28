import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K, X;
	private static List<Integer>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adj[from].add(to);
		}

		List<Integer> answer = bfs();
		if (answer.size() == 0) {
			System.out.println(-1);
			return;
		} else {
			Collections.sort(answer);
			for (Integer num : answer) {
				sb.append(num).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static List<Integer> bfs() {
		boolean[] visited = new boolean[N + 1];
		visited[X] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { X, 0 });
		List<Integer> result = new ArrayList<>();
		while (!q.isEmpty()) {
			int[] curr = q.poll();

			if (curr[1] == K) {
				result.add(curr[0]);
			}

			for (Integer next : adj[curr[0]]) {
				if (!visited[next]) {
					visited[next] = true;
					q.add(new int[] { next, curr[1] + 1 });
				}
			}
		}

		return result;
	}
}