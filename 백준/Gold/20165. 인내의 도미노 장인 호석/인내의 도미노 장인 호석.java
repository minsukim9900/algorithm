import java.io.*;
import java.util.*;;

public class Main {
	private static int N, M, R, score;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		visited = new boolean[N][M];

		board = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int dir = changeDir(st.nextToken());

			simulate(r, c, dir);

			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			visited[r][c] = false;
		}

		sb.append(score).append("\n");
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				char ch = 'S';
				if(visited[r][c]) {
					ch = 'F';
				}
				sb.append(ch).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

	private static void output() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(visited[r][c] + " ");
			}
			System.out.println();
		}
	}

	private static void simulate(int r, int c, int dir) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c, board[r][c] });
		while (!q.isEmpty()) {
			int[] curr = q.poll();

			if (visited[curr[0]][curr[1]])
				continue;

			visited[curr[0]][curr[1]] = true;
			score++;

			int nr = curr[0];
			int nc = curr[1];

			for (int i = 1; i < curr[2]; i++) {
				nr += delta[dir][0];
				nc += delta[dir][1];

				if (isRange(nr, nc) && !visited[nr][nc]) {
					q.add(new int[] { nr, nc, board[nr][nc] });
				}
			}
		}
	}

	private static int changeDir(String dir) {
		if (dir.equals("E"))
			return 3;
		if (dir.equals("N"))
			return 0;
		if (dir.equals("S"))
			return 1;
		return 2;
	}

	private static boolean isRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}