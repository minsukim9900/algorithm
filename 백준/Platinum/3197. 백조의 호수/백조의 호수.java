import java.io.*;
import java.util.*;

public class Main {
	private static int R, C;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static List<int[]> bird;
	private static Queue<int[]> waterQ, waterNextQ, swanQ, swanNextQ;
	private static boolean[][] swanVisited;
	private static int[] S, E;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new int[R][C];
		S = new int[2];
		E = new int[2];

		waterQ = new ArrayDeque<>();
		waterNextQ = new ArrayDeque<>();
		swanQ = new ArrayDeque<>();
		swanNextQ = new ArrayDeque<>();
		swanVisited = new boolean[R][C];
		bird = new ArrayList<>();

		for (int r = 0; r < R; r++) {
			String str = br.readLine();

			for (int c = 0; c < C; c++) {
				board[r][c] = (str.charAt(c) == '.') ? 0 : str.charAt(c) == 'L' ? 2 : 1;

				if (board[r][c] == 2) {
					board[r][c] = 0;
					bird.add(new int[] { r, c });
					waterQ.add(new int[] { r, c });
				} else if (board[r][c] == 0) {
					waterQ.add(new int[] { r, c });
				}
			}
		}

		S = bird.get(0);
		E = bird.get(1);

		swanQ.add(S);
		swanVisited[S[0]][S[1]] = true;

		boolean isEnd = false;

		int ans = 0;
		while (!isEnd) {
			isEnd = meetBird();
			if (isEnd)
				break;

			meltToday();
			waterQ = waterNextQ;
			waterNextQ = new ArrayDeque<>();
			swanQ = swanNextQ;
			swanNextQ = new ArrayDeque<>();
			ans++;
		}
		System.out.println(ans);
	}

	private static boolean meetBird() {
		while (!swanQ.isEmpty()) {
			int[] curr = swanQ.poll();

			if (curr[0] == E[0] && curr[1] == E[1])
				return true;

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && !swanVisited[nr][nc]) {
					swanVisited[nr][nc] = true;

					if (board[nr][nc] == 0) {
						swanQ.add(new int[] { nr, nc });
					} else {
						swanNextQ.add(new int[] { nr, nc });
					}
				}
			}
		}
		return false;
	}

	private static void meltToday() {
		while (!waterQ.isEmpty()) {
			int[] curr = waterQ.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && board[nr][nc] == 1) {
					board[nr][nc] = 0;
					waterNextQ.add(new int[] { nr, nc });
				}
			}
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}
