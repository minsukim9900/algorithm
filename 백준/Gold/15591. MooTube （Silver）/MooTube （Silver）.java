import java.io.*;
import java.util.*;

public class Main {
	private static int N, Q;
	private static List<int[]>[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		arr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			arr[from].add(new int[] { to, w });
			arr[to].add(new int[] { from, w });
		}

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			sb.append(dfs(s, Integer.MAX_VALUE, k, s, new boolean[N + 1])).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int dfs(int s, int pre, int k, int p, boolean[] visited) {
		int count = 0;
		visited[s] = true;

		for (int[] node : arr[s]) {
			if (visited[node[0]]) {
				continue;
			}

			int w = Math.min(pre, node[1]);

			if (w >= k) {
				count++;
			}

			count += dfs(node[0], w, k, p, visited);
		}
		return count;
	}
}