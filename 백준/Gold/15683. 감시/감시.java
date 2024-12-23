import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static List<int[]> cctv;
	private static int[] dr = { 0, 1, 0, -1 };
	private static int[] dc = { 1, 0, -1, 0 };
	private static int[][] map;
	private static int blind = Integer.MAX_VALUE;
	private static int[] result;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctv = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < M; c++) {
				int tmp = Integer.parseInt(st.nextToken());

				if (tmp >= 1 && tmp <= 5) {
					cctv.add(new int[] { tmp, r, c });
				}

				map[r][c] = tmp;
			}
		}

		result = new int[cctv.size()];
		dfs(0);
		System.out.println(blind);
	}

	private static void dfs(int depth) {
		
		if (depth == cctv.size()) {
			quest();
		} else {

			for (int i = 0; i < 4; i++) {
				result[depth] = i;
				dfs(depth + 1);
			}

		}
	}

	private static void quest() {
		
		int[][] copy = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}

		int cnt = 0;

		for (int i = 0; i < result.length; i++) {

			int[] curr = cctv.get(i);
			int curr_cctv = curr[0];
			int dir = result[i];
			int max = Math.max(N, M);
			int r = curr[1];
			int c = curr[2];

			switch (curr_cctv) {

			case 1:

				side1(copy, max, r, c, dir);
				break;

			case 2:

				side1(copy, max, r, c, dir);
				r = curr[1];
				c = curr[2];
				side2(copy, max, r, c, dir);
				break;

			case 3:

				side1(copy, max, r, c, dir);
				r = curr[1];
				c = curr[2];
				side3(copy, max, r, c, dir);
				break;

			case 4:

				side1(copy, max, r, c, dir);
				r = curr[1];
				c = curr[2];
				side2(copy, max, r, c, dir);
				r = curr[1];
				c = curr[2];
				side3(copy, max, r, c, dir);
				break;

			case 5:

				side1(copy, max, r, c, dir);
				r = curr[1];
				c = curr[2];
				side2(copy, max, r, c, dir);
				r = curr[1];
				c = curr[2];
				side3(copy, max, r, c, dir);
				r = curr[1];
				c = curr[2];
				side4(copy, max, r, c, dir);
				break;

			}

		}

		for (int j = 0; j < N; j++) {

			for (int k = 0; k < M; k++) {
				if (copy[j][k] == 0) {
					cnt++;
				}
			}

			if (cnt >= blind) {
				return;
			}

		}

		blind = Math.min(blind, cnt);

	}

	private static void side1(int[][] copy, int max, int r, int c, int dir) {

		for (int j = 0; j < max; j++) {
			r += dr[dir];
			c += dc[dir];
			if (r >= 0 && r < N && c >= 0 && c < M) {

				if (copy[r][c] == 6) {
					return;
				} else if (copy[r][c] == 0) {
					copy[r][c] = 7;
				}

			}

		}

	}

	private static void side2(int[][] copy, int max, int r, int c, int dir) {

		for (int j = 0; j < max; j++) {
			r += dr[(dir + 2) % 4];
			c += dc[(dir + 2) % 4];

			if (r >= 0 && r < N && c >= 0 && c < M) {

				if (copy[r][c] == 6) {
					return;
				} else if (copy[r][c] == 0) {
					copy[r][c] = 7;
				}

			}
		}
	}

	private static void side3(int[][] copy, int max, int r, int c, int dir) {

		for (int j = 0; j < max; j++) {
			r += dr[(dir + 1) % 4];
			c += dc[(dir + 1) % 4];

			if (r >= 0 && r < N && c >= 0 && c < M) {

				if (copy[r][c] == 6) {
					return;
				} else if (copy[r][c] == 0) {
					copy[r][c] = 7;
				}

			}
		}
	}

	private static void side4(int[][] copy, int max, int r, int c, int dir) {

		for (int j = 0; j < max; j++) {
			r += dr[(dir + 3) % 4];
			c += dc[(dir + 3) % 4];

			if (r >= 0 && r < N && c >= 0 && c < M) {

				if (copy[r][c] == 6) {
					return;
				} else if (copy[r][c] == 0) {
					copy[r][c] = 7;
				}

			}
		}

	}
	
}
