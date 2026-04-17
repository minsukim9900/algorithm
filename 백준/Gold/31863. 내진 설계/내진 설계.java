
import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static char[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		List<int[]> start = new ArrayList<>();

		board = new char[N][M];

		int count = 0;

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				board[r][c] = str.charAt(c);

				if (board[r][c] == '@') {
					start.add(new int[] { r, c, 2 });
				} else if (board[r][c] == '*' || board[r][c] == '#') {
					count++;
				}
			}
		}
		int result = simulate(start);
		System.out.println(result + " " + (count - result));
	}

	private static int simulate(List<int[]> start) {
		int result = 0;

		Queue<int[]> q = new ArrayDeque<>();
		for (int[] x : start) {
			q.add(x);
		}

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			int r = curr[0];
			int c = curr[1];
			int depth = curr[2];

			if (depth == 0) {
				continue;
			}

			for (int i = 0; i < 4; i++) {
				for (int j = 1; j <= depth; j++) {
					int nr = r + delta[i][0] * j;
					int nc = c + delta[i][1] * j;

					if (!isRange(nr, nc) || board[nr][nc] == '|') {
						break;
					}

					if (board[nr][nc] == '*') {
						board[nr][nc] = '.';
						result++;
						q.add(new int[] { nr, nc, 1 });

					} else if (board[nr][nc] == '#') {
						board[nr][nc] = '*';
					}
				}
			}
		}

		return result;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}