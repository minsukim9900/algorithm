import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	private static int V, E, nodeX, nodeY;
	private static int[] parent, size, level;
	private static List<Integer>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			nodeX = Integer.parseInt(st.nextToken());
			nodeY = Integer.parseInt(st.nextToken());

			adj = new ArrayList[V + 1];
			parent = new int[V + 1];
			size = new int[V + 1];
			level = new int[V + 1];

			for (int node = 1; node < V + 1; node++) {
				adj[node] = new ArrayList<>();
				parent[node] = node;
				size[node] = 1;
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				adj[from].add(to);
			}

			dfs(1, 0);
			int result = getSubTreeCount();
			sb.append("#").append(t).append(" ").append(nodeX).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int getSubTreeCount() {
		while (level[nodeX] != level[nodeY]) {
			if (level[nodeX] < level[nodeY]) {
				level[nodeY]--;
				nodeY = parent[nodeY];
			} else {
				level[nodeX]--;
				nodeX = parent[nodeX];
			}
		}

		while (nodeX != nodeY) {
			nodeX = parent[nodeX];
			nodeY = parent[nodeY];
		}

		return size[nodeX];
	}

	private static int dfs(int node, int depth) {
		level[node] = depth;
		int cnt = size[node];

		for (int next : adj[node]) {
			parent[next] = node;

			cnt += dfs(next, depth + 1);
		}

		return size[node] = cnt;
	}
}
