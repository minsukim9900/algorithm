import java.io.*;
import java.util.*;

public class Main {
	private static int N, K;
	private static int[][] board;
	private static Deque<Integer>[][] dq;
	private static int[][] delta = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	private static int[][] state;
	private static final int END_OPTION = 4;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		dq = new ArrayDeque[N][N];
		state = new int[K + 1][3];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				dq[r][c] = new ArrayDeque<>();
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken()) - 1;
			state[i + 1][0] = r;
			state[i + 1][1] = c;
			state[i + 1][2] = dir;
			dq[r][c].add(i + 1);
		}
		System.out.println(result());
	}

	private static int result() {
		for (int i = 1; i <= 1000; i++) {
			for (int j = 1; j <= K; j++) {
				int count = simulate(j);
				if(count >= END_OPTION) {
					return i;
				}
			}
		}

		return -1;
	}

	private static int simulate(int num) {
		Deque<Integer> moving = new ArrayDeque<>();
		int r = state[num][0];
		int c = state[num][1];
		int dir = state[num][2];


		int nr = r + delta[dir][0];
		int nc = c + delta[dir][1];

		if (!isRange(nr, nc) || board[nr][nc] == 2) {
			dir ^= 1;
			state[num][2] = dir;
			nr = r + delta[dir][0];
			nc = c + delta[dir][1];

			if (!isRange(nr, nc) || board[nr][nc] == 2) {
				return dq[r][c].size();
			}
		}

		while (true) {
			int x = dq[r][c].pollLast();
			moving.add(x);
			if (x == num)
				break;
		}
		
		if (board[nr][nc] == 1) {
			while (!moving.isEmpty()) {
				int x = moving.pollFirst();
				state[x][0] = nr;
				state[x][1] = nc;
				dq[nr][nc].add(x);
			}
		} else if (board[nr][nc] == 0) {
			while (!moving.isEmpty()) {
				int x = moving.pollLast();
				state[x][0] = nr;
				state[x][1] = nc;
				dq[nr][nc].add(x);
			}
		}
		return dq[nr][nc].size();
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}