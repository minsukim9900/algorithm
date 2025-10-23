import java.io.*;
import java.util.*;

public class Main {
	private static int N, Q;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = 1 << (Integer.parseInt(st.nextToken()));
		Q = Integer.parseInt(st.nextToken());
		board = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[][] change = null;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			int q = Integer.parseInt(st.nextToken());
			int x = 1 << q;

			if (x > 1) {
				for (int r = 0; r < N; r += x) {
					for (int c = 0; c < N; c += x) {
						rotate(r, c, r + x, c + x);
					}
				}
			}

			change = new boolean[N][N];

			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (board[r][c] > 0 && !countIce(r, c)) {
						change[r][c] = true;
					}
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (change[r][c]) {
						board[r][c] = Math.max(0, board[r][c] - 1);
					}
				}
			}
		}

		int answer = 0;
		int max = 0;
		boolean[][] visited = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (board[r][c] > 0) {
					answer += board[r][c];

					if (!visited[r][c]) {
						max = Math.max(max, round(r, c, visited));
					}
				}
			}
		}
		sb.append(answer).append("\n");
		sb.append(max).append("\n");
		System.out.println(sb.toString());
	}

	private static int round(int r, int c, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c });
		visited[r][c] = true;

		int result = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			result++;

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && !visited[nr][nc] && board[nr][nc] > 0) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}
		return result;
	}

	private static boolean countIce(int r, int c) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (isRange(nr, nc) && board[nr][nc] > 0) {
				cnt++;
			}
			if (cnt == 3)
				return true;
		}
		return false;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	private static void rotate(int sr, int sc, int er, int ec) {
		List<Integer> nums = new ArrayList<>();
		for (int c = sc; c < ec; c++) {
			for (int r = er - 1; r >= sr; r--) {
				int tmp = board[r][c];
				nums.add(tmp);
			}
		}

		int idx = 0;
		for (int r = sr; r < er; r++) {
			for (int c = sc; c < ec; c++) {
				board[r][c] = nums.get(idx++);
			}
		}
	}
}
