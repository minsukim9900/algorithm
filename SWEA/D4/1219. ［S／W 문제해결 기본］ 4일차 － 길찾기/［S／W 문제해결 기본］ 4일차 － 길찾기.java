import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static int N;
	private static List<Integer>[] adj;

	private static final int A = 0;
	private static final int B = 99;
	private static final int MAX_NODE = 100;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		final int T = 10;

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());

			int testCase = Integer.parseInt(st.nextToken());

			sb.append("#").append(testCase).append(" ");

			N = Integer.parseInt(st.nextToken());

			adj = new ArrayList[MAX_NODE];

			for (int node = 0; node < MAX_NODE; node++) {
				adj[node] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				adj[from].add(to);
			}

			sb.append(bfs()).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static int bfs() {
		boolean[] visited = new boolean[MAX_NODE];
		visited[A] = true;

		Queue<Integer> q = new ArrayDeque<>();
		q.add(A);

		while (!q.isEmpty()) {
			int curr = q.poll();

			if (curr == B) {
				return 1;
			}

			for (int next : adj[curr]) {
				if (visited[next]) {
					continue;
				}

				visited[next] = true;
				q.add(next);
			}
		}

		return 0;
	}
}
