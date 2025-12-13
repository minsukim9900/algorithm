import java.io.*;
import java.util.*;

public class Main {
	private static int N, K;
	private static int[][] color, info;
	private static Deque<int[]>[][] board;
	private static int[][] delta = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		color = new int[N][N];
		info = new int[K][2];

		board = new ArrayDeque[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				color[r][c] = Integer.parseInt(st.nextToken());
				board[r][c] = new ArrayDeque<>();
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;
			info[i][0] = r;
			info[i][1] = c;
			board[r][c].add(new int[] { i, dir });
		}
		System.out.println(simulate());
	}

	private static int simulate() {

		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < K; j++) {
				int state = move(info[j][0], info[j][1], j);
				if (state >= 4) {
					return i + 1;
				}
			}
		}
		return -1;
	}

	private static int move(int r, int c, int index) {
		int[] under = board[r][c].peek();
		int idx = under[0];

		if (index != idx)
			return board[r][c].size();

		int dir = under[1];

		int nr = info[idx][0] + delta[dir][0];
		int nc = info[idx][1] + delta[dir][1];

		if (!isRange(nr, nc) || color[nr][nc] == 2) {
			int ndir = dir ^ 1;
			under[1] = ndir;
			int nnr = info[idx][0] + delta[ndir][0];
			int nnc = info[idx][1] + delta[ndir][1];

			if (!isRange(nnr, nnc) || color[nnr][nnc] == 2) {
				return board[r][c].size();
			} else {
				if(color[nnr][nnc] == 1) {
					redColor(r, c, nnr, nnc);
				} else {
					whiteColor(r, c, nnr, nnc);
				}
				return board[nnr][nnc].size();
			}

		} else if (color[nr][nc] == 1) {
			redColor(r, c, nr, nc);
			return board[nr][nc].size();
		} else {
			whiteColor(r, c, nr, nc);
			return board[nr][nc].size();
		}
	}

	private static void whiteColor(int r, int c, int nr, int nc) {
		while (!board[r][c].isEmpty()) {
			int[] curr = board[r][c].pollFirst();
			board[nr][nc].add(curr);
			info[curr[0]][0] = nr;
			info[curr[0]][1] = nc;
		}
	}

	private static void redColor(int r, int c, int nr, int nc) {
		while (!board[r][c].isEmpty()) {
			int[] curr = board[r][c].pollLast();
			board[nr][nc].add(curr);
			info[curr[0]][0] = nr;
			info[curr[0]][1] = nc;
		}
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}