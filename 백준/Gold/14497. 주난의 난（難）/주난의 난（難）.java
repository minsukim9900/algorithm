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

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { start[0], start[1] });
		int answer = INF;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];

			if (r == end[0] && c == end[1]) {
				answer = Math.min(dist[r][c], INF);
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc)) {
					int w = board[nr][nc] == '0' ? 0 : 1;

					if (dist[nr][nc] > dist[r][c] + w) {
						dist[nr][nc] = dist[r][c] + w;
						q.add(new int[] { nr, nc });
					}
				}

			}
		}
		return answer;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}