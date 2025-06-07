import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static ArrayList<Integer>[] arr;
	private static int[] p;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		p = new int[N + 1];
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

		dfs(1);
		for (int i = 2; i <= N; i++) {
			sb.append(p[i]).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int node) {
		visited[node] = true;

		for (int i : arr[node]) {
			if (visited[i]) {
				continue;
			}
			p[i] = node;
			dfs(i);
		}

	}
}