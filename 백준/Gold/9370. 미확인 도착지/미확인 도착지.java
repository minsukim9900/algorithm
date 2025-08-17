import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, T;
	private static List<int[]>[] arr;
	private static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int test = Integer.parseInt(br.readLine());

		for (int t = 1; t <= test; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int pointX = Integer.parseInt(st.nextToken());
			int pointY = Integer.parseInt(st.nextToken());

			arr = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				arr[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken()) << 1;
				if ((from == pointX && to == pointY) || (from == pointY && to == pointX)) {
					w--;
				}
				arr[from].add(new int[] { to, w });
				arr[to].add(new int[] { from, w });
			}

			int[] result = dijkstra(start);

			TreeSet<Integer> answer = new TreeSet<>();
			for (int i = 0; i < T; i++) {
				int end = Integer.parseInt(br.readLine());
				if (result[end] % 2 == 1) {
					answer.add(end);
				}
			}

			for (int ans : answer) {
				sb.append(ans).append(" ");
			}
			sb.append("\n");
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
		return dist;
	}
}