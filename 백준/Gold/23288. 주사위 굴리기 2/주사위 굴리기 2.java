import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K, dir;
	private static int[] dice = { 1, 2, 3, 4, 5, 6 };
	private static int[][] delta = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	private static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dir = 3;

		board = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(simulate());
	}

	private static int simulate() {
		int r = 0;
		int c = 0;
		int A = dice[5];
		int B = board[r][c];
		int answer = 0;
		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < K; i++) {
			r += delta[dir][0];
			c += delta[dir][1];

			if (!isRange(r, c)) {
				r -= delta[dir][0];
				c -= delta[dir][1];

				dir = (dir + 2) % 4;
				r += delta[dir][0];
				c += delta[dir][1];
			}
			switch (dir) {
			case 0:
				north();
				break;
			case 1:
				west();
				break;
			case 2:
				south();
				break;
			case 3:
				east();
				break;
			}

			A = dice[5];
			B = board[r][c];
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					visited[y][x] = false;
				}
			}
			answer += bfs(r, c, B, visited);
			
			if (A > B) {
				dir = (dir + 3) % 4;
			} else if (A < B) {
				dir = (dir + 1) % 4;
			}
		}
		return answer;
	}

	private static int bfs(int r, int c, int num, boolean[][] visited) {
		int result = num;
		int cnt = 0;
		visited[r][c] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c });

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			cnt++;

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (isRange(nr, nc) && board[nr][nc] == num && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}
		return cnt * result;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	private static void east() {
		int tmp = dice[0];
		dice[0] = dice[3];
		dice[3] = dice[5];
		dice[5] = dice[2];
		dice[2] = tmp;
	}

	private static void west() {
		int tmp = dice[0];
		dice[0] = dice[2];
		dice[2] = dice[5];
		dice[5] = dice[3];
		dice[3] = tmp;
	}

	private static void south() {
		int tmp = dice[0];
		dice[0] = dice[1];
		dice[1] = dice[5];
		dice[5] = dice[4];
		dice[4] = tmp;
	}

	private static void north() {
		int tmp = dice[0];
		dice[0] = dice[4];
		dice[4] = dice[5];
		dice[5] = dice[1];
		dice[1] = tmp;
	}
}
