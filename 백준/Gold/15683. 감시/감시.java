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
			// cctv가 바라보는 방향 0~ 3 : 동 남 서 북
			// result 배열에 들어간 수는 cctv 배열의 idx
			int[] side = new int[cctv.size()];
			for (int i = 0; i < cctv.size(); i++) {
				side[i] = result[i];
			}

			quest(side);

		} else {

			for (int i = 0; i < 4; i++) {
				result[depth] = i;
				dfs(depth + 1);
			}

		}
	}

	private static void quest(int[] side) {
		int[][] copy = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}

		// 1번 cctv
		for (int i = 0; i < side.length; i++) {
			int[] curr = cctv.get(i);
			int curr_cctv = curr[0];
			int dir = side[i];
			int max = Math.max(N, M);

			if (curr_cctv == 1) {
				int r = curr[1];
				int c = curr[2];

				for (int j = 0; j < max; j++) {
					r += dr[dir];
					c += dc[dir];
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 6) {
						break;
					}

					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 0) {

						copy[r][c] = 7;
					}
				}

			} else if (curr_cctv == 2) {

				int r = curr[1];
				int c = curr[2];

				for (int j = 0; j < max; j++) {
					r += dr[dir];
					c += dc[dir];
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 6) {
						break;
					}

					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 0) {
						copy[r][c] = 7;
					}
				}

				r = curr[1];
				c = curr[2];

				for (int j = 0; j < max; j++) {
					r += dr[(dir + 2) % 4];
					c += dc[(dir + 2) % 4];
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 6) {
						break;
					}
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 0) {
						copy[r][c] = 7;
					}
				}

			} else if (curr_cctv == 3) {

				int r = curr[1];
				int c = curr[2];

				for (int j = 0; j < max; j++) {
					r += dr[dir];
					c += dc[dir];
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 6) {
						break;
					}

					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 0) {
						copy[r][c] = 7;
					}
				}

				r = curr[1];
				c = curr[2];

				for (int j = 0; j < max; j++) {
					r += dr[(dir + 1) % 4];
					c += dc[(dir + 1) % 4];

					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 6) {
						break;
					}

					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 0) {
						copy[r][c] = 7;
					}
				}

			} else if (curr_cctv == 4) {

				int r = curr[1];
				int c = curr[2];

				for (int j = 0; j < max; j++) {
					r += dr[dir];
					c += dc[dir];
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 6) {
						break;
					}
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 0) {
						copy[r][c] = 7;
					}
				}

				r = curr[1];
				c = curr[2];

				for (int j = 0; j < max; j++) {
					r += dr[(dir + 1) % 4];
					c += dc[(dir + 1) % 4];

					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 6) {
						break;
					}

					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 0) {
						copy[r][c] = 7;
					}
				}

				r = curr[1];
				c = curr[2];

				for (int j = 0; j < max; j++) {
					r += dr[(dir + 2) % 4];
					c += dc[(dir + 2) % 4];
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 6) {
						break;
					}

					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 0) {
						copy[r][c] = 7;
					}
				}

			} else {

				int r = curr[1];
				int c = curr[2];

				for (int j = 0; j < max; j++) {
					r += dr[dir];
					c += dc[dir];
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 6) {
						break;
					}
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 0) {
						copy[r][c] = 7;
					}
				}

				r = curr[1];
				c = curr[2];

				for (int j = 0; j < max; j++) {
					r += dr[(dir + 1) % 4];
					c += dc[(dir + 1) % 4];
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 6) {
						break;
					}
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 0) {
						copy[r][c] = 7;
					}
				}

				r = curr[1];
				c = curr[2];

				for (int j = 0; j < max; j++) {
					r += dr[(dir + 2) % 4];
					c += dc[(dir + 2) % 4];
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 6) {
						break;
					}
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 0) {
						copy[r][c] = 7;
					}
				}

				r = curr[1];
				c = curr[2];

				for (int j = 0; j < max; j++) {
					r += dr[(dir + 3) % 4];
					c += dc[(dir + 3) % 4];
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 6) {
						break;
					}
					if (r >= 0 && r < N && c >= 0 && c < M && copy[r][c] == 0) {
						copy[r][c] = 7;
					}
				}

			}

		}

		int cnt = 0;
		boolean check = true;
		out: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 0) {
					cnt++;
					if (cnt > blind) {
						cnt = Integer.MAX_VALUE;
						check = false;
						break out;
					}
				}
			}
		}
		if(check) {
			blind = Math.min(blind, cnt);
		}

	}
}
