import java.io.*;
import java.util.*;

public class Main {

	private static int V, E, R;
	private static List<Integer>[] adj;
	private static int[] result;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		adj = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}

		result = new int[V+1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adj[from].add(to);
			adj[to].add(from);
		}

		for (int i = 1; i <= V; i++) {
			Collections.sort(adj[i], Collections.reverseOrder());
		}

		bfs(R);
		for(int i = 1; i<=V; i++) {
			sb.append(result[i]).append("\n");
		}
		
		System.out.println(sb.toString());

	}

	private static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		int cnt = 1;
		q.add(start);
		result[R] = 1;
		
		while (!q.isEmpty()) {
			int curr = q.poll();

			for (int w : adj[curr]) {
				if (result[w] == 0) {
					result[w] = ++cnt;
					q.add(w);
				}
			}
		}

	}
}