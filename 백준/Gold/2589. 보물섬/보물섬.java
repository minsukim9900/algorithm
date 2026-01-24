import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static char[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];

		for (int r = 0; r < N; r++) {
			board[r] = br.readLine().toCharArray();
		}

		int answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (board[r][c] == 'L') {
					answer = Math.max(answer, bfs(r, c));
				}
			}
		}
		System.out.println(answer);
	}

	private static int bfs(int sr, int sc) {
		boolean[][] visited = new boolean[N][M];
		visited[sr][sc] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { sr, sc, 0 });
		int maxTime = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int time = curr[2];

			maxTime = Math.max(maxTime, time);

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && board[nr][nc] == 'L' && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc, time + 1 });
				}
			}
		}

		return maxTime;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}