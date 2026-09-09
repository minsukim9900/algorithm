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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			adj = new ArrayList[N + 1];

			for (int node = 1; node < N + 1; node++) {
				adj[node] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				adj[from].add(to);
				adj[to].add(from);
			}

			int answer = 0;
			boolean[] visited = new boolean[N + 1];

			for (int node = 1; node < N + 1; node++) {
				if (visited[node]) {
					continue;
				}

				answer++;
				bfs(node, visited);
			}

			sb.append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void bfs(int start, boolean[] visited) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		visited[start] = true;

		while (!q.isEmpty()) {
			int curr = q.poll();

			for (int next : adj[curr]) {
				if (visited[next]) {
					continue;
				}

				visited[next] = true;
				q.add(next);
			}
		}
	}
}
