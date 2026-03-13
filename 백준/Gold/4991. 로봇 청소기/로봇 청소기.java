import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static char[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());

			if (M == 0)
				break;
			N = Integer.parseInt(st.nextToken());

			board = new char[N][M];
			int sr = 0;
			int sc = 0;

			int count = 0;
			char tmp = 'a';
			for (int r = 0; r < N; r++) {
				String temp = br.readLine();
				for (int c = 0; c < M; c++) {
					board[r][c] = temp.charAt(c);

					if (board[r][c] == 'o') {
						sr = r;
						sc = c;
					} else if (board[r][c] == '*') {
						board[r][c] = tmp;
						tmp++;
						count++;
					}
				}
			}

			System.out.println(bfs(sr, sc, count));
		}
	}

	private static int bfs(int sr, int sc, int count) {
		int end = (1 << count) - 1;
		boolean[][][] visited = new boolean[1 << count][N][M];
		visited[0][sr][sc] = true;

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { sr, sc, 0, 0 });

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int dist = curr[2];
			int state = curr[3];

			if (state == end) {
				return dist;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (!isRange(nr, nc) || board[nr][nc] == 'x')
					continue;

				int nextState = state;
				if (board[nr][nc] >= 'a' && board[nr][nc] < 'a' + count) {
					nextState |= 1 << (board[nr][nc] - 'a');
				}

				if (visited[nextState][nr][nc])
					continue;
				visited[nextState][nr][nc] = true;
				q.add(new int[] { nr, nc, dist + 1, nextState });
			}
		}

		return -1;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}