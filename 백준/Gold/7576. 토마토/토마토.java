import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] tomato;
	private static boolean[][] check;
	private static int[] dr = { -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, -1, 1 };
	private static Queue<int[]> q = new ArrayDeque<>();
	private static int max = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tomato = new int[M][N];
		check = new boolean[M][N];

		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				tomato[r][c] = Integer.parseInt(st.nextToken());
				if (tomato[r][c] == 1) {
					q.add(new int[] { r, c, 0 });
					check[r][c] = true;
				} else if (tomato[r][c] == -1)
					check[r][c] = true;

			}
		}

		bfs();
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				if (!check[r][c]) {
					System.out.println(-1);
					return;
				}
			}
		}

		System.out.println(max);

	}

	private static void bfs() {
		while (!q.isEmpty()) {
			int curr[] = q.poll();
			if (max < curr[2]) {
				max = curr[2];
			}
			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];
				if (nr >= 0 && nr < M && nc >= 0 && nc < N && tomato[nr][nc] == 0 && !check[nr][nc]) {
					check[nr][nc] = true;
					q.add(new int[] { nr, nc, curr[2] + 1 });
				}
			}
		}
	}

}
