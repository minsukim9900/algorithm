import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		boolean[][] visited = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = 0;
		int max = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (board[r][c] == 1 && !visited[r][c]) {
					count++;
					int space = bfs(r, c, visited);
					max = Math.max(space, max);
				}
			}
		}
		sb.append(count).append("\n");
		sb.append(max);
		System.out.println(sb.toString());
	}

	private static int bfs(int r, int c, boolean[][] visited) {
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

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && board[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}
		return result;
	}
}