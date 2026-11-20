import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static char[][] board;
	private static int[][] stateBoard;

	private static final int[][] DELTA = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	private static final int INF = 100_000_000;

	private static void markGarbageSide() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (board[r][c] != 'g')
					continue;

				for (int[] d : DELTA) {
					int nr = r + d[0];
					int nc = c + d[1];

					if (isRange(nr, nc) && board[nr][nc] == '.') {
						board[nr][nc] = '*';
					}
				}
			}
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	private static int[][] bfs(int sr, int sc, boolean countGarbage, int[][] garbageDist) {

		int[][] dist = new int[N][M];

		for (int[] row : dist) {
			Arrays.fill(row, INF);
		}

		Deque<int[]> dq = new ArrayDeque<>();

		dist[sr][sc] = 0;
		dq.offerFirst(new int[] { sr, sc, 0 });

		while (!dq.isEmpty()) {
			int[] curr = dq.pollFirst();

			int r = curr[0];
			int c = curr[1];
			int cost = curr[2];

			if (cost != dist[r][c])
				continue;

			for (int[] d : DELTA) {
				int nr = r + d[0];
				int nc = c + d[1];

				if (!isRange(nr, nc))
					continue;

				int addGarbage = board[nr][nc] == 'g' ? 1 : 0;

				if (!countGarbage) {
					if (garbageDist[nr][nc] != garbageDist[r][c] + addGarbage) {
						continue;
					}
				}

				int weight = countGarbage ? addGarbage : (board[nr][nc] == '*' ? 1 : 0);

				int nextCost = cost + weight;

				if (dist[nr][nc] <= nextCost)
					continue;

				dist[nr][nc] = nextCost;

				if (weight == 0) {
					dq.offerFirst(new int[] { nr, nc, nextCost });
				} else {
					dq.offerLast(new int[] { nr, nc, nextCost });
				}
			}
		}

		return dist;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];
		stateBoard = new int[N][M];

		int sr = 0;
		int sc = 0;
		int er = 0;
		int ec = 0;

		for (int r = 0; r < N; r++) {
			String str = br.readLine();

			for (int c = 0; c < M; c++) {
				char state = str.charAt(c);
				board[r][c] = state;

				if (state == 'F') {
					er = r;
					ec = c;
				} else if (state == 'S') {
					sr = r;
					sc = c;
				}
			}
		}

		markGarbageSide();

		int[][] garbageDist = bfs(sr, sc, true, null);
		int[][] sideDist = bfs(sr, sc, false, garbageDist);

		System.out.println(garbageDist[er][ec] + " " + sideDist[er][ec]);
	}
}
