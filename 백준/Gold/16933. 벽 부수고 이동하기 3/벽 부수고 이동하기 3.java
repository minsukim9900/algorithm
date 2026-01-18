import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static char[][] board;
	private static final int INF = 100_000_000;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new char[N][M];
		for (int r = 0; r < N; r++) {
			board[r] = br.readLine().toCharArray();
		}

		System.out.println(bfs());
	}

	private static int bfs() {
		int[][][][] dist = new int[2][K + 1][N][M];
		for (int i = 0; i < 2; i++) {
			for (int k = 0; k < K + 1; k++) {
				for (int r = 0; r < N; r++) {
					Arrays.fill(dist[i][k][r], INF);
				}
			}
		}

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { 0, 0, 0, K });
		dist[0][K][0][0] = 1;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int state = curr[2];
			int skill = curr[3];
			int currDist = dist[state][skill][r][c];

			if (r == N - 1 && c == M - 1) {
				return dist[state][skill][r][c];
			}

			if (dist[state ^ 1][skill][r][c] > currDist + 1) {
				dist[state ^ 1][skill][r][c] = currDist + 1;
				q.add(new int[] { r, c, state ^ 1, skill });
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (!isRange(nr, nc))
					continue;

				if (board[nr][nc] == '0') {
					if (dist[state ^ 1][skill][nr][nc] > currDist + 1) {
						dist[state ^ 1][skill][nr][nc] = currDist + 1;
						q.add(new int[] { nr, nc, state ^ 1, skill });
					}
				} else {
					if (state == 0 && skill > 0) {
						if (dist[state ^ 1][skill - 1][nr][nc] > currDist + 1) {
							dist[state ^ 1][skill - 1][nr][nc] = currDist + 1;
							q.add(new int[] { nr, nc, state ^ 1, skill - 1 });
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