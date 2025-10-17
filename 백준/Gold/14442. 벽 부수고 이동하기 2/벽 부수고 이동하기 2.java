import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				board[r][c] = str.charAt(c) - '0';
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[K + 1][N][M];
		q.add(new int[] { 0, 0, 1, K });
		visited[K][0][0] = true;
		int ans = INF;

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			if (curr[0] == N - 1 && curr[1] == M - 1) {
				ans = Math.min(ans, curr[2]);
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc)) {
					if (board[nr][nc] == 1) {
						if (curr[3] > 0 && !visited[curr[3] - 1][nr][nc]) {
							visited[curr[3] - 1][nr][nc] = true;
							q.add(new int[] { nr, nc, curr[2] + 1, curr[3] - 1 });
						}
					} else if (board[nr][nc] == 0 && !visited[curr[3]][nr][nc]) {
						visited[curr[3]][nr][nc] = true;
						q.add(new int[] { nr, nc, curr[2] + 1, curr[3] });
					}
				}
			}
		}
		return ans == INF ? -1 : ans;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
