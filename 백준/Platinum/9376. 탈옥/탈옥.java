import java.io.*;
import java.util.*;

public class Main {
	private static int H, W;
	private static int[][] board;
	private static final int INF = 1_000_000_000;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			board = new int[H + 2][W + 2];
			int[][] prisoners = new int[2][2];
			int idx = 0;

			for (int r = 1; r <= H; r++) {
				String str = br.readLine();
				for (int c = 1; c <= W; c++) {
					char tmp = str.charAt(c - 1);
					board[r][c] = tmp == '*' ? 2 : tmp == '#' ? 1 : tmp == '$' ? 3 : 0;

					if (board[r][c] == 3) {
						prisoners[idx][0] = r;
						prisoners[idx++][1] = c;
						board[r][c] = 0;
					}
				}
			}

			int[][] dist1 = bfs(0, 0);
			int[][] dist2 = bfs(prisoners[0][0], prisoners[0][1]);
			int[][] dist3 = bfs(prisoners[1][0], prisoners[1][1]);

			int ans = INF;
			for (int r = 0; r < H + 2; r++) {
				for (int c = 0; c < W + 2; c++) {
					if (board[r][c] == 2)
						continue;

					if (dist1[r][c] == INF || dist2[r][c] == INF || dist3[r][c] == INF)
						continue;

					int sum = dist1[r][c] + dist2[r][c] + dist3[r][c];

					if (board[r][c] == 1) {
						sum -= 2;
					}

					ans = Math.min(ans, sum);
				}
			}
			System.out.println(ans);
		}
	}

	private static int[][] bfs(int sr, int sc) {
		int[][] dist = new int[H + 2][W + 2];
		for (int r = 0; r < H + 2; r++) {
			Arrays.fill(dist[r], INF);
		}

		Deque<int[]> dq = new ArrayDeque<>();
		dist[sr][sc] = 0;
		dq.add(new int[] { sr, sc });

		while (!dq.isEmpty()) {
			int[] curr = dq.pollFirst();
			int r = curr[0];
			int c = curr[1];
			int cost = dist[r][c];

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];
				if (isRange(nr, nc) && board[nr][nc] != 2 && dist[nr][nc] > cost + board[nr][nc]) {
					dist[nr][nc] = cost + board[nr][nc];
					if (board[nr][nc] == 0) {
						dq.addFirst(new int[] { nr, nc });
					} else {
						dq.addLast(new int[] { nr, nc });
					}
				}
			}
		}

		return dist;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < H + 2 && c >= 0 && c < W + 2;
	}

}