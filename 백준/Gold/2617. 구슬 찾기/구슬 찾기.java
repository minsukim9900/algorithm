import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static List<Integer>[] light, heavy;
	private static int[] in, out;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		in = new int[N + 1];
		out = new int[N + 1];
		light = new ArrayList[N + 1];
		heavy = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			light[i] = new ArrayList<>();
			heavy[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			light[x].add(y);
			heavy[y].add(x);
		}

		boolean[] visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(visited, false);
			out[i] = dfs(i, heavy, visited) - 1;
			Arrays.fill(visited, false);
			in[i] = dfs(i, light, visited) - 1;
		}

		int answer = 0;
		int comp = (N + 1) / 2;
		for (int i = 1; i <= N; i++) {
			if (in[i] >= comp || out[i] >= comp)
				answer++;
		}
		System.out.println(answer);
	}

	private static int dfs(int node, List<Integer>[] adj, boolean[] visited) {
		visited[node] = true;
		int cnt = 1;

		for (int next : adj[node]) {
			if (visited[next])
				continue;

			cnt += dfs(next, adj, visited);
		}

		return cnt;
	}
}