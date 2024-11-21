import java.io.*;
import java.util.*;

public class Main {
	private static int[] dr = { 0, 1, -1, 1 };
	private static int[] dc = { 1, 0, 1, 1 };
	private static int[][] board;
	private static Queue<int[]> doll = new ArrayDeque<>();
	private static int N = 19;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		board = new int[N+2][N+2];

		for (int r = 1; r <= N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				board[r][c] = tmp;
				if (tmp != 0) {
					doll.add(new int[] { tmp, r, c });
				}
			}
		}

		while (!doll.isEmpty()) {
			int[] curr = doll.poll();
			int collor = curr[0];
			int r = curr[1];
			int c = curr[2];
			int cnt = 1;
			int tmp1 = circulator1(collor, r, c, cnt);
			if (tmp1 == 5) {
				System.out.println(collor);
				System.out.print(r);
				System.out.print(" ");
				System.out.print(c);
				return;
			}
			int tmp2 = circulator2(collor, r, c, cnt);
			if (tmp2 == 5) {
				System.out.println(collor);
				System.out.print(r);
				System.out.print(" ");
				System.out.print(c);
				return;
			}
			int tmp3 = circulator3(collor, r, c, cnt);
			if (tmp3 == 5) {
				System.out.println(collor);
				System.out.print(r);
				System.out.print(" ");
				System.out.print(c);
				return;
			}
			int tmp4 = circulator4(collor, r, c, cnt);
			if (tmp4 == 5) {
				System.out.println(collor);
				System.out.print(r);
				System.out.print(" ");
				System.out.print(c);
				return;
			}
		}
		System.out.println(0);

	}

	public static int circulator1(int collor, int r, int c, int cnt) {
		int nr = r + dr[0];
		int nc = c + dc[0];
		if ((c - 1) >= 0 && board[r][c - 1] != collor) {
			if (nr >= 0 && nr <= N && nc >= 0 && nc <= N) {
				while (nr <= N && nc <= N && board[nr][nc] == collor) {
					cnt++;
					nr += dr[0];
					nc += dc[0];
				}
			}
		}
		return cnt;
	}

	public static int circulator2(int collor, int r, int c, int cnt) {
		int nr = r + dr[1];
		int nc = c + dc[1];
		if ((r - 1) >= 0 && board[r - 1][c] != collor) {
			if (nr >= 0 && nr <= N && nc >= 0 && nc <= N) {
				while (nr <= N && nc <= N && board[nr][nc] == collor) {
					cnt++;
					nr += dr[1];
					nc += dc[1];
				}
			}
		}
		return cnt;
	}

	public static int circulator3(int collor, int r, int c, int cnt) {
		int nr = r + dr[2];
		int nc = c + dc[2];
		if ((r + 1) >= 0 && (c - 1) >= 0 && board[r + 1][c - 1] != collor) {
			if (nr >= 0 && nr <= N && nc >= 0 && nc <= N) {
				while (nr <= N && nc <= N && board[nr][nc] == collor) {
					cnt++;
					nr += dr[2];
					nc += dc[2];
				}
			}
		}
		return cnt;
	}

	public static int circulator4(int collor, int r, int c, int cnt) {
		int nr = r + dr[3];
		int nc = c + dc[3];
		if ((r - 1) >= 0 && (c - 1) >= 0 && board[r - 1][c - 1] != collor) {
			if (nr >= 0 && nr <= N && nc >= 0 && nc <= N) {
				while (nr <= N && nc <= N && board[nr][nc] == collor) {
					cnt++;
					nr += dr[3];
					nc += dc[3];
				}
			}
		}
		return cnt;
	}

}
