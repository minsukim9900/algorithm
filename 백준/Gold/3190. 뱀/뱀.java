import java.io.*;
import java.util.*;

public class Main {
	private static int N, K, L;
	private static int[][] board;
	private static int[][] delta = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		board = new int[N][N];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			board[r][c] = 1;
		}

		L = Integer.parseInt(br.readLine());
		int[][] orders = new int[L][2];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int dir = 1;
			String str = st.nextToken();

			if (str.equals("L")) {
				dir = -1;
			}

			orders[i][0] = x;
			orders[i][1] = dir;
		}
		System.out.println(simulate(orders));
	}

	private static int simulate(int[][] orders) {
		int time = 0;
		Queue<int[]> snake = new ArrayDeque<>();
		board[0][0] = -1;
		snake.add(new int[] { 0, 0 });

		int idx = 0;
		int r = 0;
		int c = 0;
		int dir = 0;
		while (true) {

			if (idx < orders.length && time == orders[idx][0]) {
				dir = (dir + orders[idx][1] + 4) % 4;
				idx++;
			}

			r += delta[dir][0];
			c += delta[dir][1];

			if (!isRange(r, c)) {
				time++;
				break;
			} else {
				if (board[r][c] < 0) {
					time++;
					break;
				} else {
					snake.add(new int[] { r, c });
					if (board[r][c] == 0) {
						int[] tail = snake.poll();
						board[tail[0]][tail[1]] = 0;
					}
					board[r][c] = -1;
				}
			}
			time++;
		}
		return time;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}