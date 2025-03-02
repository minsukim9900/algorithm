import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, X;
	private static List<int[]>[] adj;
	private static int[] dist, result;
	private static final int INF = 987654321;
	private static int[][] heap;
	private static int size;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		result = new int[N + 1];
		adj = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {

			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj[from].add(new int[] { to, w });

		}

		for (int i = 1; i <= N; i++) {
			if (i == X)
				continue;

			dist = new int[N + 1];
			Arrays.fill(dist, INF);
			heap = new int[M + 1][2];
			size = 0;
			dijkstra(i, X, 0);
			Arrays.fill(dist, INF);
			size = 0;
			dijkstra(X, i, 1);
			
		}
		
		
		int ans = 0;
		for(int s : result) {
			ans = Math.max(ans, s);
		}
		
		System.out.println(ans);

	}

	private static void dijkstra(int start, int end, int state) {

		dist[start] = 0;
		boolean[] visited = new boolean[N + 1];

		push(new int[] { start, dist[start] });

		while (size > 0) {

			int[] curr = poll();

			if (visited[curr[0]])
				continue;

			visited[curr[0]] = true;

			for (int[] w : adj[curr[0]]) {

				if (dist[w[0]] > dist[curr[0]] + w[1]) {
					dist[w[0]] = dist[curr[0]] + w[1];
					push(new int[] { w[0], dist[w[0]] });
				}

			}

		}
		if (state == 0)
		result[start] += dist[end];
		else
		result[end] += dist[end];

	}

	private static void push(int[] data) {

		heap[++size] = data;

		int p = size / 2;
		int c = size;

		while (c != 1 && heap[p][1] > heap[c][1]) {
			swap(p, c);

			c = p;
			p = c / 2;
		}
	}

	private static int[] poll() {
		int[] arr = heap[1];

		heap[1] = heap[size--];

		int p = 1;
		int c = p * 2;

		if (c + 1 <= size && heap[c + 1][1] < heap[c][1]) {
			c++;
		}

		while (c <= size && heap[p][1] > heap[c][1]) {

			swap(p, c);

			p = c;
			c = p * 2;

			if (c + 1 <= size && heap[c + 1][1] < heap[c][1]) {
				c++;
			}
		}

		return arr;
	}

	private static void swap(int p, int c) {
		int[] tmp = heap[p];
		heap[p] = heap[c];
		heap[c] = tmp;
	}

}
