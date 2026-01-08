
import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, H, W;
	private static int[][] prefix;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static final int INF = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		prefix = new int[N + 1][M + 1];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for (int c = 1; c <= M; c++) {
				sum += Integer.parseInt(st.nextToken());
				prefix[r][c] = sum + prefix[r - 1][c];
			}
		}

		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		int er = Integer.parseInt(st.nextToken());
		int ec = Integer.parseInt(st.nextToken());
		System.out.println(bfs(sr, sc, er, ec));
	}

	private static int bfs(int sr, int sc, int er, int ec) {
		int[][] dist = new int[N + 1][M + 1];
		for (int r = 0; r <= N; r++) {
			Arrays.fill(dist[r], INF);
		}
		dist[sr][sc] = 0;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { sr, sc });

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int count = 1;

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && dist[nr][nc] > dist[r][c] + 1) {
					dist[nr][nc] = dist[r][c] + 1;
					q.add(new int[] { nr, nc });
				}
			}
		}

		return dist[er][ec] == INF ? -1 : dist[er][ec];
	}

	private static boolean isRange(int r, int c) {
		int er = r + H - 1;
		int ec = c + W - 1;
		return r > 0 && r <= N && c > 0 && c <= M && er > 0 && er <= N && ec > 0 && ec <= M
				&& (prefix[er][ec] - prefix[r - 1][ec] - prefix[er][c - 1] + prefix[r - 1][c - 1] == 0);
	}
}
