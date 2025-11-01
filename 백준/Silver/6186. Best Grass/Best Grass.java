import java.io.*;
import java.util.*;

public class Main {
	private static int R, C;
	private static char[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];

		for (int r = 0; r < R; r++) {
			board[r] = br.readLine().toCharArray();
		}

		int answer = 0;
		boolean[][] visited = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (board[r][c] == '#' && !visited[r][c]) {
					bfs(r, c, visited);
					answer++;
				}
			}
		}
		System.out.println(answer);
	}

	private static void bfs(int r, int c, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c });
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && board[nr][nc] == '#' && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}