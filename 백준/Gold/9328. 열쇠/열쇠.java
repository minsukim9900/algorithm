import java.io.*;
import java.util.*;

public class Main {
	private static int H, W;
	private static char[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			board = new char[H + 2][W + 2];

			for (int r = 1; r < H + 1; r++) {
				String str = br.readLine();
				for (int c = 1; c < W + 1; c++) {
					board[r][c] = str.charAt(c - 1);
				}
			}

			String str = br.readLine();

			int key = 0;
			if (!str.equals("0")) {
				for (int i = 0; i < str.length(); i++) {
					char temp = str.charAt(i);
					key |= (1 << (temp - 'a'));
				}
			}

			System.out.println(bfs(key));
		}
	}

	private static int bfs(int key) {
		int result = 0;
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[H + 2][W + 2];
		visited[0][0] = true;
		q.add(new int[] { 0, 0 });

		List<int[]>[] doors = new ArrayList[26];
		for (int i = 0; i < 26; i++) {
			doors[i] = new ArrayList<>();
		}

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (isRange(nr, nc) && board[nr][nc] != '*' && !visited[nr][nc]) {
					if (board[nr][nc] >= 'A' && board[nr][nc] <= 'Z') {

						if ((key & (1 << (board[nr][nc] - 'A'))) == 0) {
							doors[board[nr][nc] - 'A'].add(new int[] { nr, nc });
							continue;
						}
					}

					visited[nr][nc] = true;

					if (board[nr][nc] >= 'a' && board[nr][nc] <= 'z') {
						if ((key & (1 << (board[nr][nc] - 'a'))) == 0) {
							key |= (1 << (board[nr][nc] - 'a'));

							for (int[] door : doors[board[nr][nc] - 'a']) {
								if (!visited[door[0]][door[1]]) {
									visited[door[0]][door[1]] = true;
									q.add(new int[] { door[0], door[1] });
								}
							}
						}

						board[nr][nc] = '.';
					}

					if (board[nr][nc] == '$') {
						result++;
						board[nr][nc] = '.';
					}
					q.add(new int[] { nr, nc });
				}
			}
		}

		return result;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < H + 2 && c >= 0 && c < W + 2;
	}
}