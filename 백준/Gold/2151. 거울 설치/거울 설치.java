import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static char[][] board;

	private static final int[][][] DELTA = { { { -1, 0 }, { 1, 0 } }, { { 0, -1 }, { 0, 1 } } };
	private static final int INF = 100_000_000;

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static int bfs(int sr, int sc, int er, int ec) {
		Deque<int[]> dq = new ArrayDeque<>();
		int[][][] dist = new int[4][N][N];

		for (int dir = 0; dir < 4; dir++) {
			for (int r = 0; r < N; r++) {
				Arrays.fill(dist[dir][r], INF);
			}
		}

		for (int index = 0; index < 2; index++) {
			for (int dir = 0; dir < 2; dir++) {
				dist[index * 2 + dir][sr][sc] = 0;
				dq.add(new int[] { sr, sc, index, dir, 0 });
			}
		}

		while (!dq.isEmpty()) {
			int[] curr = dq.poll();

			int r = curr[0];
			int c = curr[1];
			int index = curr[2];
			int dir = curr[3];
			int step = curr[4];

			int nr = r + DELTA[index][dir][0];
			int nc = c + DELTA[index][dir][1];

			if (!isRange(nr, nc) || board[nr][nc] == '*') {
				continue;
			}

			if (dist[index * 2 + dir][nr][nc] > step) {
				dist[index * 2 + dir][nr][nc] = step;

				dq.addFirst(new int[] { nr, nc, index, dir, step });
			}

			if (board[nr][nc] == '!') {
				int nextIndex = index ^ 1;

				for (int nextDir = 0; nextDir < 2; nextDir++) {
					int nextState = nextIndex * 2 + nextDir;

					if (dist[nextState][nr][nc] > step + 1) {
						dist[nextState][nr][nc] = step + 1;

						dq.addLast(new int[] { nr, nc, nextIndex, nextDir, step + 1 });
					}
				}
			}
		}

		int result = INF;

		for (int i = 0; i < 4; i++) {
			result = Math.min(result, dist[i][er][ec]);
		}

		return result;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		board = new char[N][N];

		int[][] location = new int[2][2];
		int count = 0;

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				char state = str.charAt(c);

				board[r][c] = state;

				if (state == '#') {
					location[count][0] = r;
					location[count][1] = c;

					count++;
				}
			}
		}

		System.out.println(bfs(location[0][0], location[0][1], location[1][0], location[1][1]));
	}
}
