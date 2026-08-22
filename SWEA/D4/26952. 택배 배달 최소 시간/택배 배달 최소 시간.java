import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	private static int N, E;

	private static List<int[]>[] adj;

	private static final int INF = 1_000_000_001;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			adj = new ArrayList[N + 1];

			for (int node = 0; node < N + 1; node++) {
				adj[node] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());

				int nodeX = Integer.parseInt(st.nextToken());
				int nodeY = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				adj[nodeX].add(new int[] { nodeY, cost });
			}

			sb.append("#").append(t).append(" ").append(dijkstra()).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static int dijkstra() {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[0] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.add(new int[] { 0, 0 });

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int currNode = curr[0];
			int d = curr[1];

			if (d != dist[currNode]) {
				continue;
			}

			for (int[] next : adj[currNode]) {
				int nextNode = next[0];
				int nd = d + next[1];

				if (dist[nextNode] > nd) {
					dist[nextNode] = nd;
					pq.add(new int[] { nextNode, nd });
				}
			}
		}

		return dist[N];
	}
}
