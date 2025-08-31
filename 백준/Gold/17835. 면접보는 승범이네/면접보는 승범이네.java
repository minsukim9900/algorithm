import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static List<int[]>[] arr;
	private static final long INF = Long.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			arr[to].add(new int[] { from, w });
		}

		int[] interviews = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			interviews[i] = Integer.parseInt(st.nextToken());
		}

		long[] dist = multiSourceDijkstra(interviews);

		int city = 1;
		long distance = dist[1];

		for (int i = 2; i <= N; i++) {
			if (dist[i] > distance) {
				city = i;
				distance = dist[i];
			}
		}
		sb.append(city).append("\n").append(distance).append("\n");
		System.out.println(sb.toString());
	}

	private static long[] multiSourceDijkstra(int[] interviews) {
		long[] dist = new long[N + 1];
		Arrays.fill(dist, INF);
		PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

		for (int interview : interviews) {
			dist[interview] = 0L;
			pq.add(new long[] { interview, 0L });
		}

		while (!pq.isEmpty()) {
			long[] curr = pq.poll();
			int index = (int) curr[0];
			long d = curr[1];

			if (d != dist[index]) {
				continue;
			}

			for (int[] next : arr[index]) {
				if (dist[next[0]] > dist[index] + next[1]) {
					dist[next[0]] = dist[index] + next[1];
					pq.add(new long[] { next[0], dist[next[0]] });
				}
			}
		}

		return dist;
	}
}