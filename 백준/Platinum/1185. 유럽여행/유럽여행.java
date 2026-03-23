import java.io.*;
import java.util.*;

public class Main {
	private static int N, P;
	private static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		int[] cost = new int[N + 1];
		long answer = Long.MAX_VALUE;
		parent = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			cost[i] = Integer.parseInt(br.readLine());
			answer = Math.min(cost[i], answer);
			parent[i] = i;
		}

		List<int[]> edges = new ArrayList<>();
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges.add(new int[] { x, y, cost[x] + cost[y] + weight * 2 });
		}

		edges.sort((a, b) -> Integer.compare(a[2], b[2]));

		int pick = 0;

		for (int[] edge : edges) {
			int px = findP(edge[0]);
			int py = findP(edge[1]);
			int weight = edge[2];

			if (px != py) {
				union(px, py);
				answer += weight;
				pick++;
			}

			if (pick == N - 1) {
				break;
			}
		}
		System.out.println(answer);
	}

	private static int findP(int x) {
		if (x != parent[x]) {
			return parent[x] = findP(parent[x]);
		}

		return parent[x];
	}

	private static void union(int x, int y) {
		parent[y] = x;
	}
}