import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static final int INF = 100_000_000;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static char[][] board;
	private static int[] end;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];

		int[][] ball = new int[2][2];
		end = new int[2];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				board[r][c] = str.charAt(c);

				if (board[r][c] == 'R' || board[r][c] == 'B') {
					int num = board[r][c] == 'R' ? 0 : 1;
					ball[num] = new int[] { r, c };
					board[r][c] = '.';
				} else if (board[r][c] == 'O') {
					end[0] = r;
					end[1] = c;
				}
			}
		}

		System.out.println(simulate(ball));
	}

	private static int simulate(int[][] ball) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { ball[0][0], ball[0][1], ball[1][0], ball[1][1] });
		int[][][][] dist = new int[N][M][N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int r = 0; r < N; r++) {
					Arrays.fill(dist[i][j][r], INF);
				}
			}
		}
		dist[ball[0][0]][ball[0][1]][ball[1][0]][ball[1][1]] = 0;
		int result = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int rr = curr[0];
			int rc = curr[1];
			int br = curr[2];
			int bc = curr[3];

			for (int dir = 0; dir < 4; dir++) {
				int[] redState = move(rr, rc, dir);
				int nrr = redState[0];
				int nrc = redState[1];
				int rcount = redState[2];
				int rstate = redState[3];

				int[] blueState = move(br, bc, dir);
				int nbr = blueState[0];
				int nbc = blueState[1];
				int bcount = blueState[2];
				int bstate = blueState[3];

				if (bstate == 1)
					continue;

				if (rstate == 1) {
					result = dist[rr][rc][br][bc] + 1;
					return result > 10 ? 0 : 1;
				}

				if (nrr == nbr && nrc == nbc) {
					if (rcount > bcount) {
						nrr -= delta[dir][0];
						nrc -= delta[dir][1];
					} else {
						nbr -= delta[dir][0];
						nbc -= delta[dir][1];
					}
				}

				if (dist[nrr][nrc][nbr][nbc] > dist[rr][rc][br][bc] + 1) {
					dist[nrr][nrc][nbr][nbc] = dist[rr][rc][br][bc] + 1;
					q.add(new int[] { nrr, nrc, nbr, nbc });
				}
			}
		}
		return 0;
	}

	private static int[] move(int r, int c, int dir) {
		int count = 0;
		while (true) {
			int nr = r + delta[dir][0];
			int nc = c + delta[dir][1];

			if (board[nr][nc] == '#')
				break;
			r = nr;
			c = nc;
			count++;

			if (r == end[0] && c == end[1]) {
				return new int[] { r, c, count, 1 };
			}
		}
		return new int[] { r, c, count, 0 };
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}