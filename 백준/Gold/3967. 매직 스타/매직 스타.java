import java.io.*;
import java.util.*;

public class Main {
	private static final int N = 5;
	private static final int M = 9;
	private static final int NUMBER = 26;
	private static int[][] board = new int[N][M];
	private static Map<Integer, int[]> map = new HashMap<>();
	static {
		map.put(1, new int[] { 0, 4 });
		map.put(2, new int[] { 1, 1 });
		map.put(3, new int[] { 1, 3 });
		map.put(4, new int[] { 1, 5 });
		map.put(5, new int[] { 1, 7 });
		map.put(6, new int[] { 2, 2 });
		map.put(7, new int[] { 2, 6 });
		map.put(8, new int[] { 3, 1 });
		map.put(9, new int[] { 3, 3 });
		map.put(10, new int[] { 3, 5 });
		map.put(11, new int[] { 3, 7 });
		map.put(12, new int[] { 4, 4 });
	}
	private static boolean isEnd = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		boolean[] visited = new boolean[13];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				char tmp = str.charAt(c);
				board[r][c] = tmp == '.' ? 0 : tmp == 'x' ? 0 : tmp - 'A' + 1;

				visited[board[r][c]] = true;
			}
		}

		dfs(1, visited);
	}

	private static void dfs(int depth, boolean[] visited) {
		if (isEnd)
			return;

		if (depth == 13) {
			StringBuilder sb = new StringBuilder();
			if (finalCheck()) {
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < M; c++) {
						if (board[r][c] == 0) {
							sb.append('.');
						} else {
							char x = (char) ('A' + board[r][c] - 1);
							sb.append(x);
						}
					}
					sb.append("\n");
				}

				System.out.println(sb.toString());
				isEnd = true;
				return;
			}
			return;
		} else {
			int[] info = map.get(depth);
			int r = info[0];
			int c = info[1];

			if (board[r][c] > 0 && check()) {
				dfs(depth + 1, visited);
				return;
			}

			for (int i = 1; i <= 12; i++) {

				if (visited[i])
					continue;

				board[r][c] = i;

				if (check()) {
					visited[i] = true;
					dfs(depth + 1, visited);
					visited[i] = false;
				}
				board[r][c] = 0;
			}
		}

	}

	private static boolean finalCheck() {
		int sumFirst = 0;
		int sumSecond = 0;
		for (int c = 1; c < M; c += 2) {
			sumFirst += board[1][c];
			sumSecond += board[3][c];

			if (sumFirst > NUMBER || sumSecond > NUMBER)
				return false;
		}

		if (sumFirst != NUMBER || sumSecond != NUMBER)
			return false;

		int sumThird = board[0][4] + board[1][3] + board[2][2] + board[3][1];
		if (sumThird != NUMBER)
			return false;

		int sumFourth = board[0][4] + board[1][5] + board[2][6] + board[3][7];
		if (sumFourth != NUMBER)
			return false;

		int sumFifth = board[1][1] + board[2][2] + board[3][3] + board[4][4];
		if (sumFifth != NUMBER)
			return false;

		int sumSixth = board[1][7] + board[2][6] + board[3][5] + board[4][4];
		if (sumSixth != NUMBER)
			return false;

		return true;
	}

	private static boolean check() {
		int sumFirst = 0;
		int sumSecond = 0;
		for (int c = 1; c < M; c += 2) {
			sumFirst += board[1][c];
			sumSecond += board[3][c];

			if (sumFirst > NUMBER || sumSecond > NUMBER)
				return false;
		}

		if (sumFirst > NUMBER || sumSecond > NUMBER)
			return false;

		int sumThird = board[0][4] + board[1][3] + board[2][2] + board[3][1];
		if (sumThird > NUMBER)
			return false;

		int sumFourth = board[0][4] + board[1][5] + board[2][6] + board[3][7];
		if (sumFourth > NUMBER)
			return false;

		int sumFifth = board[1][1] + board[2][2] + board[3][3] + board[4][4];
		if (sumFifth > NUMBER)
			return false;

		int sumSixth = board[1][7] + board[2][6] + board[3][5] + board[4][4];
		if (sumSixth > NUMBER)
			return false;

		return true;
	}
}