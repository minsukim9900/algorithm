import java.io.*;
import java.util.*;

public class Solution {
	private static int N;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			int ans = 0;

			for (int r = 0; r < N; r++) {
				String tmp = br.readLine();
				for (int c = 0; c < N; c++) {
					board[r][c] = tmp.charAt(c) - '0';
				}
			}
			
			ans = bfs(N >> 1, N >> 1, N >> 1);
			
			sb.append("#" + t + " ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int bfs(int r, int c, int depth) {
		int sum = 0;
		boolean[][] visited = new boolean[N][N];
		visited[r][c] = true;

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c, depth });

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			sum += board[curr[0]][curr[1]];

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					visited[nr][nc] = true;

					if(curr[2] - 1 >= 0) {						
						q.add(new int[] { nr, nc, curr[2] - 1 });
					}
				}
			}
		}
		return sum;
	}
}