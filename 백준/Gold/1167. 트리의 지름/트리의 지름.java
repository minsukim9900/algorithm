import java.io.*;
import java.util.*;

public class Main {

	private static int V;

	private static class Node {
		int to, w;

		private Node(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}

	}

	private static List<Node>[] adj;
	private static int maxNode = 0;
	private static int maxDist = 0;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		V = Integer.parseInt(br.readLine());

		adj = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			while (true) {
				int to = Integer.parseInt(st.nextToken());

				if (to == -1)
					break;

				int w = Integer.parseInt(st.nextToken());

				adj[from].add(new Node(to, w));
				adj[to].add(new Node(from, w));

			}
		}

		visited = new boolean[V + 1];
		dfs(1, 0);
		
		visited = new boolean[V + 1];
		dfs(maxNode, 0);
		
		System.out.println(maxDist);

	}

	private static void dfs(int v, int dist) {

		visited[v] = true;

		if (maxDist < dist) {
			maxNode = v;
			maxDist = dist;
		}

		for (Node n : adj[v]) {
			if (!visited[n.to]) {
				dfs(n.to, n.w + dist);
			}
		}

	}

}
