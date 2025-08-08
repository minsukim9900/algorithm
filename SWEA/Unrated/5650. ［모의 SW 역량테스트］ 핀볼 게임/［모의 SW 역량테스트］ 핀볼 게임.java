import java.io.*;
import java.util.*;

public class Solution {
	private static int N;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static List<int[]>[] teleport;
	private static Map<Integer, Integer> infos;

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
			infos = new HashMap<>();

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());

					if (board[r][c] == 0) {
						loc.add(new int[] { r, c });
					}

					if (board[r][c] >= 6 && board[r][c] <= 10) {
						infos.put(r * N + c, teleport[board[r][c] - 6].size());
						teleport[board[r][c] - 6].add(new int[] { r, c });
					}
				}
			}


			int answer = 0;
			for (int[] start : loc) {
				for (int i = 0; i < 4; i++) {
					answer = Math.max(answer, simulate(start, i));
				}
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int simulate(int[] start, int dir) {
		int count = 0;
		int[] curr = new int[] { start[0], start[1], dir };
		boolean isStart = false;
		while (true) {

			if (isStart && start[0] == curr[0] && start[1] == curr[1]) {
				break;
			}
			
			if (!isStart && start[0] == curr[0] && start[1] == curr[1]) {
				isStart = true;
			}

			int nr = curr[0] + delta[curr[2]][0];
			int nc = curr[1] + delta[curr[2]][1];

			if (!isRange(nr, nc)) {
				int d = changeDir(5, curr[2]);
				count++;
				curr = new int[] { nr, nc, d };
				continue;
			}

			if (board[nr][nc] >= 6 && board[nr][nc] <= 10) {
				int value = infos.get(nr * N + nc) ^ 1;

				int[] next = teleport[board[nr][nc] - 6].get(value);
				curr = new int[] { next[0], next[1], curr[2] };
				continue;
			}

			if (board[nr][nc] == -1) {
				break;
			}

			if (board[nr][nc] >= 1 && board[nr][nc] <= 5) {
				int d = changeDir(board[nr][nc], curr[2]);
				curr = new int[] { nr, nc, d };
				count++;
				continue;
			}

			curr = new int[] { nr, nc, curr[2] };
		}
		return count;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static int changeDir(int block, int dir) {
		switch (block) {
		case 1:
			switch (dir) {
			case 0:
				return 1;
			case 1:
				return 3;
			case 2:
				return 0;
			case 3:
				return 2;
			}
		case 2:
			switch (dir) {
			case 0:
				return 3;
			case 1:
				return 0;
			case 2:
				return 1;
			case 3:
				return 2;
			}
		case 3:
			switch (dir) {
			case 0:
				return 2;
			case 1:
				return 0;
			case 2:
				return 3;
			case 3:
				return 1;
			}
		case 4:
			switch (dir) {
			case 0:
				return 1;
			case 1:
				return 2;
			case 2:
				return 3;
			case 3:
				return 0;
			}
		default:
			return dir ^ 1;
		}
	}
}