import java.io.*;
import java.util.*;

public class Solution {
	private static int N, min;
	private static char[][] infos;
	private static int[][] board;
	private static boolean[][] visited;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 },
			{ 1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			min = Integer.MAX_VALUE;
			infos = new char[N][N];
			board = new int[N][N];

			ArrayList<int[]> arr = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				String str = br.readLine();
				for (int c = 0; c < N; c++) {
					infos[r][c] = str.charAt(c);

					if (infos[r][c] == '.') {
						arr.add(new int[] { r, c });
					}

					if (infos[r][c] == '*') {
						board[r][c] = 9;
					}
				}
			}

			ArrayList<int[]> start = new ArrayList<>();
			for (int[] curr : arr) {
				board[curr[0]][curr[1]] = countBomb(curr);

				if (board[curr[0]][curr[1]] == 0) {
					start.add(curr);
				}
			}
			
			int cnt = 0;
			visited = new boolean[N][N];
			
			for (int[] curr : start) {
				if(visited[curr[0]][curr[1]]) {
					continue;
				}
				
				visited[curr[0]][curr[1]] = true;
				cnt++;
				bfs(curr);
			}

			for (int[] w : arr) {
				if(visited[w[0]][w[1]]) {
					continue;
				}
				
				visited[w[0]][w[1]] = true;
				cnt++;
			}

			sb.append("#" + t + " ").append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void bfs(int[] node) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(node);

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 8; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && !visited[nr][nc]) {
					if (board[nr][nc] == 0) {
						q.add(new int[] { nr, nc });
					}
					visited[nr][nc] = true;
				}
			}
		}
	}

	private static int countBomb(int[] curr) {
		int count = 0;

		for (int i = 0; i < 8; i++) {
			int nr = curr[0] + delta[i][0];
			int nc = curr[1] + delta[i][1];

			if (isRange(nr, nc) && infos[nr][nc] == '*') {
				count++;
			}
		}

		return count;
	}

	private static boolean isRange(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < N);
	}
}