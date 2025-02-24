import java.io.*;
import java.util.*;

public class Solution {

	private static int[][] map, dist;
	private static int N;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			dist = new int[N][N];
			for (int r = 0; r < N; r++) {
				String nums = br.readLine();
				for (int c = 0; c < N; c++) {
					map[r][c] = nums.charAt(c) - '0';
				}
			}

			bfs();
			int answer = dist[N - 1][N - 1];
			sb.append("#"+ t + " ").append(answer + "\n");
		}
		System.out.println(sb.toString());

	}

	private static void bfs() {

		for (int r = 0; r < N; r++) {
			Arrays.fill(dist[r], Integer.MAX_VALUE);
		}

		dist[0][0] = 0;

		Queue<int[]> q = new ArrayDeque<>();

		q.offer(new int[] { 0, 0, map[0][0] });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {

				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N) {

					int comp = map[nr][nc] + dist[curr[0]][curr[1]];
					if (comp < dist[nr][nc]) {
						dist[nr][nc] = comp;
						q.offer(new int[] { nr, nc });

					}

				}

			}
		}
	}

}