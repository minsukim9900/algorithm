import java.io.*;
import java.util.*;

public class Solution {

	private static int N, K, maxL;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			visited = new boolean[N][N];
			maxL = 0;

			int max = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[r][c]);
				}
			}

			for (int r = 0; r < N; r++) {

				for (int c = 0; c < N; c++) {
					if (max == map[r][c]) {
						dfs(r, c, map[r][c], 1, false);
					}
				}

			}

			sb.append("#" + t + " " + maxL + "\n");

		}
		
		System.out.println(sb.toString());

	}


	private static void dfs(int r, int c, int h, int l, boolean skill) {

		if (l > maxL)
			maxL = l;

		visited[r][c] = true;

		for (int i = 0; i < 4; i++) {

			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {

				if (!skill) { // 아직 깍지 않음

					if (map[nr][nc] >= h && map[nr][nc] <= h + K - 1) {
						dfs(nr, nc, h-1, l + 1, true);

					} else if (map[nr][nc] < h) {
						dfs(nr, nc, map[nr][nc], l + 1, false);
					}

				} else {

					if (map[nr][nc] < h) {
						dfs(nr, nc, map[nr][nc], l + 1, true);
					}

				}

			}

		}

		visited[r][c] = false;

	}

}