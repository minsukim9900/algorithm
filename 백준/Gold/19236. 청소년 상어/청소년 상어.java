import java.io.*;
import java.util.*;

public class Main {
	private static int[][] delta = { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 },
			{ -1, 1 } };

	private static int N = 4;
	private static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int[][] board = new int[N][N];
		int[][] fishes = new int[17][];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;

				board[r][c] = num;
				fishes[num] = new int[] { r, c, dir };
			}
		}

		int state = 0;
		state |= (1 << (board[0][0] - 1));
		answer = board[0][0];
		int start = board[0][0];
		board[0][0] = 17;
		dfs(0, 0, fishes[start][2], start, state, board, fishes);
		System.out.println(answer);
	}

	private static void dfs(int r, int c, int dir, int sum, int state, int[][] board, int[][] fishes) {
		answer = Math.max(answer, sum);

		int[][] copyBoard = copyB(board);
		int[][] copyFishes = copyF(fishes);

		moveFishes(copyBoard, copyFishes, state);
		int nr = r;
		int nc = c;

		for (int i = 0; i < 3; i++) {
			nr += delta[dir][0];
			nc += delta[dir][1];

			if (!isRange(nr, nc))
				break;

			if (copyBoard[nr][nc] > 0 && copyBoard[nr][nc] < 17) {
				copyBoard[r][c] = 0;
				int eat = copyBoard[nr][nc];
				copyBoard[nr][nc] = 17;
				dfs(nr, nc, copyFishes[eat][2], sum + eat, state | (1 << (eat - 1)), copyBoard, copyFishes);
				copyBoard[nr][nc] = eat;
				copyBoard[r][c] = 17;
			}
		}
	}

	private static void moveFishes(int[][] board, int[][] fishes, int state) {
		for (int i = 1; i <= 16; i++) {
			if ((state & (1 << (i - 1))) != 0)
				continue;

			int r = fishes[i][0];
			int c = fishes[i][1];
			int dir = fishes[i][2];

			for (int j = 0; j < 8; j++) {
				int nr = r + delta[dir][0];
				int nc = c + delta[dir][1];

				fishes[i][2] = dir;

				if (isRange(nr, nc)) {
					if (board[nr][nc] > 0 && board[nr][nc] < 17) {
						swapFishes(r, c, nr, nc, board, fishes);
						break;
					} else if (board[nr][nc] == 0) {
						swapSpace(r, c, nr, nc, board, fishes);
						break;
					}
				}

				dir = (dir + 1) % 8;
			}
		}
	}

	private static void swapSpace(int r, int c, int nr, int nc, int[][] board, int[][] fishes) {
		fishes[board[r][c]][0] = nr;
		fishes[board[r][c]][1] = nc;

		int tmp = board[r][c];
		board[r][c] = board[nr][nc];
		board[nr][nc] = tmp;
	}

	private static void swapFishes(int r, int c, int nr, int nc, int[][] board, int[][] fishes) {
		fishes[board[r][c]][0] = nr;
		fishes[board[r][c]][1] = nc;
		fishes[board[nr][nc]][0] = r;
		fishes[board[nr][nc]][1] = c;

		int tmp = board[r][c];
		board[r][c] = board[nr][nc];
		board[nr][nc] = tmp;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static int[][] copyB(int[][] board) {
		int[][] result = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				result[r][c] = board[r][c];
			}
		}
		return result;
	}

	private static int[][] copyF(int[][] fishes) {
		int[][] result = new int[fishes.length][];
		for (int i = 1; i < result.length; i++) {
			result[i] = fishes[i].clone();
		}
		return result;
	}
}