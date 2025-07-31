import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static char[][] board;
	private static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				board[r][c] = str.charAt(c);
			}
		}

		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken()) - 1;
		int sc = Integer.parseInt(st.nextToken()) - 1;
		int[] result = simulate(sr, sc);

		switch (result[0]) {
		case 0:
			System.out.println("U");
			break;
		case 1:
			System.out.println("R");
			break;
		case 2:
			System.out.println("D");
			break;
		case 3:
			System.out.println("L");
			break;
		}
		if (result[1] == -1) {
			System.out.println("Voyager");
			return;
		}
		System.out.println(result[1]);
	}

	private static int[] simulate(int r, int c) {
		int dir = 0;
		int max = 0;

		Queue<int[]> q = new ArrayDeque<>();

		for (int i = 0; i < 4; i++) {
			q.add(new int[] { r, c, i, 1 });

			while (!q.isEmpty()) {
				int[] curr = q.poll();

				int nr = curr[0] + delta[curr[2]][0];
				int nc = curr[1] + delta[curr[2]][1];

				if (curr[3] > 500 * 500 * 2) {
					return new int[] { i, -1 };
				}

				if (max < curr[3]) {
					dir = i;
					max = curr[3];
				}

				if (isRange(nr, nc) && board[nr][nc] != 'C') {
					if (board[nr][nc] == '/') {
						int num = 1;
						if (curr[2] % 2 == 0) {
							num = -1;
						}
						curr[2] = (curr[2] + ((-1) * num) + 4) % 4;
					} else if (board[nr][nc] == '\\') {
						int num = 1;
						if (curr[2] % 2 == 0) {
							num = -1;
						}
						curr[2] = (curr[2] + (1 * num) + 4) % 4;
					}
					q.add(new int[] { nr, nc, curr[2], curr[3] + 1 });
				}
			}
		}
		return new int[] { dir, max };
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}