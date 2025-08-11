import java.io.*;
import java.util.*;

public class Main {
	private static int N, R, Q;
	private static int[] subTreeCount;
	private static int[] parent;
	private static int[] level;
	private static boolean[] visited;

	private static List<Integer>[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		subTreeCount = new int[N + 1];
		parent = new int[N + 1];
		level = new int[N + 1];
		visited = new boolean[N + 1];

		arr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from].add(to);
			arr[to].add(from);
		}
		dfs(R, 0);
//		System.out.println(Arrays.toString(subTreeCount));
//		System.out.println(Arrays.toString(parent));
//		System.out.println(Arrays.toString(level));

		for (int i = 0; i < Q; i++) {
			sb.append(subTreeCount[Integer.parseInt(br.readLine())]);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int curr, int l) {
		visited[curr] = true;
		int cnt = 1;
		level[curr] = l;

		for (int node : arr[curr]) {
			if (visited[node])
				continue;

			parent[node] = curr;
			dfs(node, l + 1);
			cnt += subTreeCount[node];
		}

		subTreeCount[curr] = cnt;
	}
}