import java.io.*;
import java.util.*;;

public class Main {
	private static int N, M;
	private static List<int[]>[] arr;
	private static int[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		p = new int[N + 1];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			arr[x].add(new int[] { y, w });
			arr[y].add(new int[] { x, w });
		}
		int firstState = dijkstra(1, -1, -1, true);
		
		if(firstState == -1) {
			System.out.println(-1);
			return;
		}
		
		int answer = 0;
		for (int i = 2; i <= N; i++) {
			int distance = dijkstra(1, i, p[i], false);
			if (distance == -1) {
				answer = -1;
				break;
			}
			answer = Math.max(answer, distance - firstState);
		}
		System.out.println(answer);
	}

	private static final int INF = Integer.MAX_VALUE;

	private static int dijkstra(int start, int node1, int node2, boolean isFirst) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);

		dist[start] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

		pq.add(new int[] { start, 0 });

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			if (dist[curr[0]] != curr[1])
				continue;

			for (int[] next : arr[curr[0]]) {
				if ((curr[0] == node1 && next[0] == node2) || (curr[0] == node2 && next[0] == node1))
					continue;

				if (dist[next[0]] > dist[curr[0]] + next[1]) {
					if (isFirst) {
						p[next[0]] = curr[0];
					}
					dist[next[0]] = dist[curr[0]] + next[1];
					pq.add(new int[] { next[0], dist[next[0]] });
				}
			}
		}
		return dist[N] == INF ? -1 : dist[N];
	}
}
