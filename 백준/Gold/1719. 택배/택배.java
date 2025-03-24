import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static ArrayList<int[]>[] arr;
	private static int[][] memo;
	private static final int INF = 987654321;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		memo = new int[N + 1][N + 1];
		arr = new ArrayList[N + 1];
		int[][] map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(map[i], INF);
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

		for (int i = 1; i <= N; i++) {
			dijkstra(i);
		}
		
		output();

	}
	
	private static void output() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if(memo[i][j] == 0) {
					sb.append("-" + " ");
				}else {
					sb.append(memo[i][j] + " ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dijkstra(int s) {
		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);

		dist[s] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});

		pq.add(new int[] { s, dist[s], s });

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			if (visited[curr[0]])
				continue;

			visited[curr[0]] = true;
			
			for (int[] w : arr[curr[0]]) {
				
				if (dist[w[0]] > dist[curr[0]] + w[1]) {
					dist[w[0]] = dist[curr[0]] + w[1];
					
					if (curr[2] == s) {
						memo[s][w[0]] = w[0];
						pq.offer(new int[] { w[0], dist[w[0]], w[0] });
					} else {
						memo[s][w[0]] = curr[2];
						pq.offer(new int[] { w[0], dist[w[0]], curr[2] });
					}
					
				}
			}
		}
	}

}