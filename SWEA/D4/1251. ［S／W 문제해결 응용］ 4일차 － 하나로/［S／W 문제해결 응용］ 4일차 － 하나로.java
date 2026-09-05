import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	private static class Info {
		int nodeX;
		int nodeY;
		double cost;

		public Info(int nodeX, int nodeY, double cost) {
			this.nodeX = nodeX;
			this.nodeY = nodeY;
			this.cost = cost;
		}
	}

	private static int N;
	private static double E;
	private static long[][] nodes;

	private static int[] parent, size;
	private static List<Info> edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			sb.append("#").append(t).append(" ");

			N = Integer.parseInt(br.readLine());

			nodes = new long[N][2];
			parent = new int[N];
			size = new int[N];

			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());

				for (int node = 0; node < N; node++) {
					long loc = Long.parseLong(st.nextToken());

					nodes[node][i] = loc;
				}
			}

			E = Double.parseDouble(br.readLine());

			edges = new ArrayList<>();

			for (int node = 0; node < N; node++) {
				parent[node] = node;
				size[node] = 1;
			}

			for (int curr = 0; curr < N - 1; curr++) {
				for (int next = curr + 1; next < N; next++) {
					long distX = Math.abs(nodes[curr][0] - nodes[next][0]);
					long distY = Math.abs(nodes[curr][1] - nodes[next][1]);

					long dist = distX * distX + distY * distY;
					double cost = dist;

					edges.add(new Info(curr, next, cost));
				}
			}

			edges.sort((a, b) -> Double.compare(a.cost, b.cost));

			double answer = 0;
			int pick = 0;

			for (int i = 0; i < edges.size(); i++) {
				Info info = edges.get(i);

				int nodeX = info.nodeX;
				int nodeY = info.nodeY;
				double cost = info.cost;

				int rootX = findParent(nodeX);
				int rootY = findParent(nodeY);

				if (rootX != rootY) {
					union(rootX, rootY);
					answer += cost;
					pick++;

					if (pick == N - 1) {
						break;
					}
				}
			}

			answer *= E;

			String ans = String.format("%.0f", answer);
			sb.append(ans).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static int findParent(int nodeX) {
		if (parent[nodeX] == nodeX) {
			return nodeX;
		}

		return parent[nodeX] = findParent(parent[nodeX]);
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
