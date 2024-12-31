
import java.io.*;
import java.util.*;

public class Main {

	private static int R, C;
	private static int[][] board;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new int[R][C];
		boolean check = false;
		int[] start = new int[2];

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());

				if (!check && board[r][c] == 0) {
					start[0] = r;
					start[1] = c;
					check = true;
				}

			}
		}

		int time = 0;
		int cheese = cnt();
		while (true) {
			visited = new boolean[R][C];
			bfs(start);
			time++;
			int tmp = cnt();
			if(tmp == 0) {
				System.out.println(time);
				System.out.println(cheese);
				return;
			}else {
				cheese = tmp;
			}
		}

	}

	private static void bfs(int[] start) {
		Queue<int[]> q = new ArrayDeque<>();

		q.add(start);

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < R && nc >= 0 && nc < C) {

					if (!visited[nr][nc] && board[nr][nc] == 0) {

						visited[nr][nc] = true;
						q.add(new int[] { nr, nc });

					} else if (!visited[nr][nc] && board[nr][nc] == 1) {

						board[nr][nc] = 0;
						visited[nr][nc] = true;

					}

				}
			}
		}
	}

	private static int cnt() {
		
		int sum = 0;
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sum += board[r][c];
			}
		}
		
		return sum;
	}

}
