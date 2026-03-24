import java.io.*;
import java.util.*;

public class Main {
	private static int N, P, K;
	private static List<int[]>[] adj;
	private static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		int max = 0;
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			max = Math.max(max, weight);

			adj[from].add(new int[] { to, weight });
			adj[to].add(new int[] { from, weight });
		}
		System.out.println(binarySearch(max));
	}

	private static int binarySearch(int max) {
		int s = 0;
		int e = max;

		int answer = -1;
		while (s <= e) {
			int mid = (s + e) / 2;

			if (dijkstra(mid)) {
				answer = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}

		return answer;
	}

	private static boolean dijkstra(int value) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[1] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.add(new int[] { 1, 0 });

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			int node = curr[0];
			int currCount = curr[1];

			if (dist[node] < currCount) {
				continue;
			}

			for (int[] next : adj[node]) {
				int nextNode = next[0];
				int nextWeight = next[1];
				int nextCount = currCount;

				if (nextWeight > value) {
					nextCount++;
				}

				if (dist[nextNode] > nextCount) {
					dist[nextNode] = nextCount;
					pq.add(new int[] { nextNode, nextCount });
				}
			}
		}

		return dist[N] <= K;
	}
}