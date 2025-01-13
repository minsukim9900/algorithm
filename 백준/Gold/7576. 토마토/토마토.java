import java.io.*;
import java.util.*;

public class Main {

	private static class tomato {
		int r, c, t;

		public tomato(int r, int c, int t) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;
		}

	}

	private static int M, N;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int time = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		Queue<tomato> q = new ArrayDeque<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());

				if (board[r][c] == 1) {
					q.add(new tomato(r, c, 0));
				}
			}
		}
		
		bfs(q);
		
		if (isClear()) {
			System.out.println(time);
		} else {
			System.out.println(-1);
		}

	}

	private static void bfs(Queue<tomato> q) {

		while (!q.isEmpty()) {

			tomato curr = q.poll();
			
			time = Math.max(time, curr.t);
			
			for (int i = 0; i < 4; i++) {
				int nr = curr.r + delta[i][0];
				int nc = curr.c + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && board[nr][nc] == 0) {
					board[nr][nc] = 1;
					q.add(new tomato(nr, nc, curr.t + 1));
				}
			}

		}


	}

	private static boolean isClear() {

		for (int r = 0; r < N; r++) {

			for (int c = 0; c < M; c++) {

				if (board[r][c] == 0) {
					return false;
				}

			}

		}

		return true;
	}

}
