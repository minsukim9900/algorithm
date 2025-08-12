import java.io.*;
import java.util.*;

public class Solution {
	private static int N, M, K;
	private static int[][] board;
	private static final int MAX_LENGTH = 800;
	private static boolean[][] visited;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new int[MAX_LENGTH][MAX_LENGTH];
			visited = new boolean[MAX_LENGTH][MAX_LENGTH];

			Queue<int[]> cell = new ArrayDeque<>();

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < M; c++) {
					int state = Integer.parseInt(st.nextToken());
					board[(MAX_LENGTH / 2) + r][(MAX_LENGTH / 2) + c] = state;

					if (state > 0) {
						cell.add(new int[] { (MAX_LENGTH / 2) + r, (MAX_LENGTH / 2) + c, state, state, state });
						visited[(MAX_LENGTH / 2) + r][(MAX_LENGTH / 2) + c] = true;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(simulate(cell)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int simulate(Queue<int[]> cell) {
		int count = 0;

		while (K-- > 0) {
			int size = cell.size();
			List<int[]> growCell = new ArrayList<>();

			for (int i = 0; i < size; i++) {
				int[] curr = cell.poll();

				curr[2]--;
				if (curr[2] < 0) {
					growCell.add(curr);
				} else {
					cell.add(curr);
				}
			}

			Collections.sort(growCell, (a, b) -> a[4] == b[4] ? a[1] - b[1] : b[4] - a[4]);

			for (int[] c : growCell) {
				for (int i = 0; i < 4; i++) {
					int nr = c[0] + delta[i][0];
					int nc = c[1] + delta[i][1];

					if (nr >= 0 && nr < MAX_LENGTH && nc >= 0 && nc < MAX_LENGTH && !visited[nr][nc]) {
						board[nr][nc] = c[4];
						visited[nr][nc] = true;
						cell.add(new int[] { nr, nc, board[nr][nc], board[nr][nc], board[nr][nc] });
					}

				}
				c[3]--;
				if (c[3] > 0) {
					cell.add(c);
				}
			}
			count = cell.size();
		}
		return count;
	}
}