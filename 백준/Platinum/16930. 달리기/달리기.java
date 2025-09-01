import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, K;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		for (int r = 0; r < N; r++) {
			char[] ch = br.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				int num = 0;

				if (ch[c] == '#') {
					num = -1;
				}
				board[r][c] = num;
			}
		}

		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken()) - 1;
		int sc = Integer.parseInt(st.nextToken()) - 1;
		int er = Integer.parseInt(st.nextToken()) - 1;
		int ec = Integer.parseInt(st.nextToken()) - 1;

		System.out.println(bfs(sr, sc, er, ec));
	}

	private static int bfs(int sr, int sc, int er, int ec) {
		int[][] dist = new int[N][M];
		for (int r = 0; r < N; r++) {
			Arrays.fill(dist[r], INF);
		}

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { sr, sc });
		dist[sr][sc] = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			if (curr[0] == er && curr[1] == ec) {
				return dist[curr[0]][curr[1]];
			}

			for (int i = 0; i < 4; i++) {
				int nr = curr[0];
				int nc = curr[1];

				for (int j = 0; j < K; j++) {
					nr += delta[i][0];
					nc += delta[i][1];

					if (!isRange(nr, nc) || board[nr][nc] == -1)
						break;

					if (dist[nr][nc] < dist[curr[0]][curr[1]] + 1) {
						break;
					}

					if (dist[nr][nc] == dist[curr[0]][curr[1]] + 1)
						continue;

					dist[nr][nc] = dist[curr[0]][curr[1]] + 1;
					q.add(new int[] { nr, nc });
				}
			}
		}
		return -1;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}