import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static int[][][] board;
	private static int[][] passenger;
	private static int[] taxi;
	private static int[] dist;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new int[M + 1][N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int state = Integer.parseInt(st.nextToken());

				for (int i = 0; i < M + 1; i++) {
					board[i][r][c] = state == 1 ? -1 : state;
				}
			}
		}
		taxi = new int[2];
		st = new StringTokenizer(br.readLine());
		taxi[0] = Integer.parseInt(st.nextToken()) - 1;
		taxi[1] = Integer.parseInt(st.nextToken()) - 1;

		passenger = new int[M + 1][4];
		for (int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken()) - 1;
			int sc = Integer.parseInt(st.nextToken()) - 1;
			int er = Integer.parseInt(st.nextToken()) - 1;
			int ec = Integer.parseInt(st.nextToken()) - 1;
			board[0][sr][sc] = i;
			passenger[i][0] = sr;
			passenger[i][1] = sc;
			passenger[i][2] = er;
			passenger[i][3] = ec;
			calDistination(sr, sc, er, ec, i);
		}
		System.out.println(simulate());
	}

	private static int simulate() {
		boolean[] isUsed = new boolean[M + 1];
		boolean isPoss = true;
		int cnt = 0;

		while (cnt < M) {
			int sr = taxi[0];
			int sc = taxi[1];

			Queue<int[]> q = new ArrayDeque<>();
			q.add(new int[] { sr, sc, 0 });
			boolean[][] visited = new boolean[N][N];
			visited[sr][sc] = true;

			PriorityQueue<int[]> pq = new PriorityQueue<>(
					(a, b) -> a[3] == b[3] ? a[1] == b[1] ? a[2] - b[2] : a[1] - b[1] : a[3] - b[3]);
			while (!q.isEmpty()) {
				int[] curr = q.poll();
				int r = curr[0];
				int c = curr[1];
				int d = curr[2];

				if (board[0][r][c] > 0 && !isUsed[board[0][r][c]]) {
					pq.add(new int[] { board[0][r][c], r, c, d });
					continue;
				}

				for (int i = 0; i < 4; i++) {
					int nr = r + delta[i][0];
					int nc = c + delta[i][1];

					if (isRange(nr, nc) && board[0][nr][nc] != -1 && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.add(new int[] { nr, nc, d + 1 });
					}
				}
			}

			if (pq.isEmpty()) {
				isPoss = false;
				break;
			} else {
				int[] p = pq.poll();
				int idx = p[0];
				int d1 = p[3];
				int d2 = board[idx][passenger[idx][2]][passenger[idx][3]] - 1;
				if (d2 < 0) {
					isPoss = false;
					break;
				}

				int gas = d1 + d2;

				if (gas <= K) {
					cnt++;
					K -= gas;
					K += (d2 * 2);
					taxi[0] = passenger[idx][2];
					taxi[1] = passenger[idx][3];
					isUsed[idx] = true;
				} else {
					isPoss = false;
					break;
				}
			}
		}

		return isPoss ? K : -1;
	}

	private static void calDistination(int sr, int sc, int er, int ec, int idx) {
		Queue<int[]> q = new ArrayDeque<>();
		board[idx][sr][sc] = 1;
		q.add(new int[] { sr, sc, 1 });

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int d = curr[2];

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && board[idx][nr][nc] == 0) {
					board[idx][nr][nc] = d + 1;
					q.add(new int[] { nr, nc, d + 1 });
				}
			}
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}