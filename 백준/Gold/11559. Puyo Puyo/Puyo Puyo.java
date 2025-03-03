import java.io.*;
import java.util.*;

public class Main {

	private static char[][] map;
	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		map = new char[12][6];
		visited = new boolean[12][6];

		for (int r = 0; r < 12; r++) {

			String str = br.readLine();
			for (int c = 0; c < 6; c++) {

				map[r][c] = str.charAt(c);
			}
		}
		int time = 0;
		while (true) {

			boolean isPossible = false;
			ArrayList<int[]> bomb = new ArrayList<>();

			for (int r = 0; r < 12; r++) {

				for (int c = 0; c < 6; c++) {

					if (map[r][c] >= 'A' && map[r][c] <= 'Z' && !visited[r][c]) {
						ArrayList<int[]> select = bfs(new int[] { r, c, map[r][c] });

						if (select.size() >= 4) {
							isPossible = true;
							bomb.addAll(select);
						}
					}

				}

			}

			if (!isPossible)
				break;

			process(bomb);
			time++;
			visited = new boolean[12][6];

		}

		System.out.println(time);

	}

	private static void process(ArrayList<int[]> arr) {

		for (int[] w : arr) {
			map[w[0]][w[1]] = '.';
		}

		delete();

	}

	private static void delete() {

		for (int c = 0; c < 6; c++) {

			for (int r = 11; r > 0; r--) {

				if (map[r][c] == '.') {

					for (int i = r - 1; i >= 0; i--) {

						if (map[i][c] != '.') {
							map[r][c] = map[i][c];
							map[i][c] = '.';
							break;
						}

					}

				}

			}

		}


	}

	private static ArrayList<int[]> bfs(int[] start) {
		visited[start[0]][start[1]] = true;

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(start);

		ArrayList<int[]> bomb = new ArrayList<>();

		while (!q.isEmpty()) {

			int[] curr = q.poll();
			bomb.add(curr);

			for (int i = 0; i < 4; i++) {

				int nr = curr[0] + delta[i][0];
				int nc = curr[1] + delta[i][1];

				if (nr >= 0 && nr < 12 && nc >= 0 && nc < 6 && map[nr][nc] == curr[2] && !visited[nr][nc]) {

					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc, map[nr][nc] });
				}

			}
		}

		return bomb;

	}

}
