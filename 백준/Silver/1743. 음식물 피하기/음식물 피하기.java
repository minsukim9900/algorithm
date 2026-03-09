import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new int[N][M];

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			board[r][c] = 1;
		}

		boolean[][] visited = new boolean[N][M];
		int answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (board[r][c] == 1 && !visited[r][c]) {
					answer = Math.max(answer, bfs(r, c, visited));
				}
			}
		}
		System.out.println(answer);
	}

	private static int bfs(int sr, int sc, boolean[][] visited) {
		visited[sr][sc] = true;
		Queue<int[]> q = new ArrayDeque<>();

		q.add(new int[] { sr, sc });

		int result = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			result++;
			int r = curr[0];
			int c = curr[1];

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && board[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}

		return result;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}