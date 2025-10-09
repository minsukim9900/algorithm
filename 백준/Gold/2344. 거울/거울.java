import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	private static int[][] board;
	private static Map<Integer, Integer> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N + 2][M + 2];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int num = 1;
		for (int r = 1; r <= N; r++) {
			board[r][0] = num++;
		}

		for (int c = 1; c <= M; c++) {
			board[N + 1][c] = num++;
		}

		for (int r = N; r >= 1; r--) {
			board[r][M + 1] = num++;
		}

		for (int c = M; c >= 1; c--) {
			board[0][c] = num++;
		}
		System.out.println(simulate());
	}

	private static String simulate() {
		StringBuilder sb = new StringBuilder();
		for (int r = 1; r <= N; r++) {
			sb.append(shoot(r, 1, 1)).append(" ");
		}

		for (int c = 1; c <= M; c++) {
			sb.append(shoot(N, c, 0)).append(" ");
		}

		for (int r = N; r >= 1; r--) {
			sb.append(shoot(r, M, 3)).append(" ");
		}

		for (int c = M; c >= 1; c--) {
			sb.append(shoot(1, c, 2)).append(" ");
		}
		return sb.toString();
	}

	private static int shoot(int r, int c, int dir) {
		if (!isRange(r, c)) {
			return board[r][c];
		}

		if (board[r][c] == 1) {
			dir = reflect(dir);
		}

		return shoot(r + delta[dir][0], c + delta[dir][1], dir);
	}

	private static int reflect(int dir) {
		return (dir) ^ 1;
	}

	private static boolean isRange(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= M;
	}
}
