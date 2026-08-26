import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	private static int N, M;
	private static char[][] board;

	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	private static final int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			board = new char[N][];

			List<int[]> waterLocation = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				board[r] = br.readLine().toCharArray();

				for (int c = 0; c < M; c++) {
					if (board[r][c] == 'W') {
						waterLocation.add(new int[] { r, c });
					}
				}
			}

			sb.append("#").append(t).append(" ").append(bfs(waterLocation)).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static int bfs(List<int[]> waterLocation) {
		int[][] dist = new int[N][M];

		for (int r = 0; r < N; r++) {
			Arrays.fill(dist[r], INF);
		}

		Queue<int[]> q = new ArrayDeque<>();

		for (int[] location : waterLocation) {
			int r = location[0];
			int c = location[1];

			dist[r][c] = 0;
			q.add(new int[] { r, c, 0 });
		}

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			int r = curr[0];
			int c = curr[1];
			int d = curr[2];

			if (d != dist[r][c]) {
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && dist[nr][nc] > d + 1) {
					dist[nr][nc] = d + 1;
					q.add(new int[] { nr, nc, d + 1 });
				}
			}
		}

		int result = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				result += dist[r][c];
			}
		}

		return result;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
