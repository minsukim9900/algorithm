import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Main {
	private static int N, answer;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	private static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = 5;
		board = new int[N][N];
		visited = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				board[r][c] = str.charAt(c) == 'Y' ? 0 : 1;
			}
		}

		dfs(0, 0, 0, 0);
		System.out.println(answer);
	}

	private static void dfs(int depth, int count, int r, int c) {
		if (count > 3)
			return;

		if (depth == 7) {
			if (isConnected())
				answer++;
			return;
		}
		
		if(r == N) return;

		int nr = r;
		int nc = c + 1;

		if (nc == N) {
			nr = r + 1;
			nc = 0;
		}

		visited[r][c] = true;
		dfs(depth + 1, count + (board[r][c] == 0 ? 1 : 0), nr, nc);
		visited[r][c] = false;
		dfs(depth, count, nr, nc);
	}

	private static boolean isConnected() {
		int sr = -1;
		int sc = -1;

		out: for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (visited[r][c]) {
					sr = r;
					sc = c;
					break out;
				}
			}
		}

		if (sr == -1)
			return false;

		boolean[][] vis = new boolean[N][N];

		Queue<int[]> q = new ArrayDeque<>();
		vis[sr][sc] = true;
		q.add(new int[] { sr, sc });
		int cnt = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			cnt++;

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && visited[nr][nc] && !vis[nr][nc]) {
					vis[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}
		return cnt == 7;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
