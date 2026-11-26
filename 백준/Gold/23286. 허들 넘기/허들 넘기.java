import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, T;

	private static List<int[]>[] adj;

	private static final int INF = 1_000_000_000;

	private static int[][] dijkstra() {
		int[][] dist = new int[N + 1][N + 1];

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

		for (int node = 1; node < N + 1; node++) {
			Arrays.fill(dist[node], INF);

			dist[node][node] = 0;

			pq.add(new int[] { node, 0, node });
		}

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			int node = curr[0];
			int d = curr[1];
			int idx = curr[2];

			if (dist[idx][node] != d) {
				continue;
			}

			for (int[] next : adj[node]) {
				int nextD = Math.max(d, next[1]);

				if (dist[idx][next[0]] > nextD) {
					dist[idx][next[0]] = nextD;

					pq.add(new int[] { next[0], nextD, idx });
				}
			}
		}

		return dist;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];

		for (int node = 1; node < N + 1; node++) {
			adj[node] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int nodeX = Integer.parseInt(st.nextToken());
			int nodeY = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());

			adj[nodeX].add(new int[] { nodeY, height });
		}

		int[][] dist = dijkstra();

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			int result = dist[from][to];

			result = result == INF ? -1 : result;
			sb.append(result).append("\n");
		}

		System.out.println(sb.toString());
	}
}
