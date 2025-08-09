import java.io.*;
import java.util.*;

public class Solution {
	private static int N;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static List<int[]>[] teleport;
	private static int[][] pairs;
	private static final int[][] REFLECT = { { 0, 0, 0, 0 }, { 1, 3, 0, 2 }, { 3, 0, 1, 2 }, { 2, 0, 3, 1 },
			{ 1, 2, 3, 0 }, { 1, 0, 3, 2 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N][N];

			List<int[]> loc = new ArrayList<>();
			teleport = new ArrayList[5];
			for (int i = 0; i < 5; i++) {
				teleport[i] = new ArrayList<>();
			}

			pairs = new int[5][4];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());

					if (board[r][c] == 0) {
						loc.add(new int[] { r, c });
					}

					if (board[r][c] >= 6 && board[r][c] <= 10) {
						teleport[board[r][c] - 6].add(new int[] { r, c });
					}
				}
			}

			for (int i = 0; i < 5; i++) {
				if (!teleport[i].isEmpty()) {
					int[] portal1 = teleport[i].get(0);
					int[] portal2 = teleport[i].get(1);
					pairs[i][0] = portal2[0];
					pairs[i][1] = portal2[1];
					pairs[i][2] = portal1[0];
					pairs[i][3] = portal1[1];
				}
			}

			int answer = 0;

			for (int[] start : loc) {
				for (int i = 0; i < 4; i++) {
					answer = Math.max(answer, simulate(start, i, false));
				}
			}

			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int simulate(int[] start, int dir, boolean isStart) {
		int count = 0;
		int[] curr = new int[] { start[0], start[1], dir };

		while (true) {
			if (isStart && start[0] == curr[0] && start[1] == curr[1]) {
				break;
			}

			isStart = true;

			curr[0] += delta[curr[2]][0];
			curr[1] += delta[curr[2]][1];

			if (!isRange(curr[0], curr[1])) {
				int d = REFLECT[5][curr[2]];
				count++;
				curr = new int[] { curr[0], curr[1], d };
				continue;
			}

			if (board[curr[0]][curr[1]] >= 6 && board[curr[0]][curr[1]] <= 10) {
				int r = pairs[board[curr[0]][curr[1]] - 6][0];
				int c = pairs[board[curr[0]][curr[1]] - 6][1];

				if (curr[0] == r && curr[1] == c) {
					r = pairs[board[curr[0]][curr[1]] - 6][2];
					c = pairs[board[curr[0]][curr[1]] - 6][3];
				}

				curr = new int[] { r, c, curr[2] };
				continue;
			}

			if (board[curr[0]][curr[1]] == -1) {
				break;
			}

			if (board[curr[0]][curr[1]] >= 1 && board[curr[0]][curr[1]] <= 5) {
				int d = REFLECT[board[curr[0]][curr[1]]][curr[2]];
				curr = new int[] { curr[0], curr[1], d };
				count++;
				continue;
			}

			curr = new int[] { curr[0], curr[1], curr[2] };
		}
		return count;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}