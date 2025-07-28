import java.io.*;
import java.util.*;

public class Main {
	private static int N, R, Q;
	private static List<Integer>[] arr;
	private static int[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		arr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			link(from, to);
		}

		p = new int[N + 1];
		dfs(R, new boolean[N + 1]);

		for (int i = 0; i < Q; i++) {
			sb.append(p[Integer.parseInt(br.readLine())]).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int dfs(int num, boolean[] visited) {
		visited[num] = true;
		int cnt = 1;

		for (int node : arr[num]) {
			if (visited[node])
				continue;
			cnt += dfs(node, visited);
		}
		p[num] = cnt;
		return cnt;
	}

	private static void link(int from, int to) {
		arr[from].add(to);
		arr[to].add(from);
	}
}