import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static Set<Integer> set;

	private static final int N = 4;
	private static final int M = 7;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			sb.append("#").append(t).append(" ");

			board = new int[N][N];
			set = new HashSet<>();

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < N; c++) {
					int num = Integer.parseInt(st.nextToken());

					board[r][c] = num;
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					int[] result = new int[M];
					result[0] = board[r][c];

					dfs(r, c, 1, result);
				}
			}

			sb.append(set.size()).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static void dfs(int r, int c, int depth, int[] result) {
		if (depth == M) {
			int num = 0;

			for (int i = 0; i < M; i++) {
				num = num + result[i];
				num *= 10;
			}

			set.add(num);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (isRange(nr, nc)) {
				result[depth] = board[nr][nc];
				dfs(nr, nc, depth + 1, result);
			}
		}
	}
}
