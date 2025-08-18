import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static char[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];

		int[] start = new int[2];
		for (int r = 0; r < N; r++) {
			char[] tmp = br.readLine().toCharArray();

			for (int c = 0; c < M; c++) {
				board[r][c] = tmp[c];
				if (board[r][c] == '0') {
					start[0] = r;
					start[1] = c;
				}
			}
		}

		System.out.println(bfs(start));

	}

	private static int bfs(int[] start) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { start[0], start[1], 0, 0, -1 });
		boolean[][][] visited = new boolean[1 << 6][N][M];
		int answer = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			if (board[curr[0]][curr[1]] == '1') {
				answer = curr[3];
				break;
			}

			for (int i = 0; i < 4; i++) {
				if (curr[4] != -1 && (curr[4] ^ 1) == i) {
					continue;
				}

				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && board[nr][nc] != '#' && !visited[curr[2]][nr][nc]) {
					if (board[nr][nc] >= 'A' && board[nr][nc] <= 'F' && containsKey(curr[2], board[nr][nc] - 'A')) {
						visited[curr[2]][nr][nc] = true;
						q.add(new int[] { nr, nc, curr[2], curr[3] + 1, i });
					}

					if (board[nr][nc] >= 'a' && board[nr][nc] <= 'f') {
						visited[curr[2]][nr][nc] = true;
						q.add(new int[] { nr, nc, curr[2] | (1 << (board[nr][nc] - 'a')), curr[3] + 1, -1 });
					}

					if (board[nr][nc] == '0' || board[nr][nc] == '1' || board[nr][nc] == '.') {
						visited[curr[2]][nr][nc] = true;
						q.add(new int[] { nr, nc, curr[2], curr[3] + 1, i });
					}

				}
			}
		}
		return answer == 0 ? -1 : answer;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	private static boolean containsKey(int key, int door) {
		return (key & (1 << door)) == (1 << door);
	}
}