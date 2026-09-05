import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static int N;
	private static char[][] board;
	private static int[][] state;

	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 },
			{ 1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			sb.append("#").append(t).append(" ");

			N = Integer.parseInt(br.readLine());

			board = new char[N][N];
			state = new int[N][N];

			boolean[][] visited = new boolean[N][N];

			for (int r = 0; r < N; r++) {
				board[r] = br.readLine().toCharArray();
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (board[r][c] == '*') {
						state[r][c] = -1;

						for (int i = 0; i < 8; i++) {
							int nr = r + delta[i][0];
							int nc = c + delta[i][1];

							if (isRange(nr, nc) && board[nr][nc] == '.') {
								state[nr][nc]++;
							}
						}
					}
				}
			}

			int answer = 0;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (board[r][c] == '*' || state[r][c] > 0 || visited[r][c]) {
						continue;
					}

					bfs(r, c, visited);
					answer++;
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c] && state[r][c] != -1) {
						answer++;
					}
				}
			}

			sb.append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static void bfs(int sr, int sc, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();
		visited[sr][sc] = true;

		q.add(new int[] { sr, sc });

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];

			for (int i = 0; i < 8; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && state[nr][nc] != -1 && !visited[nr][nc]) {
					visited[nr][nc] = true;

					if (state[nr][nc] == 0) {
						q.add(new int[] { nr, nc });
					}
				}
			}
		}
	}
}
