import java.io.*;
import java.util.*;

public class Main {

	private static int N, M;
	private static int[][] map;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int r = 0; r < N; r++) {

			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

			}
		}

		int time = 0;
		while (true) {
			selectCorner(wallCheese());
			time++;
			
			if (!isRemain())
				break;

		}

		System.out.println(time);

	}

	private static ArrayList<int[]> wallCheese() {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new ArrayDeque<>();

		ArrayList<int[]> cheese = new ArrayList<>();
		visited[0][0] = true;
		q.offer(new int[] { 0, 0 });
		map[0][0] = 2;

		while (!q.isEmpty()) {

			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {

				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {

					visited[nr][nc] = true;

					if (map[nr][nc] == 0 || map[nr][nc] == 2) {
						map[nr][nc] = 2;
						q.add(new int[] { nr, nc });
					} else {
						cheese.add(new int[] { nr, nc });
					}

				}

			}

		}

		return cheese;

	}

	private static void selectCorner(ArrayList<int[]> cheese) {

		ArrayList<int[]> deleteCheese = new ArrayList<>();

		for (int i = 0; i < cheese.size(); i++) {
			int[] curr = cheese.get(i);

			int cnt = 0;

			for (int j = 0; j < 4; j++) {

				int nr = curr[0] + delta[j][0];
				int nc = curr[1] + delta[j][1];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 2) {
					cnt++;
				}
			}

			if (cnt >= 2) {
				deleteCheese.add(curr);
			}

		}

		deleteCheese(deleteCheese);

	}

	private static void deleteCheese(ArrayList<int[]> deleteCheese) {

		for (int[] w : deleteCheese) {
			map[w[0]][w[1]] = 0;
		}

	}

	private static boolean isRemain() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(map[r][c] == 1) return true;
			}
		}

		return false;
	}

	private static void output() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}

}
