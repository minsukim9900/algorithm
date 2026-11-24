import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, A, B;
	private static List<int[]>[] adj;

	private static final long INF = 1L << 62;

	private static long[][] dijkstra() {
		long[][] dist = new long[2][N + 1];

		for (int i = 0; i < 2; i++) {
			Arrays.fill(dist[i], INF);
		}

		dist[0][A] = 0;
		dist[1][B] = 0;

		PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

		pq.add(new long[] { A, 0, 0 });
		pq.add(new long[] { B, 0, 1 });

		while (!pq.isEmpty()) {
			long[] curr = pq.poll();

			int node = (int) curr[0];
			long d = curr[1];
			int idx = (int) curr[2];

			if (dist[idx][node] != d) {
				continue;
			}

			for (int[] next : adj[node]) {
				if (dist[idx][next[0]] > d + next[1]) {
					dist[idx][next[0]] = d + next[1];

					pq.add(new long[] { next[0], d + next[1], idx });
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
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];

		for (int city = 1; city < N + 1; city++) {
			adj[city] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int cityX = Integer.parseInt(st.nextToken());
			int cityY = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adj[cityX].add(new int[] { cityY, weight });
			adj[cityY].add(new int[] { cityX, weight });
		}

		long[][] dist = dijkstra();

		long len = dist[0][B];

		List<Integer> answer = new ArrayList<>();

		for (int city = 1; city < N + 1; city++) {

			long distCityFromA = dist[0][city];
			long distCityFromB = dist[1][city];

			if (distCityFromA + distCityFromB == len) {
				answer.add(city);
			}
		}

		sb.append(answer.size()).append("\n");

		for (int city : answer) {
			sb.append(city).append(" ");
		}

		System.out.println(sb.toString());
	}
}
