import java.io.*;
import java.util.*;

public class Main {
	private static int N, answer;
	private static int[][] board;
	private static int[][] delta = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int t = 1;
		while (true) {
			answer = Integer.MIN_VALUE;
			N = Integer.parseInt(br.readLine().trim());
			if (N == 0)
				break;

			board = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					for (int i = 0; i < 5; i++) {
						simulate(r, c, i);
					}
				}
			}

			sb.append(t).append(".").append(" ").append(answer).append("\n");
			t++;
		}
		System.out.println(sb.toString());
	}

	private static void simulate(int r, int c, int shape) {
		switch (shape) {
		case 0:
			shape0(r, c, 0, 1, board[r][c]);
			shape0(r, c, 1, 1, board[r][c]);
			return;
		case 1:
			shape1(r, c, 0, 1, board[r][c]);
			shape1(r, c, 1, 1, board[r][c]);
			return;
		case 2:
			shape2(r, c, 0, 1, board[r][c]);
			shape2(r, c, 1, 1, board[r][c]);
			return;
		case 3:
			shape3(r, c, 0, 1, board[r][c]);
			shape3(r, c, 1, 1, board[r][c]);
			return;
		default:
			shape4(r, c, 0, 1, board[r][c]);
			return;
		}
	}

	private static void shape0(int r, int c, int dir, int depth, int sum) {
		if (depth == 4) {
			answer = Math.max(answer, sum);
			return;
		}

		int nr = r + delta[dir][0];
		int nc = c + delta[dir][1];
		if (isRange(nr, nc)) {
			shape0(nr, nc, dir, depth + 1, sum + board[nr][nc]);
		}
	}

	private static void shape1(int r, int c, int dir, int depth, int sum) {
		if (depth == 4) {
			answer = Math.max(answer, sum);
			return;
		}

		if (depth == 2) {
			dir = dir == 0 ? 3 : 0;
		} else if (depth == 3) {
			dir = dir == 3 ? 0 : 1;
		}

		int nr = r + delta[dir][0];
		int nc = c + delta[dir][1];

		if (isRange(nr, nc)) {
			shape1(nr, nc, dir, depth + 1, sum + board[nr][nc]);
		}
	}

	private static void shape2(int r, int c, int dir, int depth, int sum) {
		if (depth == 4) {
			answer = Math.max(answer, sum);
			return;
		}

		if (depth == 3) {
			if (dir == 0) {
				int nr = r + delta[3][0];
				int nc = c + delta[3][1];

				if (isRange(nr, nc)) {
					shape2(nr, nc, 3, depth + 1, sum + board[nr][nc]);
				}

				nr = r - delta[dir][0] - delta[dir][0] + delta[1][0];
				nc = c - delta[dir][1] - delta[dir][1] + delta[1][1];
				if (isRange(nr, nc)) {
					shape2(nr, nc, 1, depth + 1, sum + board[nr][nc]);
				}
			} else {
				int nr = r + delta[0][0];
				int nc = c + delta[0][1];

				if (isRange(nr, nc)) {
					shape2(nr, nc, 0, depth + 1, sum + board[nr][nc]);
				}

				nr = r - delta[dir][0] - delta[dir][0] + delta[2][0];
				nc = c - delta[dir][1] - delta[dir][1] + delta[2][1];
				if (isRange(nr, nc)) {
					shape2(nr, nc, 2, depth + 1, sum + board[nr][nc]);
				}
			}
		}

		int nr = r + delta[dir][0];
		int nc = c + delta[dir][1];

		if (isRange(nr, nc)) {
			shape2(nr, nc, dir, depth + 1, sum + board[nr][nc]);
		}
	}

	private static void shape3(int r, int c, int dir, int depth, int sum) {
		if (depth == 4) {
			answer = Math.max(answer, sum);
			return;
		}

		int nr = 0;
		int nc = 0;
		if (depth == 3) {
			nr = r - delta[dir][0];
			nc = c - delta[dir][1];

			for (int i = 0; i < 4; i++) {
				if (dir == 0 && i % 2 == 0)
					continue;
				if (dir == 1 && i % 2 == 1)
					continue;

				int nnr = nr + delta[i][0];
				int nnc = nc + delta[i][1];
				if (isRange(nnr, nnc)) {
					shape3(nnr, nnc, i, depth + 1, sum + board[nnr][nnc]);
				}
			}
		} else {
			nr = r + delta[dir][0];
			nc = c + delta[dir][1];
			if (isRange(nr, nc)) {
				shape3(nr, nc, dir, depth + 1, sum + board[nr][nc]);
			}
		}
	}

	private static void shape4(int r, int c, int dir, int depth, int sum) {
		if (depth == 4) {
			answer = Math.max(answer, sum);
			return;
		}

		int nr = r + delta[dir][0];
		int nc = c + delta[dir][1];
		if (isRange(nr, nc)) {
			shape4(nr, nc, (dir + 1) % 4, depth + 1, sum + board[nr][nc]);
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}