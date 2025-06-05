import java.io.*;
import java.util.*;

class Main {
	private static int N, M, R;
	private static int[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		board = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int range = Math.min(N, M) >> 1;

		for (int j = 0; j < range; j++) {
			int tmp = 2 * (N - (2 * j) + M - (2 * j)) - 4;

			for (int i = 0; i < (R % tmp); i++) {
				for (int c = j; c < M - 1 - j; c++) {
					swap(j, c, j, c + 1);
				}

				for (int r = j; r < N - 1 - j; r++) {
					swap(r, M - 1 - j, r + 1, M - 1 - j);
				}

				for (int c = M - 1 - j; c > 0 + j; c--) {
					swap(N - 1 - j, c, N - 1 - j, c - 1);
				}

				for (int r = N - 1 - j; r > 0 + j + 1; r--) {
					swap(r, j, r - 1, j);
				}
			}

		}
		output();
	}

	private static void output() {
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				sb.append(board[r][c] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void swap(int sr, int sc, int er, int ec) {
		int tmp = board[sr][sc];
		board[sr][sc] = board[er][ec];
		board[er][ec] = tmp;
	}
}