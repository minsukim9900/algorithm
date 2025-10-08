import java.io.*;
import java.util.*;

public class Main {

	private static int[][][] map;
	private static int N;
	private static int[][] board;
	private static int[][] delta = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		init();
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(simulate());
	}

	private static int simulate() {
		int r = N / 2, c = N / 2;
		int dir = 0;
		int step = 0;
		int segmentTwice = 2;
		int answer = 0;

		while (true) {
			r += delta[dir][0];
			c += delta[dir][1];
			// 토네이도 이동

			if (!isRange(r, c))
				break;

			answer += cal(r, c, dir);
			step++;
			if (step == segmentTwice / 2) {
				dir = (dir + 1) % 4;
			} else if (step == segmentTwice) {
				dir = (dir + 1) % 4;
				step = 0;
				segmentTwice += 2;
			}
		}
		return answer;
	}

	private static int cal(int r, int c, int dir) {
		int[][] info = map[dir];

		int total = board[r][c];

		if (total == 0) {
			return 0;
		}
		int sum = 0;
		int result = 0;

		for (int i = 0; i < info.length - 1; i++) {
			int nr = r + info[i][0];
			int nc = c + info[i][1];

			int percent = info[i][2];

			int tmp = total * percent / 100;

			if (!isRange(nr, nc)) {
				result += tmp;
			} else {
				board[nr][nc] += tmp;
			}
			sum += tmp;
		}

		int x = total - sum;
		int nr = r + info[info.length - 1][0];
		int nc = c + info[info.length - 1][1];
		if (!isRange(nr, nc)) {
			result += x;
		} else {
			board[nr][nc] += x;
		}

		board[r][c] = 0;
		return result;
	}

	private static void init() {
		map = new int[4][][];
		map[0] = new int[][] { { -1, 0, 7 }, { 1, 0, 7 }, { -2, 0, 2 }, { 2, 0, 2 }, { -1, -1, 10 }, { 1, -1, 10 },
				{ 0, -2, 5 }, { -1, 1, 1 }, { 1, 1, 1 }, { 0, -1 } };

		map[1] = new int[][] { { 0, -1, 7 }, { 0, 1, 7 }, { 0, -2, 2 }, { 0, 2, 2 }, { 1, -1, 10 }, { 1, 1, 10 },
				{ 2, 0, 5 }, { -1, -1, 1 }, { -1, 1, 1 }, { 1, 0 } };

		map[2] =  new int[][] { { -1, 0, 7 }, { 1, 0, 7 }, { -2, 0, 2 }, { 2, 0, 2 }, { -1, 1, 10 }, { 1, 1, 10 },
				{ 0, 2, 5 }, { -1, -1, 1 }, { 1, -1, 1 }, { 0, 1 } };

		map[3] = new int[][] { { 0, -1, 7 }, { 0, 1, 7 }, { 0, -2, 2 }, { 0, 2, 2 }, { -1, -1, 10 }, { -1, 1, 10 },
				{ -2, 0, 5 }, { 1, -1, 1 }, { 1, 1, 1 }, { -1, 0 } };
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
