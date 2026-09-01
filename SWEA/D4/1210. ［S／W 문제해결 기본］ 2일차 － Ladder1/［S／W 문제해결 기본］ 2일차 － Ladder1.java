import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static int[][] board;
	private static int[][] delta = { { 0, -1 }, { 0, 1 }, { -1, 0 } };

	private static final int N = 100;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t < 11; t++) {
			int testCase = Integer.parseInt(br.readLine());
			sb.append("#").append(testCase).append(" ");

			board = new int[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					int num = Integer.parseInt(st.nextToken());

					board[r][c] = num;
				}
			}

			for (int c = 0; c < N; c++) {
				if (board[N - 1][c] == 2) {
					sb.append(bfs(N - 1, c)).append("\n");
				}
			}
		}

		System.out.println(sb.toString());
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static int bfs(int sr, int sc) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];

		q.add(new int[] { sr, sc });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			int r = curr[0];
			int c = curr[1];

			if (r == 0) {
				return c;
			}

			for (int i = 0; i < 3; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && board[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
					break;
				}
			}
		}

		return -1;
	}
}
