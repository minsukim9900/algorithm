import java.io.*;
import java.util.*;

public class Main {
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { -1, -1 }, { 1, -1 }, { -1, 1 }, { 0, 1 },
			{ 1, 1 } };

	private static int W, H;
	private static char[][] board;
	private static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		board = new char[H][W];
		int sr = 0;
		int sc = 0;
		int er = 0;
		int ec = 0;

		for (int r = 0; r < H; r++) {
			String str = br.readLine();
			for (int c = 0; c < W; c++) {
				char state = str.charAt(c);

				if (state == 'K') {
					sr = r;
					sc = c;
				} else if (state == '*') {
					er = r;
					ec = c;
				}

				board[r][c] = state;
			}
		}

		System.out.println(bfs(sr, sc, er, ec));
	}

	private static int bfs(int sr, int sc, int er, int ec) {

		int[][] dist = new int[H][W];
		Deque<int[]> dq = new ArrayDeque<>();

		for (int r = 0; r < H; r++) {
			Arrays.fill(dist[r], INF);
		}

		dist[sr][sc] = 0;

		dq.add(new int[] { sr, sc, 0 });

		while (!dq.isEmpty()) {
			int[] curr = dq.pollFirst();

			int r = curr[0];
			int c = curr[1];
			int w = curr[2];

			if (r == er && c == ec) {
				return w;
			}

			if (dist[r][c] != w)
				continue;

			for (int i = 0; i < 8; i++) {
				int nw = 1;
				if (i > 4) {
					nw = 0;
				}

				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && board[nr][nc] != '#' && dist[nr][nc] > w + nw) {
					dist[nr][nc] = w + nw;

					if (nw == 0) {
						dq.addFirst(new int[] { nr, nc, dist[nr][nc] });
					} else {
						dq.addLast(new int[] { nr, nc, dist[nr][nc] });
					}
				}
			}
		}

		return -1;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
}