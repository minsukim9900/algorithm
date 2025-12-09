import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static char[][] board;
	private static final int INF = 100_000_000;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];

		int[] start = new int[2];
		int[] end = new int[2];
		st = new StringTokenizer(br.readLine());

		start[0] = Integer.parseInt(st.nextToken()) - 1;
		start[1] = Integer.parseInt(st.nextToken()) - 1;
		end[0] = Integer.parseInt(st.nextToken()) - 1;
		end[1] = Integer.parseInt(st.nextToken()) - 1;

		for (int r = 0; r < N; r++) {
			board[r] = br.readLine().toCharArray();
		}
		System.out.println(bfs(start, end));
	}

	private static int bfs(int[] start, int[] end) {
		int[][] dist = new int[N][M];
		for (int r = 0; r < N; r++) {
			Arrays.fill(dist[r], INF);
		}
		dist[start[0]][start[1]] = 0;

		Deque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { start[0], start[1] });
		int answer = INF;
		while (!dq.isEmpty()) {
			int[] curr = dq.poll();
			int r = curr[0];
			int c = curr[1];

			if (r == end[0] && c == end[1]) {
				return dist[r][c];
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc)) {
					int w = board[nr][nc] == '0' ? 0 : 1;

					if (dist[nr][nc] > dist[r][c] + w) {
						dist[nr][nc] = dist[r][c] + w;

						if (w == 0) {
							dq.addFirst(new int[] { nr, nc });
						} else {
							dq.addLast(new int[] { nr, nc });
						}

					}
				}

			}
		}
		return -1;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}