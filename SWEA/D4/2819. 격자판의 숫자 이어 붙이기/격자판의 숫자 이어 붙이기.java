import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int answer;
	private static int[] visited;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	private static final int N = 4;
	private static final int M = 7;
	private static final int MAX_NUM = 10_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			sb.append("#").append(t).append(" ");

			board = new int[N][N];
			answer = 0;
			visited = new int[(MAX_NUM >> 5) + 1];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < N; c++) {
					int num = Integer.parseInt(st.nextToken());

					board[r][c] = num;
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					dfs(r, c, 1, board[r][c]);
				}
			}

			sb.append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static boolean isVisited(int num) {
		int index = num >> 5;
		int mask = num & 31;

		boolean result = ((visited[index] & (1 << mask)) != 0);

		if (!result) {
			visited[index] |= (1 << mask);
		}

		return result;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static void dfs(int r, int c, int depth, int num) {
		if (depth == M) {
			if (!isVisited(num)) {
				answer++;
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (isRange(nr, nc)) {
				dfs(nr, nc, depth + 1, num * 10 + board[nr][nc]);
			}
		}
	}
}
