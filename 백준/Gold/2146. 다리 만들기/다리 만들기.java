import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static int[][] board;
	private static boolean[][] visited;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		visited = new boolean[N][N];
		int zr = -1, zc = -1;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if (board[r][c] == 0) {
					zr = r;
					zc = c;
				}
			}
		}

		int num = 1;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (visited[r][c] || board[r][c] == 0)
					continue;
				devideIsland(r, c, num++);
			}
		}

		List<int[]> surface = findSurface(zr, zc);
		int answer = Integer.MAX_VALUE;
		for (int[] s : surface) {
			answer = Math.min(answer, constructBridge(s, board[s[0]][s[1]]));
		}
		System.out.println(answer);
	}

	private static int constructBridge(int[] start, int point) {
		boolean[][] zero = new boolean[N][N];
		zero[start[0]][start[1]] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { start[0], start[1], 0 });
		int count = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			if (board[curr[0]][curr[1]] > 0 && board[curr[0]][curr[1]] != point) {
				count = curr[2] - 1;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && !zero[nr][nc] && board[nr][nc] != point) {
					zero[nr][nc] = true;
					q.add(new int[] { nr, nc, curr[2] + 1 });
				}
			}
		}
		return count;
	}

	private static List<int[]> findSurface(int r, int c) {
		boolean[][] zero = new boolean[N][N];
		zero[r][c] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c });
		List<int[]> surface = new ArrayList<>();

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && !zero[nr][nc]) {
					zero[nr][nc] = true;

					if (board[nr][nc] == 0) {
						q.add(new int[] { nr, nc });
					} else {
						surface.add(new int[] { nr, nc });
					}
				}
			}
		}
		return surface;
	}

	private static void devideIsland(int r, int c, int num) {
		visited[r][c] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c });
		board[r][c] = num;

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && !visited[nr][nc] && board[nr][nc] == 1) {
					visited[nr][nc] = true;
					board[nr][nc] = num;
					q.add(new int[] { nr, nc });
				}
			}
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}