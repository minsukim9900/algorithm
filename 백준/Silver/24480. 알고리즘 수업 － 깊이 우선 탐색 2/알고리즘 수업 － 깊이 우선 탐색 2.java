import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, R, cnt;
	private static List<Integer>[] arr;
	private static int[] count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		count = new int[N + 1];
		arr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from].add(to);
			arr[to].add(from);
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(arr[i], Comparator.reverseOrder());
		}
		dfs(R, new boolean[N + 1]);
		for(int i = 1; i <= N; i++) {
			sb.append(count[i]).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean[] dfs(int num, boolean[] visited) {
		visited[num] = true;
		count[num] = ++cnt;
		for (int w : arr[num]) {
			if (visited[w])
				continue;

			dfs(w, visited);
		}
		return visited;
	}
}