import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, cnt;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static boolean[][] visited, finished;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M];
		finished = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				board[r][c] = getDir(str.charAt(c));
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (visited[r][c]) {
					continue;
				}
				dfs(r, c, board[r][c]);
			}
		}
		System.out.println(cnt);
	}

	private static void dfs(int r, int c, int dir) {
		visited[r][c] = true;

		int nr = r + delta[dir][0];
		int nc = c + delta[dir][1];

		if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
			if (!visited[nr][nc]) {
				dfs(nr, nc, board[nr][nc]);
			} else if (!finished[nr][nc]) {
				cnt++;
			}

			finished[r][c] = true;
		}
	}

	private static int getDir(char c) {
		if (c == 'U') {
			return 0;
		} else if (c == 'D') {
			return 1;
		} else if (c == 'L') {
			return 2;
		} else {
			return 3;
		}
	}
}
