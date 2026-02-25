import java.io.*;
import java.util.*;

public class Main {
	private static int R, C, M;
	private static PriorityQueue<Shark>[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new PriorityQueue[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				board[r][c] = new PriorityQueue<>((a, b) -> Integer.compare(b.z, a.z));
			}
		}

		List<int[]> sharks = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());

			sharks.add(new int[] { r, c, d, s, z });
			board[r][c].add(new Shark(s, z, d));
		}

		System.out.println(simulate(sharks));
	}

	private static void moveShark() {
		List<int[]> tempInfo = new ArrayList<>();

		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (board[r][c].isEmpty()) {
					continue;
				}

				Shark shark = board[r][c].poll();

				while (!board[r][c].isEmpty()) {
					board[r][c].poll();
				}

				int d = shark.d;
				int s = shark.s;
				int z = shark.z;

				if (d == 0 || d == 1) {
					s %= (2 * (R - 1));
				} else {
					s %= (2 * (C - 1));
				}

				int nr = r, nc = c;

				for (int i = 0; i < s; i++) {
					int tr = nr + delta[d][0];
					int tc = nc + delta[d][1];

					if (tr < 1 || tr > R || tc < 1 || tc > C) {
						d ^= 1;
						tr = nr + delta[d][0];
						tc = nc + delta[d][1];
					}

					nr = tr;
					nc = tc;
				}

				tempInfo.add(new int[] { nr, nc, d, s, z });
			}
		}

		for (int[] info : tempInfo) {
			int r = info[0];
			int c = info[1];
			int d = info[2];
			int s = info[3];
			int z = info[4];
			board[r][c].add(new Shark(s, z, d));
		}
	}

	private static int simulate(List<int[]> sharks) {
		int answer = 0;

		for (int c = 1; c <= C; c++) {
			answer += searchShark(c);
			moveShark();
		}

		return answer;
	}

	private static int searchShark(int c) {
		for (int r = 1; r <= R; r++) {
			if (board[r][c].isEmpty())
				continue;
			int answer = board[r][c].poll().z;

			while (!board[r][c].isEmpty()) {
				board[r][c].poll();
			}
			return answer;
		}
		return 0;
	}

	private static boolean isRange(int r, int c) {
		return r >= 1 && r <= R && c >= 1 && c <= C;
	}

	private static class Shark {
		int s, z, d;

		public Shark(int s, int z, int d) {
			this.s = s;
			this.z = z;
			this.d = d;
		}
	}
}