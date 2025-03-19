import java.io.*;
import java.util.*;

import javax.lang.model.type.UnionType;

public class Main {
	private static int N, M;
	private static ArrayList<int[]>[] arr;
	private static long[] dist, cost;
	private static int[] p;
	private static int[] childCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new ArrayList[N + 1];
		childCnt = new int[N + 1];
		dist = new long[N + 1];
		cost = new long[N + 1];
		p = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

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
		
		ArrayList<Integer>[] adj = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 2; i<=N; i++) {
			adj[p[i]].add(i);
		}
		
		dfs(1, new boolean[N +1], adj);
		
		long sum = 0;

		for (int i = 2; i <= N; i++) {
			long cal = (long) (cost[i] * childCnt[i]) - ((cost[i] / 10) * (childCnt[i] - 1));
			sum += cal;
		}
		System.out.println(sum);
	}
	
	private static int dfs(int v, boolean[] visited, ArrayList<Integer>[] adj) {
		int cnt = 1;
		visited[v] = true;
		
		for(int w : adj[v]) {
			if(visited[w]) continue;
			cnt += dfs(w, visited, adj);
		}
		
		childCnt[v] = cnt;
		
		return cnt;
		
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

		pq.add(new long[] { 1, 0, 0, 1 });

		while (!pq.isEmpty()) {
			long[] curr = pq.poll();
			int idx = (int) curr[0];
			if (visited[idx])
				continue;

			visited[idx] = true;

			for (int[] w : arr[idx]) {
				if (dist[w[0]] > dist[idx] + w[1]) {
					dist[w[0]] = dist[idx] + w[1];
					union(idx, w[0]);
					cost[w[0]] = (long) w[1];
					pq.offer(new long[] { w[0], dist[w[0]], w[1], idx });
				} else if (dist[w[0]] == dist[idx] + w[1] && cost[w[0]] > (long) w[1]) {
						union(idx, w[0]);
						cost[w[0]] = (long) w[1];
				}
			}
		}
		

	}
	
	private static void union(int x, int y) {
		p[y] = x;
	}

}