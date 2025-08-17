import java.io.*;
import java.util.*;

public class Main {
	private static int N, D, C;
	private static List<int[]>[] arr;
	private static final int INF = 100_000_001;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			arr = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				arr[i] = new ArrayList<>();
			}

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				arr[from].add(new int[] { to, w });
			}

			int[] result = dijkstra(C);
			int max = 0;
			int count = 0;
			for(int w : result) {
				if(w == INF) continue;
				count++;
				max = Math.max(max, w);
			}
			
			sb.append(count).append(" ").append(max).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int[] dijkstra(int start) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

		pq.add(new int[] { start, 0 });

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			if (curr[1] != dist[curr[0]]) {
				continue;
			}
			for (int[] node : arr[curr[0]]) {
				if (dist[node[0]] > dist[curr[0]] + node[1]) {
					dist[node[0]] = dist[curr[0]] + node[1];
					pq.add(new int[] { node[0], dist[node[0]] });
				}
			}
		}
		return dist;
	}
}