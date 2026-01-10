import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[][] board;
	private static int[][] delta = { { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

	private static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				char x = str.charAt(c);
				board[r][c] = x == '/' ? 1 : 0;
			}
		}
		int answer = simulate();
		System.out.println(answer == -1 ? "NO SOLUTION" : answer);
	}

	private static int simulate() {
		int[][] dist = new int[N + 1][M + 1];
		for (int r = 0; r <= N; r++) {
			Arrays.fill(dist[r], INF);
		}

		dist[0][0] = 0;

		Deque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { 0, 0 });

		while (!dq.isEmpty()) {
			int[] curr = dq.pollFirst();
			int r = curr[0];
			int c = curr[1];

			if (r == N && c == M) {
				return dist[r][c];
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc)) {
					int mr = Math.min(r, nr);
					boolean state = ((nr - r) == (nc - c));
					int mc = state ? Math.min(c, nc) : Math.max(c, nc) - 1;
					int w = state ? board[mr][mc] == 0 ? 0 : 1 : board[mr][mc] == 1 ? 0 : 1;

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
		return r >= 0 && r <= N && c >= 0 && c <= M;
	}
}