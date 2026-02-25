import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[][] board;
	private static final int INF = 100_000_000;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];

		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken()) - 1;
		int sc = Integer.parseInt(st.nextToken()) - 1;
		int er = Integer.parseInt(st.nextToken()) - 1;
		int ec = Integer.parseInt(st.nextToken()) - 1;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = bfs(sr, sc, er, ec);
		System.out.println(answer);
	}

	private static int bfs(int sr, int sc, int er, int ec) {
		int[][][] dist = new int[3][N][M];

		int[][] deltaRange = new int[3][2];
		deltaRange[0][0] = 0;
		deltaRange[0][1] = 4;
		deltaRange[1][0] = 0;
		deltaRange[1][1] = 2;
		deltaRange[2][0] = 2;
		deltaRange[2][1] = 4;

		for (int i = 0; i < 3; i++) {
			for (int r = 0; r < N; r++) {
				Arrays.fill(dist[i][r], INF);
			}
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[3], b[3]));
		pq.add(new int[] { sr, sc, 0, 0 });
		dist[0][sr][sc] = 0;

		int answer = INF;
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			int r = curr[0];
			int c = curr[1];
			int d = curr[2];
			int weight = curr[3];

			if (dist[d][r][c] != weight)
				continue;

			if (r == er && c == ec) {
				return dist[d][r][c];
			}

			int idx = (d + 1) % 3;
			int[] range = deltaRange[(d + 1) % 3];

			for (int i = range[0]; i < range[1]; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && board[nr][nc] != -1 && dist[idx][nr][nc] > dist[d][r][c] + board[nr][nc]) {
					dist[idx][nr][nc] = dist[d][r][c] + board[nr][nc];
					pq.add(new int[] { nr, nc, idx, dist[idx][nr][nc] });
				}
			}
		}
		return -1;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}