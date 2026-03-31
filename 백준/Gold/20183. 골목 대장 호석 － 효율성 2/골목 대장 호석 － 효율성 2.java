import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, A, B;
	private static long C;
	private static final long INF = Long.MAX_VALUE;

	private static List<int[]>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Long.parseLong(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		int max = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			max = Math.max(max, w);

			adj[x].add(new int[] { y, w });
			adj[y].add(new int[] { x, w });
		}
		
		System.out.println(binarySearch(max));
	}

	private static int binarySearch(int max) {
		int s = 0;
		int e = max;
		int answer = -1;

		while (s <= e) {
			int mid = (s + e) / 2;

			if (dijksta(mid)) {
				answer = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}

		return answer;
	}

	private static boolean dijksta(int value) {
		long[] dist = new long[N + 1];
		Arrays.fill(dist, INF);

		dist[A] = 0;

		PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
		pq.add(new long[] { A, 0, 0 });

		while (!pq.isEmpty()) {
			long[] curr = pq.poll();
			int idx = (int) curr[0];
			long cost = curr[1];
			int maxValue = 0;

			if (dist[idx] != cost || cost > C) {
				continue;
			}

			if (idx == B) {
				return true;
			}

			for (int[] next : adj[idx]) {
				if (next[1] > value)
					continue;

				if (dist[next[0]] > cost + next[1]) {
					dist[next[0]] = cost + next[1];
					pq.add(new long[] { next[0], dist[next[0]] });
				}
			}
		}

		return false;
	}
}