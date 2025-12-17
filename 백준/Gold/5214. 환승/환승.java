import java.io.*;
import java.util.*;

public class Main {
	private static int N, K, M;
	private static int[][] hyperTube;
	private static List<Integer>[] station;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		station = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			station[i] = new ArrayList<>();
		}

		hyperTube = new int[M][K];
		List<int[]> start = new ArrayList<>();
		for (int i = 0; i < M; i++) {

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < K; j++) {
				hyperTube[i][j] = Integer.parseInt(st.nextToken());
				station[hyperTube[i][j]].add(i);
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		int[] dist = new int[N + 1];
		boolean[] visited = new boolean[M];

		Queue<Integer> q = new ArrayDeque<>();
		dist[1] = 1;
		q.add(1);

		while (!q.isEmpty()) {
			int curr = q.poll();
			if (curr == N)
				return dist[N];

			for (int tube : station[curr]) {
				if (visited[tube])
					continue;
				visited[tube] = true;

				for (int next : hyperTube[tube]) {
					if (dist[next] == 0) {
						dist[next] = dist[curr] + 1;
						q.add(next);
					}
				}
			}
		}
		return -1;
	}
}