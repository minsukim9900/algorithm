import java.io.*;
import java.util.*;

public class Main {

	private static int[][] delta = { { 0, 1 }, { 1, 1 }, { 1, 0 } };
	private static int N, result;
	private static int[][] board;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		visited = new boolean[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		visited[0][0] = true;
		visited[0][1] = true;
		dfs(0, 1, 0);
		System.out.println(result);

	}

	private static void dfs(int r, int c, int dir) {

		if (r == N - 1 && c == N - 1) {
			result++;
			return;
		}


		for (int i = -1; i < 2; i++) {
			int nd = dir + i;
			if (nd < 0 || nd > 2) {
				continue;
			}

			int nr = r + delta[nd][0];
			int nc = c + delta[nd][1];

			if (nr >= 00 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
				visited[r][c] = true;
				if ((nd == 0 || nd == 2) && board[nr][nc] == 0) {
					dfs(nr, nc, nd);
				} else if (nd == 1 && board[nr][nc] == 0 && board[nr - 1][nc] == 0 && board[nr][nc - 1] == 0) {
					dfs(nr, nc, nd);
				}
				visited[r][c] = false;
			}

		}

	}
}