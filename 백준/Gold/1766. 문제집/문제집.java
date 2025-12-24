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

		in = new int[N + 1];
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

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
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 1; i <= N; i++) {
			if (in[i] == 0)
				pq.add(i);
		}

		List<Integer> answer = new ArrayList<>();
		while (!pq.isEmpty()) {
			int curr = pq.poll();

			answer.add(curr);

			for (int next : adj[curr]) {
				in[next]--;

				if (in[next] == 0) {
					pq.add(next);
				}
			}
		}

		for (int w : answer) {
			sb.append(w).append(" ");
		}
		return sb.toString();
	}
}