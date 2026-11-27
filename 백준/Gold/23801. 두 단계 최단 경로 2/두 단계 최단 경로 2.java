import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static List<int[]>[] adj;

	private static final long INF = Long.MAX_VALUE;

	private static long[] dijkstra(int start) {
		PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

		long[] dist = new long[N + 1];
		Arrays.fill(dist, INF);

		dist[start] = 0;

		pq.add(new long[] { start, 0 });

		while (!pq.isEmpty()) {
			long[] curr = pq.poll();

			int node = (int) curr[0];
			long d = curr[1];

			if (dist[node] != d) {
				continue;
			}

			for (int[] next : adj[node]) {
				if (dist[next[0]] > d + next[1]) {
					dist[next[0]] = d + next[1];
					pq.add(new long[] { next[0], d + next[1] });
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

		adj = new ArrayList[N + 1];

		for (int node = 1; node < N + 1; node++) {
			adj[node] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int nodeX = Integer.parseInt(st.nextToken());
			int nodeY = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adj[nodeX].add(new int[] { nodeY, weight });
			adj[nodeY].add(new int[] { nodeX, weight });
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		long[] distStart = dijkstra(start);
		long[] distEnd = dijkstra(end);

		long answer = INF;

		int P = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < P; i++) {
			int p = Integer.parseInt(st.nextToken());

			if (distStart[p] == INF || distEnd[p] == INF) {
				continue;
			}

			answer = Math.min(answer, distStart[p] + distEnd[p]);
		}

		System.out.println(answer == INF ? -1 : answer);
	}
}
