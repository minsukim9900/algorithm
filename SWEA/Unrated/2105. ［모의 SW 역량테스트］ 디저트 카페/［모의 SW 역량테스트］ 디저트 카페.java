import java.io.*;
import java.util.*;

public class Solution {
	private static int N, max;
	private static int[][] board;
	private static int[][] delta = { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };
	private static boolean[][] visited;
	private static boolean[] dir;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			max = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					visited = new boolean[N][N];
					dir = new boolean[4];
					dfs(r, c, r, c, 0, 0, new boolean[101]);
				}
			}
			sb.append("#").append(t).append(" ").append(max = max == 0 ? -1 : max).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int r, int c, int sr, int sc, int count, int preDir, boolean[] tasted) {
		if (count > 0 && r == sr && c == sc) {
			dir[preDir] = true;
			if (check(sr, sc)) {
				max = Math.max(max, count);
			}
			dir[preDir] = false;
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (dir[i])
				continue;

			if (preDir != i) {
				dir[preDir] = true;
			}

			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (isRange(nr, nc) && !visited[nr][nc] && !tasted[board[nr][nc]]) {
				visited[nr][nc] = true;
				tasted[board[nr][nc]] = true;
				dfs(nr, nc, sr, sc, count + 1, i, tasted);
				visited[nr][nc] = false;
				tasted[board[nr][nc]] = false;
			}

			if (dir[preDir]) {
				dir[preDir] = false;
			}
		}

	}

	private static boolean check(int sr, int sc) {
		for (boolean v : dir) {
			if (!v)
				return false;
		}
		return true;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}