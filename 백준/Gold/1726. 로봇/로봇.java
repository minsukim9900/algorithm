import java.io.*;
import java.util.*;

public class Main {
	private static int M, N;
	private static int[][] board;
	private static int[][] delta = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	private static final int INF = 100_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		board = new int[M][N];
		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken()) - 1;
		int sc = Integer.parseInt(st.nextToken()) - 1;
		int sDir = Integer.parseInt(st.nextToken()) - 1;

		st = new StringTokenizer(br.readLine());
		int er = Integer.parseInt(st.nextToken()) - 1;
		int ec = Integer.parseInt(st.nextToken()) - 1;
		int eDir = Integer.parseInt(st.nextToken()) - 1;
		
		System.out.println(bfs(sr, sc, sDir, er, ec, eDir));
	}

	private static int bfs(int sr, int sc, int sDir, int er, int ec, int eDir) {
		int[][][] dist = new int[4][M][N];

		for (int i = 0; i < 4; i++) {
			for (int r = 0; r < M; r++) {
				Arrays.fill(dist[i][r], INF);
			}
		}

		dist[sDir][sr][sc] = 0;

		Queue<int[]> q = new ArrayDeque<>();

		q.add(new int[] { sr, sc, sDir});

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			int r = curr[0];
			int c = curr[1];
			int dir = curr[2];
			int currDist = dist[dir][r][c];
			
			if (r == er && c == ec && eDir == dir) {
				return currDist;
			}

			for (int i = 1; i <= 3; i++) {
				int nr = r + (delta[dir][0] * i);
				int nc = c + (delta[dir][1] * i);

				if (!isRange(nr, nc) || board[nr][nc] == 1)
					break;

				if (dist[dir][nr][nc] > currDist + 1) {
					dist[dir][nr][nc] = currDist + 1;
					q.add(new int[] { nr, nc, dir});
				}
			}

			int newLeftDir = dir;
			int newRightDir = dir;
			for (int cost = 1; cost <= 3; cost++) {
				newLeftDir = changeLeftDir(newLeftDir);
				newRightDir = changeRightDir(newRightDir);

				if (dist[newLeftDir][r][c] > currDist + cost) {
					dist[newLeftDir][r][c] = currDist + cost;
					q.add(new int[] { r, c, newLeftDir});
				}

				if (dist[newRightDir][r][c] > currDist + cost) {
					dist[newRightDir][r][c] = currDist + cost;
					q.add(new int[] { r, c, newRightDir});
				}
			}
		}
		return -1;
	}

	private static int changeLeftDir(int dir) {
		switch (dir) {
		case 0:
			return 3;
		case 1:
			return 2;
		case 2:
			return 0;
		default:
			return 1;
		}
	}

	private static int changeRightDir(int dir) {
		switch (dir) {
		case 0:
			return 2;
		case 1:
			return 3;
		case 2:
			return 1;
		default:
			return 0;
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < M && c >= 0 && c < N;
	}
}