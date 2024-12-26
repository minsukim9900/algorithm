import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] map;
	private static int[][] dist;
	private static List<int[]> wall = new ArrayList<>();
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static final int INF = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		dist = new int[N][M];

		for (int r = 0; r < N; r++) {
			String nums = br.readLine();
			for (int c = 0; c < M; c++) {
				int tmp = nums.charAt(c) - '0';

				if (tmp == 1) {
					wall.add(new int[] { r, c });
				}

				map[r][c] = tmp;
			}
		}

		int result = bfs();

		if (result >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}

	}

	private static int bfs() {

		for (int[] i : dist) {
			Arrays.fill(i, INF);
		}
		boolean[][][] visited = new boolean[N][M][2];
		visited[0][0][0] = true;

		dist[0][0] = 1;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 0, 0, 0 });

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			
			if(curr[0] == N-1 && curr[1] == M-1) {
				return dist[N-1][M-1];
			}

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (curr[2] == 1) {
						if (map[nr][nc] == 0 && !visited[nr][nc][1]) {
							visited[nr][nc][1] = true;
							dist[nr][nc] = dist[curr[0]][curr[1]] + 1;
							q.offer(new int[] { nr, nc, 1 });
						}
					} else {

						if (map[nr][nc] == 0 && !visited[nr][nc][0]) {
							visited[nr][nc][0] = true;
							dist[nr][nc] = dist[curr[0]][curr[1]] + 1;
							q.offer(new int[] { nr, nc, 0 });

						} else if(map[nr][nc] == 1) {
							visited[nr][nc][1] = true;
							dist[nr][nc] = dist[curr[0]][curr[1]] + 1;

							q.offer(new int[] { nr, nc, 1 });
						}

					}
				}

			}
		}
		return dist[N - 1][M - 1];

	}

}