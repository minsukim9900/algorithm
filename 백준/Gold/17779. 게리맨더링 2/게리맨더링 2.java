import java.io.*;
import java.util.*;

public class Main {
	private static int N, x, y, sum, answer;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		answer = INF;
		sum = 0;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				sum += board[r][c];
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				for (int d1 = 1; d1 <= c; d1++) {
					for (int d2 = 1; d2 < N - c; d2++) {
						if (r + d1 + d2 >= N)
							continue;
						x = r;
						y = c;
						checkArea(d1, d2);
					}
				}
			}
		}
		System.out.println(answer);
	}

	private static void checkArea(int d1, int d2) {
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;

		if (!checkFirstFourthBoundary(x, y, d1, visited)) {
			return;
		}
		if (!checkSecondThirdBoundary(x, y, d2, visited)) {
			return;
		}
		if (!checkSecondThirdBoundary(x + d1, y - d1, d2, visited)) {
			return;
		}
		if (!checkFirstFourthBoundary(x + d2, y + d2, d1, visited)) {
			return;
		}
		simulate(d1, d2, visited);
	}

	private static void simulate(int d1, int d2, boolean[][] area) {
		int max = 0;
		int min = INF;
		boolean[][] visited = new boolean[N][N];
		int five = sum;
		int result = bfs(0, 0, d1, d2, 1, area, visited);
		five -= result;
		max = Math.max(max, result);
		min = Math.min(min, result);

		result = bfs(0, N - 1, d1, d2, 2, area, visited);
		five -= result;
		max = Math.max(max, result);
		min = Math.min(min, result);

		result = bfs(N - 1, 0, d1, d2, 3, area, visited);
		five -= result;
		max = Math.max(max, result);
		min = Math.min(min, result);

		result = bfs(N - 1, N - 1, d1, d2, 4, area, visited);
		five -= result;
		max = Math.max(max, result);
		min = Math.min(min, result);

		max = Math.max(max, five);
		min = Math.min(min, five);
		answer = Math.min(answer, (max - min));
	}

	private static int bfs(int r, int c, int d1, int d2, int gu, boolean[][] area, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c });
		visited[r][c] = true;

		int result = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			result += board[curr[0]][curr[1]];

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isAreaRange(nr, nc, d1, d2, gu) && !visited[nr][nc] && !area[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}
		return result;
	}

	private static boolean checkFirstFourthBoundary(int r, int c, int dir, boolean[][] visited) {
		for (int i = 0; i <= dir; i++) {
			int nx = r + i;
			int ny = c - i;

			if (!isRange(nx, ny)) {
				return false;
			}
			visited[nx][ny] = true;
		}
		return true;
	}

	private static boolean checkSecondThirdBoundary(int r, int c, int dir, boolean[][] visited) {
		for (int i = 0; i <= dir; i++) {
			int nx = r + i;
			int ny = c + i;

			if (!isRange(nx, ny)) {
				return false;
			}
			visited[nx][ny] = true;
		}
		return true;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static boolean isAreaRange(int r, int c, int d1, int d2, int gu) {
		switch (gu) {
		case 1:
			return r >= 0 && r < (x + d1) && c >= 0 && c <= y;
		case 2:
			return r >= 0 && r <= (x + d2) && c > y && c < N;
		case 3:
			return r >= (x + d1) && r < N && c >= 0 && c < (y - d1 + d2);
		default:
			return r > (x + d2) && r < N && c >= (y - d1 + d2) && c < N;
		}
	}
}