import java.io.*;
import java.util.*;

public class Main {
	private static int V, E, P;
	private static List<int[]>[] arr;
	private static final int INF = 100_000_001;
	private static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		arr = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			arr[from].add(new int[] { to, w });
			arr[to].add(new int[] { from, w });
		}

		dijkstra(1);
		int firstEnd = dist[V];
		int stopover = dist[P];
		dijkstra(P);
		int secondEnd = dist[V];
		
		sb.append(firstEnd == stopover + secondEnd ? "SAVE HIM" : "GOOD BYE");
		System.out.println(sb.toString());
	}

	private static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		pq.add(new int[] { start, 0 });

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			if (dist[curr[0]] != curr[1]) {
				continue;
			}

			for (int[] node : arr[curr[0]]) {
				if (dist[node[0]] > dist[curr[0]] + node[1]) {
					dist[node[0]] = dist[curr[0]] + node[1];
					pq.add(new int[] { node[0], dist[node[0]] });
				}
			}
		}
	}
}