import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static List<Integer>[] adj;
	private static int[] in;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		in = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			adj[x].add(y);
			in[y]++;
		}
		System.out.println(bfs());
	}

	private static String bfs() {
		StringBuilder sb = new StringBuilder();
		Queue<int[]> q = new ArrayDeque<>();
		int[] answer = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			if (in[i] == 0) {
				q.add(new int[] { i, 1 });
			}
		}

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			answer[curr[0]] = curr[1];

			for (int next : adj[curr[0]]) {

				if (--in[next] == 0) {
					q.add(new int[] { next, curr[1] + 1 });
				}
			}
		}
		for(int i = 1; i <= N; i++) {
			sb.append(answer[i]).append(" ");
		}
		return sb.toString();
	}
}