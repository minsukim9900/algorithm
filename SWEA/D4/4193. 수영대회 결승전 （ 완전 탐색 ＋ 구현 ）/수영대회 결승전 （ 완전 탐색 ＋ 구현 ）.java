import java.io.*;
import java.util.*;

public class Solution {
	private static int N;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
        
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			board = new int[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());

				for (int c = 0; c < N; c++) {
					board[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] infos = new int[2][2];

			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());

				infos[i][0] = Integer.parseInt(st.nextToken());
				infos[i][1] = Integer.parseInt(st.nextToken());
			}

			sb.append("#" + t + " ").append(simulate(infos)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int simulate(int[][] infos) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});

		pq.add(new int[] { infos[0][0], infos[0][1], 0 });
		boolean[][] visited = new boolean[N][N];
		visited[infos[0][0]][infos[0][1]] = true;

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();

			if (curr[0] == infos[1][0] && curr[1] == infos[1][1]) {
				return curr[2];
			}

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && board[nr][nc] != 1 && !visited[nr][nc]) {
					if (board[nr][nc] == 0) {
						pq.add(new int[] { nr, nc, curr[2] + 1 });
					}

					if (board[nr][nc] == 2) {
						pq.add(new int[] { nr, nc, curr[2] + ((2 - (curr[2] % 3) + 3) % 3) + 1 });
					}

					visited[nr][nc] = true;
				}
			}
		}
		return -1;

	}

	private static boolean isRange(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < N);
	}
}