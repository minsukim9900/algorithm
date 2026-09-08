import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	private static int V, E;
	private static List<int[]> edges;
	private static int[] parent, size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			parent = new int[V + 1];
			size = new int[V + 1];

			edges = new ArrayList<>();

			for (int node = 1; node < V + 1; node++) {
				parent[node] = node;
				size[node] = 1;
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());

				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());

				edges.add(new int[] { A, B, C });
			}

			edges.sort((a, b) -> Integer.compare(a[2], b[2]));

			int pick = 0;
			long answer = 0L;

			for (int i = 0; i < edges.size(); i++) {
				int[] curr = edges.get(i);

				int nodeX = curr[0];
				int nodeY = curr[1];
				int weight = curr[2];

				int rootX = findParent(nodeX);
				int rootY = findParent(nodeY);

				if (rootX != rootY) {
					pick++;
					answer += weight;

					union(rootX, rootY);

					if (pick == V - 1) {
						break;
					}
				}
			}

			sb.append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static int findParent(int node) {
		if (parent[node] == node) {
			return node;
		}

		return parent[node] = findParent(parent[node]);
	}

	private static void union(int rootX, int rootY) {
		if (size[rootX] >= size[rootY]) {
			parent[rootY] = rootX;
			size[rootX] += size[rootY];
		} else {
			parent[rootX] = rootY;
			size[rootY] += size[rootX];
		}
	}
}
