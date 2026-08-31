import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static int N;
	private static int[][] board;

	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			sb.append("#").append(t).append(" ");

			N = Integer.parseInt(br.readLine());

			board = new int[N][N];

			int maxDay = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < N; c++) {
					int num = Integer.parseInt(st.nextToken());

					board[r][c] = num;

					maxDay = Math.max(maxDay, num);
				}

			}

			boolean[][] visited = new boolean[N][N];

			int answer = 0;

			for (int day = 0; day < maxDay + 1; day++) {
				int count = 0;

				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (board[r][c] > day && !visited[r][c]) {
							count++;
							visited[r][c] = true;
							bfs(r, c, day, visited);
						}
					}
				}

				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						visited[r][c] = false;
					}
				}

				answer = Math.max(answer, count);
			}

			sb.append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static void bfs(int sr, int sc, int day, boolean[][] visited) {
		visited[sr][sc] = true;

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { sr, sc });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			int r = curr[0];
			int c = curr[1];

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && !visited[nr][nc] && board[nr][nc] > day) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}
	}
}
