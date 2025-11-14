import java.io.*;
import java.util.*;

public class Main {
	private static int R, C;
	private static int[][] board;
	private static int[] start;
	private static Queue<int[]> target;
	private static char[] orders;
	private static final int INF = 100_000_000;

	private static int[][] delta = { { 0, 0 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, -1 }, { 0, 0 }, { 0, 1 }, { -1, -1 },
			{ -1, 0 }, { -1, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new int[R][C];
		start = new int[2];
		target = new ArrayDeque<>();

		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				char chr = str.charAt(c);
				if (chr == 'I') {
					start[0] = r;
					start[1] = c;
				} else if (chr == 'R') {
					target.add(new int[] { r, c });
				}

				board[r][c] = chr == '.' ? 0 : chr == 'I' ? 1 : 2;
			}
		}

		orders = br.readLine().toCharArray();
		System.out.println(simulate());
	}

	private static String simulate() {
		for (int i = 0; i < orders.length; i++) {
			if (!move(orders[i] - '0'))
				return gameOver(i + 1);
			
			if (!moveTarget()) {
				return gameOver(i + 1);
			}
		}

		return output();
	}

	private static String output() {
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sb.append(board[r][c] == 0 ? '.' : board[r][c] == 1 ? 'I' : 'R');
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private static boolean moveTarget() {
		int size = target.size();

		List<int[]> nextMoveInfo = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			int[] curr = target.poll();
			int minLength = INF;

			int r = curr[0];
			int c = curr[1];
			int er = -1;
			int ec = -1;

			for (int dir = 1; dir <= 9; dir++) {
				if (dir == 5)
					continue;

				int nr = r + delta[dir][0];
				int nc = c + delta[dir][1];
				int nd = calDistance(nr, nc);

				if (isRange(nr, nc) && nd < minLength) {
					minLength = nd;
					er = nr;
					ec = nc;
				}
			}
			
			if(er == -1 && ec == -1) continue;
			
			board[r][c] = 0;

			if (board[er][ec] == 1)
				return false;

			nextMoveInfo.add(new int[] { er, ec });
		}

		Collections.sort(nextMoveInfo, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		
		if(nextMoveInfo.size() > 0) {
			int[] curr = nextMoveInfo.get(0);
			boolean flag = false;
			
			for (int i = 1; i < nextMoveInfo.size(); i++) {
				int[] comp = nextMoveInfo.get(i);
				
				if (curr[0] == comp[0] && curr[1] == comp[1]) {
					flag = true;
					continue;
				} else {
					if (!flag) {
						board[curr[0]][curr[1]] = 2;
						target.add(curr);
					}
					curr = comp;
					flag = false;
				}
			}
			
			if (!flag) {
				board[curr[0]][curr[1]] = 2;
				target.add(curr);
			}
		}
		return true;
	}

	private static String gameOver(int count) {
		return "kraj " + count;
	}

	private static int calDistance(int r, int c) {
		return Math.abs(start[0] - r) + Math.abs(start[1] - c);
	}

	private static boolean move(int dir) {
		board[start[0]][start[1]] = 0;
		start[0] += delta[dir][0];
		start[1] += delta[dir][1];
		
		if(board[start[0]][start[1]] == 2) return false;
		
		board[start[0]][start[1]] = 1;
		return true;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}