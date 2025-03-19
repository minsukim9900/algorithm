import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static ArrayList<int[]>[] arr;
	private static long[] dist;
	private static int[] cost;
	private static long[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N + 1];
		dist = new long[N + 1];
		cost = new int[N + 1];
		result = new long[N + 1];

		Arrays.fill(dist, Long.MAX_VALUE);

		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			arr[from].add(new int[] { to, w });
			arr[to].add(new int[] { from, w });
		}
		
		dijkstra();
		
		long sum = 0;
		
		for(long w : result) {
			sum += w;
		}
		System.out.println(sum);
	}

	private static void dijkstra() {
		boolean[] visited = new boolean[N + 1];
		dist[1] = 0;

		PriorityQueue<long[]> pq = new PriorityQueue<>(new Comparator<long[]>() {

			@Override
			public int compare(long[] o1, long[] o2) {

				return Long.compare(o1[1], o2[1]);
			}
		});

		pq.add(new long[] { 1, 0 });

		while (!pq.isEmpty()) {
			long[] curr = pq.poll();
			int idx = (int) curr[0];
			
			if (visited[idx])
				continue;

			visited[idx] = true;

			for (int[] w : arr[idx]) {
				if (dist[w[0]] > dist[idx] + w[1]) {
					dist[w[0]] = dist[idx] + w[1];
					result[w[0]] = (long) (dist[idx] * 0.9) + w[1];

					pq.add(new long[] { w[0], dist[w[0]] });

				} else if (dist[w[0]] == dist[idx] + w[1]) {
					dist[w[0]] = dist[idx] + w[1];
					result[w[0]] = Math.min(result[w[0]],(long) (dist[idx] * 0.9) + w[1]);
				}
			}
		}
	}
}
