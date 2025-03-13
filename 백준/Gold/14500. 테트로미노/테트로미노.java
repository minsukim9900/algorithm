import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, result, max;
	private static int[][] map;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[r][c]);
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				visited[r][c] = true;
				dfs(r, c, 1, map[r][c]);
				visited[r][c] = false;
			}
		}

		System.out.println(result);

	}

	private static void dfs(int r, int c, int depth, int sum) {

		if (sum + max * ((1 << 2) - depth) <= result)
			return;

		if (depth == 4) {
			result = Math.max(result, sum);
		} else {

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {

					if (depth == 2) {
						visited[nr][nc] = true;
						dfs(r, c, depth + 1, sum + map[nr][nc]);
						visited[nr][nc] = false;
					}

					visited[nr][nc] = true;
					dfs(nr, nc, depth + 1, sum + map[nr][nc]);
					visited[nr][nc] = false;
				}
			}

		}

	}

}
