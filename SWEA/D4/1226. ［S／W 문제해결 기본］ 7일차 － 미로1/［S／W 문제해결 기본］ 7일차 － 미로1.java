import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	private static final int N = 16;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < 10; t++) {
			int testCase = Integer.parseInt(br.readLine());

			board = new int[N][N];

			int sr = 0;
			int sc = 0;
			int er = 0;
			int ec = 0;

			for (int r = 0; r < N; r++) {
				String str = br.readLine();

				for (int c = 0; c < N; c++) {
					int state = str.charAt(c) - '0';

					board[r][c] = state;

					if (state == 2) {
						sr = r;
						sc = c;
					} else if (state == 3) {
						er = r;
						ec = c;
					}
				}
			}

			sb.append("#").append(testCase).append(" ").append(bfs(sr, sc, er, ec)).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static int bfs(int sr, int sc, int er, int ec) {
		Queue<int[]> q = new ArrayDeque<>();

		boolean[][] visited = new boolean[N][N];
		visited[sr][sc] = true;
		q.add(new int[] { sr, sc });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			int r = curr[0];
			int c = curr[1];

			if (r == er && c == ec) {
				return 1;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && !visited[nr][nc] && board[nr][nc] != 1) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}

		return 0;
	}
}
