import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static List<Integer>[] building;
	private static int[] in, times;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		building = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			building[i] = new ArrayList<>();
		}

		in = new int[N + 1];
		times = new int[N + 1];

		for (int node = 1; node <= N; node++) {
			st = new StringTokenizer(br.readLine());
			times[node] = Integer.parseInt(st.nextToken());

			while (true) {
				int num = Integer.parseInt(st.nextToken());
				if (num == -1)
					break;

				building[num].add(node);
				in[node]++;
			}
		}

		Queue<Integer> q = new ArrayDeque<>();
		int[] dist = new int[N + 1];
		for (int node = 1; node <= N; node++) {
			if (in[node] == 0) {
				q.add(node);
				dist[node] = times[node];
			}
		}

		bfs(q, dist);
		for (int node = 1; node <= N; node++) {
			sb.append(dist[node]).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void bfs(Queue<Integer> q, int[] dist) {
		while (!q.isEmpty()) {
			int node = q.poll();

			for (int next : building[node]) {
				dist[next] = Math.max(dist[next], dist[node] + times[next]);

				if (--in[next] == 0) {
					q.add(next);
				}
			}
		}
	}
}