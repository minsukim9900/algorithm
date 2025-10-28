import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Main {
	private static int N = 5;
	private static int answer;
	private static final int MAX = 25;
	private static boolean[][] board;
	private static boolean[] visited = new boolean[1 << MAX + 1];
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		board = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				board[r][c] = str.charAt(c) == 'S' ? true : false;
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (board[r][c]) {
					int mask = 1 << (r * 5 + c);
					visited[mask] = true;
					dfs(1, 1, mask);
				}
			}
		}
		System.out.println(answer);
	}

	private static void dfs(int depth, int count, int mask) {
		if (7 - depth + count < 4) {
			return;
		}

		if (depth == 7 && count >= 4) {
			answer++;
			return;
		}

		for (int i = 0; i < MAX; i++) {
			if ((mask & (1 << i)) == 0)
				continue;

			int r = i / N;
			int c = i % N;

			for (int d = 0; d < 4; d++) {
				int nr = r + delta[d][0];
				int nc = c + delta[d][1];

				if (!isRange(nr, nc))
					continue;

				int next = nr * N + nc;
				if ((mask & (1 << next)) != 0)
					continue;
				
				int nextMask = mask | (1 << next);
				
				if(!visited[nextMask]) {
					visited[nextMask] = true;
					dfs(depth + 1, count + (board[nr][nc] ? 1: 0), nextMask);
				}
			}
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
