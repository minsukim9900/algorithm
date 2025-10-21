import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static int[][] board;
	private static int[][][][] puzzles;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		puzzles = new int[K][4][][];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			puzzles[i][0] = new int[R][C];

			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					puzzles[i][0][r][c] = Integer.parseInt(st.nextToken());
				}
			}
			puzzles[i] = rotatePuzzle(R, C, puzzles[i][0]);

			out: for (int j = 0; j < 4; j++) {
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < M; c++) {
						if (isPoss(r, c, puzzles[i][j]))
							break out;
					}
				}
			}
		}
		System.out.println(answer());
	}

	private static int answer() {
		int count = 0;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (board[r][c] == 1) {
					count++;
				}
			}
		}
		return count;
	}

	private static boolean isPoss(int sr, int sc, int[][] shape) {
		for (int r = 0; r < shape.length; r++) {
			for (int c = 0; c < shape[r].length; c++) {
				if (!isRange(sr + r, sc + c) || board[sr + r][sc + c] + shape[r][c] > 1)
					return false;
			}
		}

		for (int r = 0; r < shape.length; r++) {
			for (int c = 0; c < shape[r].length; c++) {
				board[sr + r][sc + c] += shape[r][c];
			}
		}

		return true;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	private static int[][][] rotatePuzzle(int R, int C, int[][] shape) {
		int[][][] result = new int[4][][];
		result[0] = shape;
		result[1] = new int[C][R];
		result[2] = new int[R][C];
		result[3] = new int[C][R];

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				result[3][(C - 1) - c][r] = shape[r][c];
				result[2][(R - 1) - r][(C - 1) - c] = shape[r][c];
				result[1][c][(R - 1) - r] = shape[r][c];
			}
		}
		return result;
	}
}