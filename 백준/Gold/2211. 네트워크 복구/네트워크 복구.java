import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[] p;
	private static List<int[]>[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		p = new int[N + 1];
		arr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}

		Arrays.fill(p, -1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			arr[x].add(new int[] { y, w });
			arr[y].add(new int[] { x, w });
		}

		dijsktra(1);
		int count = 0;
		for (int i = 2; i <= N; i++) {
			if (p[i] > 0) {
				count++;
				sb.append(p[i]).append(" ").append(i).append("\n");
			}
		}
		System.out.println(count);
		System.out.println(sb.toString());
	}

	private static void dijsktra(int start) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, 10_000_000);
		dist[start] = 0;
		p[start] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.add(new int[] { start, 0 });

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			if (dist[curr[0]] != curr[1]) {
				continue;
			}

			for (int[] node : arr[curr[0]]) {
				if (dist[node[0]] > dist[curr[0]] + node[1]) {
					dist[node[0]] = dist[curr[0]] + node[1];
					p[node[0]] = curr[0];
					pq.add(new int[] { node[0], dist[node[0]] });
				}
			}
		}
	}
}