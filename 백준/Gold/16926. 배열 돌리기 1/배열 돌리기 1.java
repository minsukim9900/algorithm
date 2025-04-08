import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, R;
	private static int[][] board;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

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

		for (int i = 0; i < R; i++) {
			select();
		}
		output();

	}

	private static void output() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				sb.append(board[r][c] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void select() {
		int tmp = (Math.min(N, M)) >> 1;
		int r = 0;
		int c = 0;

		for (int i = 0; i < tmp; i++) {
			move(r, c);
			r++;
			c++;
		}
	}

	private static void move(int r, int c) {
		int tmp = board[r][c];

		for (int i = c; i < M - c - 1; i++) {
			swap(r, i, r, i + 1);
		}

		for (int i = r + 1; i < N - r; i++) {
			swap(i - 1, M - 1 - c, i, M - 1 - c);
		}

		for (int i = M - c - 2; i >= 0 + c; i--) {
			swap(N - 1 - r, i + 1, N - 1 - r, i);
		}

		for (int i = N - r - 2; i > 0 + r; i--) {
			swap(i + 1, c, i, c);
		}
	}

	private static void swap(int sr, int sc, int er, int ec) {
		int tmp = board[sr][sc];
		board[sr][sc] = board[er][ec];
		board[er][ec] = tmp;
	}
}