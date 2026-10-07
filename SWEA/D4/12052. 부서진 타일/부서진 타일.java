import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int N, M, brokenTileCount;
	private static char[][] board;

	private static int[][] delta = { { 1, 0 }, { 0, 1 }, { 1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			brokenTileCount = 0;

			board = new char[N][M];

			for (int r = 0; r < N; r++) {
				String str = br.readLine();

				for (int c = 0; c < M; c++) {
					char state = str.charAt(c);

					if (state == '#') {
						brokenTileCount++;
					}

					board[r][c] = state;
				}
			}

			boolean[][] visited = new boolean[N][M];

			out: for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (board[r][c] == '#' && !visited[r][c]) {
						boolean state = bfs(r, c, visited);

						if (!state) {
							break out;
						}

						brokenTileCount -= 4;
					}
				}
			}

			sb.append(brokenTileCount == 0 ? "YES" : "NO").append("\n");
		}

		System.out.println(sb.toString());
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	private static boolean bfs(int r, int c, boolean[][] visited) {
		int count = 0;

		for (int i = 0; i < 3; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (isRange(nr, nc) && board[nr][nc] == '#' && !visited[nr][nc]) {
				count++;
			}
		}

		if (count < 3) {
			return false;
		}

		visited[r][c] = true;

		for (int i = 0; i < 3; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			visited[nr][nc] = true;
		}

		return true;
	}
}
