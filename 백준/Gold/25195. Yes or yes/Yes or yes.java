import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, S;
	private static boolean[] state;
	private static List<Integer>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		state = new boolean[N + 1];
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			adj[x].add(y);
		}

		S = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < S; i++) {
			state[Integer.parseInt(st.nextToken())] = true;
		}
		System.out.println(bfs() ? "yes" : "Yes");
	}

	private static boolean bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		int count = state[1] ? 1 : 0;
		q.add(new int[] { 1, count });
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int cnt = 0;

			for (int next : adj[curr[0]]) {
				if (visited[next])
					continue;

				cnt++;
				int weight = state[next] ? 1 : 0;
				q.add(new int[] { next, curr[1] + weight });
			}

			if (cnt == 0 && curr[1] == 0) {
				return true;
			}
		}
		return false;
	}
}