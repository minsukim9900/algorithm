import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
	private static int N, M;
	private static TreeSet<Integer>[] adj;

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

			adj = new TreeSet[MAX_NODE];

			for (int node = 1; node < MAX_NODE; node++) {
				adj[node] = new TreeSet<>();
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
		Queue<int[]> q = new ArrayDeque<>();
		boolean[] visited = new boolean[MAX_NODE];

		visited[M] = true;

		q.add(new int[] { M, 0 });

		int result = 0;
		int d = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int node = curr[0];
			int depth = curr[1];

			if (d < depth || (d == depth && node > result)) {
				result = node;
				d = depth;
			}

			for (int next : adj[node]) {
				if (visited[next]) {
					continue;
				}

				visited[next] = true;
				q.add(new int[] { next, depth + 1 });
			}
		}

		return result;
	}
}
