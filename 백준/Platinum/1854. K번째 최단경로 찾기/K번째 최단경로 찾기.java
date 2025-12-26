import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static List<int[]>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj[x].add(new int[] { y, w });
		}

		PriorityQueue<Long>[] dist = dijkstra();
		for (int i = 1; i <= N; i++) {
			if (dist[i].size() < K) {
				sb.append(-1).append("\n");
			} else {
				sb.append(dist[i].poll()).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static PriorityQueue<Long>[] dijkstra() {
		PriorityQueue<Long>[] dist = new PriorityQueue[N + 1];
		for (int i = 1; i <= N; i++) {
			dist[i] = new PriorityQueue<>(Collections.reverseOrder());
		}

		dist[1].add(0L);

		PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
		pq.add(new long[] { 1, 0L });

		while (!pq.isEmpty()) {
			long[] curr = pq.poll();
			int node = (int) curr[0];
			long currCost = curr[1];
			
			if (dist[node].size() == K && dist[node].peek() < currCost) continue;

			for (int[] next : adj[node]) {
				int nextNode = next[0];
				long nextCost = next[1] + currCost;

				if (dist[nextNode].size() < K) {
					dist[nextNode].add(nextCost);
					pq.add(new long[] { nextNode, nextCost });
				} else if (dist[nextNode].peek() > nextCost) {
					dist[nextNode].poll();
					dist[nextNode].add(nextCost);
					pq.add(new long[] { nextNode, nextCost });
				}
			}
		}
		return dist;
	}

}