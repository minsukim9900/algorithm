import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] board;
	private static int[][] dist;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		dist = new int[N][M];

		for (int r = 0; r < N; r++) {
			String nums = br.readLine();
			for (int c = 0; c < M; c++) {
				int tmp = nums.charAt(c) - '0';
				board[r][c] = tmp;
				dist[r][c] = N * M + 1;
			}

		}

		bfs();
		System.out.println(dist[N-1][M-1]);

	}

	private static void bfs() {
		dist[0][0] = 0;

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { 0, 0 });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {

					int tmp = dist[curr[0]][curr[1]] + board[nr][nc];

					if (dist[nr][nc] > tmp) {
						dist[nr][nc] = tmp;
						q.offer(new int[] { nr, nc });
					}
				}
			}
		}

	}

}