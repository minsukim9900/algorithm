import java.io.*;
import java.util.*;

public class Main {
	private static int N, T, M;
	private static List<int[]>[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		arr = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			arr[from].add(new int[] { to, time, cost });
			arr[to].add(new int[] { from, time, cost });
		}
		System.out.println(dijkstra(1));
	}

	private static final int INF = Integer.MAX_VALUE;

	private static int dijkstra(int start) {
		int[][] dist = new int[N + 1][M + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dist[i], INF);
		}

		dist[1][0] = 0;
		dist[1][0] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
		pq.add(new int[] { start, 0, 0 });

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			if (curr[1] != dist[curr[0]][curr[2]])
				continue;

			if (curr[1] > T)
				continue;

			for (int[] next : arr[curr[0]]) {
				if (curr[2] + next[2] > M)
					continue;

				if (curr[1] + next[1] > T)
					continue;

				if (curr[1] + next[1] < dist[next[0]][curr[2] + next[2]]) {
					dist[next[0]][curr[2] + next[2]] = curr[1] + next[1];
					pq.add(new int[] { next[0], curr[1] + next[1], curr[2] + next[2] });
				}
			}

		}
		int answer = INF;
		for (int m = 0; m <= M; m++) {
			if (dist[N][m] <= T) {
				answer = m;
				break;
			}
		}
		return answer == INF ? -1 : answer;
	}
}