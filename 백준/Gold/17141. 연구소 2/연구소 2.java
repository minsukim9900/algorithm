import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Main {
	private static int N, M, answer, emptySpace;
	private static final int MAX = Integer.MAX_VALUE;
	private static int[][] board;
	private static List<int[]> virus;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		virus = new ArrayList<>();
		answer = MAX;

		emptySpace = 0;
		board = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				if (board[r][c] == 2) {
					virus.add(new int[] { r, c });
				}

				if (board[r][c] != 1) {
					emptySpace++;
				}
			}
		}
		selectVirus(0, 0, new int[M][2]);
		System.out.println(answer == MAX ? -1 : answer);
	}

	private static void selectVirus(int idx, int depth, int[][] viruses) {
		if (depth == M) {
			answer = Math.min(answer, simulate(viruses));
			return;
		}

		for (int i = idx; i < virus.size(); i++) {
			viruses[depth] = virus.get(i);
			selectVirus(i + 1, depth + 1, viruses);
		}
	}

	private static int simulate(int[][] viruses) {
		int result = 0;
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new ArrayDeque<>();
		for (int[] v : viruses) {
			q.add(new int[] { v[0], v[1], 0 });
			visited[v[0]][v[1]] = true;
		}

		int cnt = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			int d = curr[2];

			result = Math.max(result, d);
			cnt++;

			for (int i = 0; i < 4; i++) {
				int nr = r + delta[i][0];
				int nc = c + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] != 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc, d + 1 });
				}
			}
		}

		return cnt == emptySpace ? result : MAX;
	}

}
