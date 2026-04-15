
import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static char[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	private static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];

		List<int[]> wolfs = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			String state = br.readLine();
			for (int c = 0; c < M; c++) {
				board[r][c] = state.charAt(c);

				if (board[r][c] == 'W') {
					wolfs.add(new int[] { r, c });
				}
			}
		}

		System.out.println(bfs(wolfs));
	}

	private static String bfs(List<int[]> wolfs) {

		int[][][] dist = new int[5][N][M];
		for (int i = 0; i < 5; i++) {
			for (int r = 0; r < N; r++) {
				Arrays.fill(dist[i][r], INF);
			}
		}

		Queue<int[]> q = new ArrayDeque<>();
		for (int[] wolf : wolfs) {
			dist[4][wolf[0]][wolf[1]] = 0;
			q.add(new int[] { wolf[0], wolf[1], 4 });
		}

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int dir = curr[2];

			if (dir == 4) {
				for (int i = 0; i < 4; i++) {
					int nr = r + delta[i][0];
					int nc = c + delta[i][1];

					if (isRange(nr, nc) && board[nr][nc] != '#') {

						int nextDir = dir;
						if (board[nr][nc] == '+') {
							nextDir = i;
						}

						if (dist[nextDir][nr][nc] > dist[dir][r][c] + 1) {
							dist[nextDir][nr][nc] = dist[dir][r][c] + 1;
							q.add(new int[] { nr, nc, nextDir });
						}
					}
				}
			} else {
				int nr = r + delta[dir][0];
				int nc = c + delta[dir][1];

				if (!isRange(nr, nc) || board[nr][nc] == '#') {
					if (dist[4][r][c] > dist[dir][r][c]) {
						dist[4][r][c] = dist[dir][r][c];
						q.add(new int[] { r, c, 4 });
					}
					continue;
				}

				if (isRange(nr, nc)) {
					if ((board[nr][nc] == '.' || board[nr][nc] == 'W') && dist[4][nr][nc] > dist[dir][r][c] + 1) {
						dist[4][nr][nc] = dist[dir][r][c] + 1;
						q.add(new int[] { nr, nc, 4 });
					} else if (board[nr][nc] == '+' && dist[dir][nr][nc] > dist[dir][r][c] + 1) {
						dist[dir][nr][nc] = dist[dir][r][c] + 1;
						q.add(new int[] { nr, nc, dir });
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				char temp = board[r][c];
				if (board[r][c] == '.') {
					boolean isPoss = false;
					for (int i = 0; i < 5; i++) {
						if (dist[i][r][c] != INF) {
							isPoss = true;
							break;
						}
					}

					if (!isPoss) {
						sb.append("P");
					} else {
						sb.append(".");
					}
				} else {
					sb.append(temp);
				}
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}