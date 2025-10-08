import java.io.*;
import java.util.*;

public class Main {

	private static Map<Integer, int[][]> map;
	private static int N;
	private static int[][] board;
	private static int[][] delta = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		map = new HashMap<>();
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
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { N / 2, N / 2, 0, 0, 2 });
		int answer = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int dir = curr[2];
			int count = curr[3];
			int max = curr[4];

			// 토네이도 이동
			int nr = r + delta[dir][0];
			int nc = c + delta[dir][1];

			if (!isRange(nr, nc))
				break;

			answer += cal(nr, nc, dir);

			if ((max / 2) == (count + 1)) {
				q.add(new int[] { nr, nc, (dir + 1) % 4, count + 1, max });
				continue;
			}

			if (max == count + 1) {
				q.add(new int[] { nr, nc, (dir + 1) % 4, 0, max + 2 });
				continue;
			}

			q.add(new int[] { nr, nc, dir, count + 1, max });
		}
		return answer;
	}

	private static int cal(int r, int c, int dir) {
		int[][] info = map.get(dir);

		int total = board[r][c];
		
		if(total == 0) {
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

	private static void output() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(board[r][c] + " ");
			}
			System.out.println();
		}
	}

	private static void init() {
		map.put(0, new int[][] { { -1, 0, 7 }, { 1, 0, 7 }, { -2, 0, 2 }, { 2, 0, 2 }, { -1, -1, 10 }, { 1, -1, 10 },
				{ 0, -2, 5 }, { -1, 1, 1 }, { 1, 1, 1 }, { 0, -1 } });

		map.put(1, new int[][] { { 0, -1, 7 }, { 0, 1, 7 }, { 0, -2, 2 }, { 0, 2, 2 }, { 1, -1, 10 }, { 1, 1, 10 },
				{ 2, 0, 5 }, { -1, -1, 1 }, { -1, 1, 1 }, { 1, 0 } });

		map.put(2, new int[][] { { -1, 0, 7 }, { 1, 0, 7 }, { -2, 0, 2 }, { 2, 0, 2 }, { -1, 1, 10 }, { 1, 1, 10 },
				{ 0, 2, 5 }, { -1, -1, 1 }, { 1, -1, 1 }, { 0, 1 } });

		map.put(3, new int[][] { { 0, -1, 7 }, { 0, 1, 7 }, { 0, -2, 2 }, { 0, 2, 2 }, { -1, -1, 10 }, { -1, 1, 10 },
				{ -2, 0, 5 }, { 1, -1, 1 }, { 1, 1, 1 }, { -1, 0 } });
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
