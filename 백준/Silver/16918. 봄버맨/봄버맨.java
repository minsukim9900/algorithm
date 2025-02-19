import java.io.*;
import java.util.*;

public class Main {

	private static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int[][] map;
	private static int R, C, N;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				char tmp = str.charAt(c);

				if (tmp == '.')
					map[r][c] = 0;
				else
					map[r][c] = 2;
			}
		}

		int time = 1;
		while (time < N) {
			time++;
			ArrayList<int[]> adj = new ArrayList<>();
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					map[r][c]++;
					if (map[r][c] >= 2)
						adj.add(new int[] { r, c });
				}
			}
			if (time >= N)
				break;
			time++;
			bfs(adj);

		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 0)
					sb.append(".");
				else
					sb.append("O");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void bfs(ArrayList<int[]> adj) {

		boolean[][] visited = new boolean[R][C];
		int size = adj.size();
		for (int i = 0; i < size; i++) {
			int[] curr = adj.get(i);

			for (int idx = 0; idx < 4; idx++) {
				int nr = curr[0] + delta[idx][0];
				int nc = curr[1] + delta[idx][1];

				if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != 0 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					adj.add(new int[] { nr, nc });
				}
			}
		}

		explode(adj);
	}

	private static void explode(ArrayList<int[]> adj) {

		for (int i = 0; i < adj.size(); i++) {
			int[] curr = adj.get(i);

			map[curr[0]][curr[1]] = 0;
		}

	}

}
