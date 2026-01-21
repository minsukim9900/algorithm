import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static char[][] board;
	private static final int INF = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];
		for (int r = 0; r < N; r++) {
			board[r] = br.readLine().toCharArray();
		}

		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken()) - 1;
		int sc = Integer.parseInt(st.nextToken()) - 1;

		st = new StringTokenizer(br.readLine());
		int er = Integer.parseInt(st.nextToken()) - 1;
		int ec = Integer.parseInt(st.nextToken()) - 1;
		if (M * N == 1) {
			System.out.println("NO");
			return;
		}
		System.out.println(bfs(sr, sc, er, ec));
	}

	private static String bfs(int sr, int sc, int er, int ec) {
		boolean[][] visited = new boolean[N][M];

		int count = 0;

		visited[sr][sc] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { sr, sc, 0 });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			int r = curr[0];
			int c = curr[1];
			int dist = curr[2];

			if (r == er && c == ec && dist > 0) {
				if (board[r][c] == 'X') {
					return "YES";
				} else {
					if (count > 0) {
						return "YES";
					} else {
						count++;
					}
				}
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (!isRange(nr, nc) || !(nr == er && nc == ec) && board[nr][nc] == 'X')
					continue;

				if (nr == er && nc == ec) {
					q.add(new int[] { nr, nc, dist + 1 });
					continue;
				}

				if (!visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc, dist + 1 });
				}
			}
		}

		return "NO";
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}