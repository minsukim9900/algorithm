import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static List<Integer>[] arr;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];

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

		int answer = 0;
		for (int i = 1; i <= N; i++) {
			if (visited[i])
				continue;
			dfs(i);
			answer++;
		}
		System.out.println(answer);
	}

	private static void dfs(int node) {
		visited[node] = true;

		for (int w : arr[node]) {
			if (visited[w])
				continue;
			dfs(w);
		}
	}
}
