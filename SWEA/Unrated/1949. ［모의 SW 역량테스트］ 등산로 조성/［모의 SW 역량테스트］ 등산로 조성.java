import java.io.*;
import java.util.*;

public class Solution {

	private static int N, K;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int result;
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			visited = new boolean[N][N];
			result = 0;
			board = new int[N][N];

			int max = 0;
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					int tmp = Integer.parseInt(st.nextToken());
					board[r][c] = tmp;
					max = Math.max(max, tmp);
				}
			}
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (board[r][c] == max) {
						bfs(r, c, max, 1, true);
					}

				}
			}

			System.out.println("#" + tc + " " + result);

		}

	}

	private static void bfs(int r, int c, int h, int dist, boolean skill) {

		if (dist > result)
			result = dist;
		
		visited[r][c] = true;

		for (int i = 0; i < 4; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
				// 1. 다음 좌표의 높이가 내 높이 보다 낮아
				if (h > board[nr][nc]) {
					bfs(nr, nc, board[nr][nc], dist + 1, skill);
				} else if (skill && h > board[nr][nc] - K) {
					// 2 다음 좌표의 높이가 나와 같거나 높아
					bfs(nr, nc, board[r][c] - 1, dist + 1, false);
				}

			}

		}

		visited[r][c] = false;

	}

}