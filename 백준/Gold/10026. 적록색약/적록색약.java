import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static char[][] board;
	private static boolean[][] visited;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		board = new char[N][N];

		for (int r = 0; r < N; r++) {
			board[r] = br.readLine().toCharArray();
		}

		int cntA = 0;

		visited = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (visited[r][c])
					continue;
				cntA++;
				dfs(r, c, board[r][c], 0);

			}
		}
		sb.append(cntA).append(" ");

		int cntB = 0;
		visited = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (visited[r][c])
					continue;
				cntB++;
				dfs(r, c, board[r][c], 1);
			}
		}
		sb.append(cntB);
		System.out.println(sb.toString());
	}

	private static void dfs(int r, int c, char color, int state) {
		visited[r][c] = true;

		if (state == 0) {
			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] == color && !visited[nr][nc]) {
					dfs(nr, nc, color, state);
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					if (color != board[nr][nc]) {
						if ((color == 'R' && board[nr][nc] == 'G') || (color == 'G' && board[nr][nc] == 'R')) {
							dfs(nr, nc, color, state);
						}
					} else {
						dfs(nr, nc, color, state);
					}
				}
			}
		}
	}
}