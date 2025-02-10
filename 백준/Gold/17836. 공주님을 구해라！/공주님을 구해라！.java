import java.io.*;
import java.util.*;

public class Main {

	private static int N, M, T;
	private static int[][] maze;
	private static int[][] bugMaze;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static boolean[][] preVisited;
	private static boolean[][] postVisited;
	private static int cnt = 987654321;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		maze = new int[N][M];
		bugMaze = new int[N][M];
		preVisited = new boolean[N][M];
		postVisited = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				maze[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		mazeBfs();
		
		if(cnt > T) {
			System.out.println("Fail");
		}else {
			System.out.println(cnt);
		}

	}

	private static void mazeBfs() {

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { 0, 0, 0 });
		preVisited[0][0] = true;

		while (!q.isEmpty()) {

			int[] curr = q.poll();

			if ((curr[0] == N - 1) && (curr[1] == M - 1)) {
				cnt = Math.min(cnt, curr[2]);
			}

				for (int i = 0; i < 4; i++) {
					int nr = curr[0] + delta[i][0];
					int nc = curr[1] + delta[i][1];

					if (nr >= 0 && nr < N && nc >= 0 && nc < M && !preVisited[nr][nc]) {

						if (maze[nr][nc] == 0) {
							preVisited[nr][nc] = true;
							q.offer(new int[] { nr, nc, curr[2] + 1 });
						} else if (maze[nr][nc] == 2) {
							preVisited[nr][nc] = true;
							bugBfs(nr, nc, curr[2] + 1);
							q.offer(new int[] { nr, nc, curr[2] + 1 });

						}

					}
				}

		}

	}

	private static void bugBfs(int r, int c, int len) {

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c, len });
		postVisited[r][c] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			
			if ((curr[0] == N - 1) && (curr[1] == M - 1)) {
				cnt = Math.min(cnt, curr[2]);
			}

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && bugMaze[nr][nc] == 0 && !postVisited[nr][nc]) {
					postVisited[nr][nc] = true;
					q.offer(new int[] { nr, nc, curr[2] + 1 });
				}
			}
		}

	}

}
