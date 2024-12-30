import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] ocean;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ocean = new int[N + 2][M + 2];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				ocean[r][c] = Integer.parseInt(st.nextToken());

			}
		}

		int time = 0;
		while (true) {

			boolean[][] visited = new boolean[N + 1][M + 1];
			Queue<int[]> q = new ArrayDeque<>();
			boolean first = true;

			for (int r = 1; r <= N; r++) {

				for (int c = 1; c <= M; c++) {

					if (first && ocean[r][c] > 0) {
						q.offer(new int[] { r, c, ocean[r][c] });
						first = false;
					}

				}

			}

			select(q, visited);
			time++;
			if (diviedCheck(visited)) {
				time--;
				break;
			}
			
			if(disappearCheck()) {
				time = 0;
				break;
			}
		}
		
		System.out.println(time);
	}

	private static void select(Queue<int[]> q, boolean[][] visited) {
		Queue<int[]> change = new ArrayDeque<>();
		visited[q.peek()[0]][q.peek()[1]] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int count = 0;

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (ocean[nr][nc] == 0) {
					count++;
				} else if (!visited[nr][nc] && ocean[nr][nc] > 0) {
					q.offer(new int[] { nr, nc, ocean[nr][nc] });
					visited[nr][nc] = true;
				}

			}

			change.offer(new int[] { curr[0], curr[1], count });

		}

		later(change);
		

	}

	private static void later(Queue<int[]> change) {

		while (!change.isEmpty()) {
			int[] curr = change.poll();

			int r = curr[0];
			int c = curr[1];
			ocean[r][c] -= curr[2];

			if (ocean[r][c] < 0) {
				ocean[r][c] = 0;
			}

		}

	}

	private static boolean diviedCheck(boolean[][] visited) {

		for (int r = 1; r <= N; r++) {

			for (int c = 1; c <= M; c++) {

				if (!visited[r][c] && ocean[r][c] > 0) {
					return true;
				}

			}

		}

		return false;
	}

	private static boolean disappearCheck() {
		int sum = 0;
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				sum += ocean[r][c];
			}
		}
		
		if(sum <= 0) {
			return true;
		}
		return false;
	}

}