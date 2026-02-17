import java.io.*;
import java.util.*;

public class Main {
	private static int N1, M1, N2, M2;
	private static int[][] board;
	private static int[][] puzzleA;
	private static int[][][] puzzleB;

	private static final int MAX = 300;
	private static final int INF = 3000;
	private static final int STANDARD = 51;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		board = new int[MAX][MAX];

		st = new StringTokenizer(br.readLine());
		N1 = Integer.parseInt(st.nextToken());
		M1 = Integer.parseInt(st.nextToken());

		puzzleA = new int[N1][M1];

		for (int r = 0; r < N1; r++) {
			String str = br.readLine();
			for (int c = 0; c < M1; c++) {
				puzzleA[r][c] = str.charAt(c) - '0';
			}
		}

		st = new StringTokenizer(br.readLine());
		N2 = Integer.parseInt(st.nextToken());
		M2 = Integer.parseInt(st.nextToken());

		puzzleB = new int[4][][];
		puzzleB[0] = new int[N2][M2];

		for (int r = 0; r < N2; r++) {
			String str = br.readLine();
			for (int c = 0; c < M2; c++) {
				puzzleB[0][r][c] = str.charAt(c) - '0';
			}
		}
		rotate(puzzleB);

		for (int r = 0; r < N1; r++) {
			for (int c = 0; c < M1; c++) {
				board[r + STANDARD][c + STANDARD] = puzzleA[r][c];
			}
		}

		int answer = INF;
		for (int i = 0; i < 4; i++) {
			int N = puzzleB[i].length;
			int M = puzzleB[i][0].length;

			for (int r = STANDARD - N; r <= STANDARD + N1; r++) {
				for (int c = STANDARD - M; c <= STANDARD + M1; c++) {
					answer = Math.min(answer, cal(r, c, puzzleB[i]));
				}
			}
		}
		System.out.println(answer);
	}

	private static int cal(int sr, int sc, int[][] puzzle) {
		int[][] copyBoard = new int[MAX][MAX];
		for (int r = 0; r < MAX; r++) {
			copyBoard[r] = board[r].clone();
		}

		int N = puzzle.length;
		int M = puzzle[0].length;

		boolean isPoss = true;

		int minR = Math.min(sr, STANDARD);
		int minC = Math.min(sc, STANDARD);

		int maxR = Math.max(sr + N - 1, STANDARD + N1 - 1);
		int maxC = Math.max(sc + M - 1, STANDARD + M1 - 1);

		int x = maxR - minR + 1;
		int y = maxC - minC + 1;

		out: for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				copyBoard[r + sr][c + sc] += puzzle[r][c];

				if (copyBoard[r + sr][c + sc] > 1) {
					isPoss = false;
					break out;
				}
			}
		}

		return isPoss ? x * y : INF;
	}

	private static void rotate(int[][][] puzzle) {
		int N = puzzle[0].length;
		int M = puzzle[0][0].length;

		puzzle[1] = new int[M][N];
		puzzle[2] = new int[N][M];
		puzzle[3] = new int[M][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				puzzle[1][M - 1 - c][r] = puzzle[0][r][c];
				puzzle[2][N - 1 - r][M - 1 - c] = puzzle[0][r][c];
				puzzle[3][c][N - 1 - r] = puzzle[0][r][c];
			}
		}
	}
}