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
	private static int N, M;
	private static List<Integer>[] adj;

	private static final int MAX_NODE = 101;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		final int T = 10;

		for (int t = 1; t < T + 1; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			adj = new ArrayList[MAX_NODE];

			for (int node = 1; node < MAX_NODE; node++) {
				adj[node] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				adj[from].add(to);
			}

			sb.append(bfs()).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static int bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[MAX_NODE];

		visited[M] = true;

		q.add(M);

		while (!q.isEmpty()) {
			int size = q.size();
			int maxNode = 0;

			for (int i = 0; i < size; i++) {
				int curr = q.poll();

				maxNode = Math.max(maxNode, curr);

				for (int next : adj[curr]) {
					if (visited[next]) {
						continue;
					}

					visited[next] = true;
					q.add(next);
				}
			}

			if (q.isEmpty()) {
				return maxNode;
			}
		}

		return 1;
	}
}
