import java.io.*;
import java.util.*;

public class Solution {
	private static int N, K, answer;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new int[N][N];
			int max = 0;
			answer = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
					max = Math.max(max, board[r][c]);
				}
			}
			ArrayList<int[]> high = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (board[r][c] == max) {
						high.add(new int[] { r, c });
					}
				}
			}
			
			visited = new boolean[N][N];
			for (int[] curr : high) {
				visited[curr[0]][curr[1]] = true;
				dfs(curr[0], curr[1], false, 1);
				visited[curr[0]][curr[1]] = false;
			}
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int r, int c, boolean isComplete, int distance) {
		for (int idx = 0; idx < 4; idx++) {
			int nr = r + delta[idx][0];
			int nc = c + delta[idx][1];
			if (isRange(nr, nc) && !visited[nr][nc]) {
				int savePoint = board[nr][nc];
				
				if (board[nr][nc] < board[r][c]) {
					visited[nr][nc] = true;
					dfs(nr, nc, isComplete, distance + 1);
				} else {
					if (!isComplete && board[nr][nc] - K < board[r][c]) {
						isComplete = true;
						board[nr][nc] = board[r][c] - 1;
						visited[nr][nc] = true;
						dfs(nr, nc, isComplete, distance + 1);
						isComplete = false;
					} else {
						answer = Math.max(answer, distance);
					}
				}
				visited[nr][nc] = false;
				board[nr][nc] = savePoint;
			}
		}
	}

	private static boolean isRange(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < N);
	}
}