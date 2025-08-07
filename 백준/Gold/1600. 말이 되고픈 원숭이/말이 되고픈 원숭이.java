import java.io.*;
import java.util.*;

public class Main {
	private static int K, W, H;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -2, -1 }, { -2, 1 }, { -1, -2 },
			{ -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 }, { 2, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		board = new int[H][W];

		for (int r = 0; r < H; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < W; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(simulate());
	}

	private static int simulate() {
		int answer = Integer.MAX_VALUE;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { 0, 0, 0, K });
		boolean[][][] visited = new boolean[K + 1][H][W];
		for (int i = 0; i < K; i++) {
			visited[i][0][0] = true;
		}

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			if (curr[0] == H - 1 && curr[1] == W - 1) {
				answer = Math.min(answer, curr[2]);
				break;
			}

			int range = curr[3] > 0 ? delta.length : 4;

			for (int i = 0; i < range; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && board[nr][nc] == 0) {
					if (i >= 4 && !visited[curr[3] - 1][nr][nc]) {
						visited[curr[3] - 1][nr][nc] = true;
						q.add(new int[] { nr, nc, curr[2] + 1, curr[3] - 1 });
					} else if (i < 4 && !visited[curr[3]][nr][nc]) {
						visited[curr[3]][nr][nc] = true;
						q.add(new int[] { nr, nc, curr[2] + 1, curr[3] });
					}
				}
			}
		}

		return answer = answer == Integer.MAX_VALUE ? -1 : answer;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
}
