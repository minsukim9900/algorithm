import java.io.*;
import java.util.*;

public class Solution {

	private static int N, max;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static ArrayList<int[]>[] hole;
	private static int[][] block = new int[5][4];

	static {
		block[0] = new int[] { 1, 3, 0, 2 };
		block[1] = new int[] { 3, 0, 1, 2 };
		block[2] = new int[] { 2, 0, 3, 1 };
		block[3] = new int[] { 1, 2, 3, 0 };
		block[4] = new int[] { 1, 0, 3, 2 };
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			max = 0;
			board = new int[N][N];
			hole = new ArrayList[5];

			for (int i = 0; i < 5; i++) {
				hole[i] = new ArrayList<>();
			}

			ArrayList<int[]> loc = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());

					if (board[r][c] > 5) {
						hole[board[r][c] - 6].add(new int[] { r, c });
					}
					if (board[r][c] == 0) {
						loc.add(new int[] { r, c });
					}
				}
			}


			for (int[] w : loc) {
				for (int i = 0; i < 4; i++) {
					dfs(w[0], w[1], i);
				}
			}

			sb.append("#" + t + " " + max + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int sr, int sc, int dir) {

		int r = sr;
		int c = sc;
		int d = dir;
		int cnt = 0;

		while (true) {
			r += delta[d][0];
			c += delta[d][1];

			if ((r == sr && sc == c) || (isRange(r, c) && board[r][c] == -1)) {
				max = Math.max(max, cnt);
				break;
			}

			if (!isRange(r, c)) {
				d = visitWall(d);
				cnt++;
				continue;
			}

			if (board[r][c] >= 1 && board[r][c] <= 5) {
				d = block[board[r][c] - 1][d];
				cnt++;
				continue;
			}

			if (board[r][c] >= 6 && board[r][c] <= 10) {
				int[] info = potal(r, c);
				r = info[0];
				c = info[1];
				continue;
			}
		}

	}

	private static boolean isRange(int r, int c) {

		if (r < 0 || r >= N || c < 0 || c >= N) {
			return false;
		}

		return true;
	}

	private static int visitWall(int dir) {
		if (dir == 0)
			return 1;
		if (dir == 1)
			return 0;
		if (dir == 2)
			return 3;
		return 2;
	}

	private static int[] potal(int r, int c) {
		int[] location = null;
		for (int i = 0; i < 2; i++) {
			int[] curr = hole[board[r][c] - 6].get(i);
			if (curr[0] == r && curr[1] == c) {
				location = hole[board[r][c] - 6].get((i + 1) & 1);
			}
		}

		return location;

	}

}